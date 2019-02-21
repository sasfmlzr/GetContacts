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

        retryButton = findViewById(R.id.buttotRetry)
        retryButton.setOnClickListener {
            initializeApp()
        }
        if (savedInstanceState == null) {
            initializeApp()
        }
    }

    private fun initializeApp() {
        coroutineScope.launch {

            delay(MIN_SHOW_TIME)
            withContext(Dispatchers.Default) {
                initUseCase.buildUseCaseObservable(Unit).doOnComplete {
                    loadingMainActivity()
                }.doOnError {
                    Snackbar.make(findViewById(android.R.id.content),
                            "Server not response",
                            Snackbar.LENGTH_LONG).show()
                    coroutineScope.launch {
                        withContext(Dispatchers.Main) {
                            retryButton.visibility = View.VISIBLE
                        }
                    }
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
