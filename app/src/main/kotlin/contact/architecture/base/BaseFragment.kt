package contact.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    @Inject
    lateinit var logger: Logger

    protected abstract val layoutId: Int
    protected abstract val pipeline: Pipeline
    protected abstract val presenter: Presenter<S, M>
    protected abstract val ui: U
    protected abstract fun funnel(): Funnel<S>
    protected abstract fun inject(component: FragmentComponent)

    protected val eventSource = PublishSubject.create<ViewEvent>()
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
        val toolkit = UiToolkit(
                view,
                activity.supportActionBar,
                null,
                eventSource,
                extensions)
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
    }
}
