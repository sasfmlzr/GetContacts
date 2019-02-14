package contact.activity.container

import androidx.navigation.Navigation
import contact.R
import contact.architecture.base.BaseActivity
import contact.di.core.ActivityComponent

class ContainerActivity : BaseActivity() {

    override val layoutId = R.layout.activity_container_fragment

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
}
