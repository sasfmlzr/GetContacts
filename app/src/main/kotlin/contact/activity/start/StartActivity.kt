package contact.activity.start

import android.content.Intent
import android.os.Bundle
import contact.R
import contact.activity.container.ContainerActivity
import contact.architecture.base.BaseActivity
import contact.di.core.ActivityComponent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StartActivity : BaseActivity() {

    companion object {
        const val MIN_SHOW_TIME: Long = 2000
    }

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
            withContext(Dispatchers.Main) {
                loadingMainActivity()
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
