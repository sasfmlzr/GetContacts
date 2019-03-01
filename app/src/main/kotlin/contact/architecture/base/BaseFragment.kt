package contact.architecture.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.SupportMapFragment
import contact.R
import contact.architecture.*
import contact.architecture.base.ui.Ui
import contact.architecture.base.ui.UiModel
import contact.architecture.base.ui.UiToolkit
import contact.architecture.logging.Logger
import contact.di.core.FragmentComponent
import contact.di.core.Injector
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class BaseFragment<S : ViewState, M : UiModel, out U : Ui<M>>
    : Fragment() {

    companion object {
        private const val permissionRequestCode = 100
    }

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var navigator: Navigator

    protected abstract val layoutId: Int
    protected abstract val pipeline: Pipeline
    protected abstract val presenter: Presenter<S, M>
    protected abstract val ui: U
    protected abstract fun funnel(): Funnel<S>
    protected abstract fun inject(component: FragmentComponent)

    private val eventSource = PublishSubject.create<ViewEvent>()
    private lateinit var plumbing: Observable<M>
    private val defaultDisposable = CompositeDisposable()
    private val uiDisposable = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(Injector.viewComponent())

        createPlumbing()
                .also { plumbing = it }
                .run { subscribe({}, {}) }
                .also { defaultDisposable.add(it) }

        navigator.setNavigator(findNavController())
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    private fun createPlumbing() =
            eventSource
                    .observeOn(Schedulers.io())
                    .compose(pipeline)
                    .compose(funnel())
                    .compose(presenter)
                    .distinctUntilChanged()
                    .replay(1)
                    .autoConnect()
                    .observeOn(AndroidSchedulers.mainThread())

    protected fun sendEvent(event: ViewEvent) {
        eventSource.onNext(event)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = activity as AppCompatActivity

        val extensions = Extensions(activity)
        val toolbar: Toolbar? = view.findViewById(R.id.toolbar)
                ?: activity.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)
        val toolkit = UiToolkit(
                view,
                activity.supportActionBar,
                toolbar,
                eventSource,
                extensions, runtimePermissions)

        (ui as? GoogleMapCallback)?.let {
            val mapFr = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            it.getMap(mapFr)
        }

        ui.init(toolkit)
        plumbing.subscribe(ui::render).also { uiDisposable.add(it) }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        uiDisposable.clear()
        ui.onDestroy()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        defaultDisposable.clear()
        navigator.clearNavigator()
    }

    private val runtimePermissions = object : RuntimePermissions {
        override fun request(permissions: List<String>) {
            requestPermissions(permissions.toTypedArray(), permissionRequestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != permissionRequestCode) {
            return
        }

        val grantedPermissions: MutableList<String> = mutableListOf()

        if (permissions.isNotEmpty() && grantResults.isNotEmpty()) {
            for ((index, value) in grantResults.withIndex()) {
                if (value == PackageManager.PERMISSION_GRANTED) {
                    grantedPermissions.add(permissions[index])
                }
            }
        }

        (ui as? PermissionsCallback)?.onPermissionsGranted(grantedPermissions)
    }
}
