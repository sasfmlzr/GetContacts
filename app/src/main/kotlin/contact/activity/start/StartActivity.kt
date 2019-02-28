package contact.activity.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import contact.R
import contact.activity.container.ContainerActivity
import contact.architecture.base.BaseActivity
import contact.di.core.ActivityComponent
import contact.usecase.feature.InitUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class StartActivity : BaseActivity() {

    companion object {
        const val MIN_SHOW_TIME: Long = 2000
    }

    @Inject
    lateinit var initUseCase: InitUseCase

    override val layoutId = R.layout.activity_start

    private lateinit var retryButton: Button

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    private val coroutineScope = CoroutineScope(coroutineContext)

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retryButton = findViewById(R.id.buttonRetry)
        retryButton.setOnClickListener {
            initializeApp()
        }

        if (savedInstanceState == null) {
            initializeApp()
        }
    }

    private fun initializeApp() {
        coroutineScope.launch {

            withContext(Dispatchers.Default) {
                val timeElapsed = measureTimeMillis {
                    executeInitUseCase()
                }
                delay(MIN_SHOW_TIME - timeElapsed)
            }
        }
    }

    private fun executeInitUseCase() {
        initUseCase.buildUseCaseObservable(Unit).doOnComplete {
            loadingMainActivity()
        }.doOnError {
            it.printStackTrace()
            Snackbar.make(findViewById(android.R.id.content),
                    "Server not response",
                    Snackbar.LENGTH_LONG).show()
            doRetryBtnVisible()
        }.onErrorComplete()
                .subscribe()
    }

    private fun doRetryBtnVisible() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                retryButton.visibility = View.VISIBLE
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
