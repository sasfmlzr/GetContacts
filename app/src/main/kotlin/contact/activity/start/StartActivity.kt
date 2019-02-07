package contact.activity.start

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import contact.R
import contact.activity.container.ContainerActivity
import contact.architecture.base.BaseActivity
import contact.di.core.ActivityComponent
import contact.usecase.feature.InitUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class StartActivity : BaseActivity() {

    companion object {
        const val MIN_SHOW_TIME: Long = 2000
    }

    @Inject
    lateinit var initUseCase: InitUseCase

    override val layoutId = R.layout.activity_start

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    private val coroutineScope = CoroutineScope(coroutineContext)

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeApp()
    }

    private fun initializeApp() {
        coroutineScope.launch {
              delay(MIN_SHOW_TIME)
            withContext(Dispatchers.IO) {
                initUseCase.buildUseCaseObservable(Unit).doOnComplete {
                    loadingMainActivity()
                }.doOnError {
                    Snackbar.make(findViewById(android.R.id.content),
                            "Server not response",
                            Snackbar.LENGTH_LONG).show()
                }.onErrorComplete()
                        .subscribe()
            }
        }
    }

    private fun loadingMainActivity() {
        val intent = Intent(applicationContext,
                ContainerActivity::class.java)
        startActivity(intent)
        finish()
    }
}
