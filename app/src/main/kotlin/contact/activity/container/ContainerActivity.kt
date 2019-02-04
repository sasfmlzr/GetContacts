package contact.activity.container

import android.os.Bundle
import contact.R
import contact.architecture.base.BaseActivity
import contact.databinding.ActivityContainerFragmentBinding
import contact.di.core.ActivityComponent
import contact.fragment.contacts.ContactsFragment
import kotlinx.android.synthetic.main.activity_container_fragment.*

class ContainerActivity : BaseActivity<ContainerActivityVM,
        ActivityContainerFragmentBinding>(ContainerActivityVM::class.java) {

    override val layoutId = R.layout.activity_container_fragment

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, ContactsFragment())
                .commit()
    }
}
