package contact.architecture.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import contact.di.core.ActivityComponent
import contact.di.core.Injector

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun inject(component: ActivityComponent)
    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        inject(Injector.navigationComponent())
    }
}
