package contact.activity.container

import android.os.Bundle
import contact.R
import contact.architecture.base.BaseActivity
import contact.di.core.ActivityComponent
import contact.fragment.contacts.ContactsFragment

class ContainerActivity : BaseActivity() {

    override val layoutId = R.layout.activity_container_fragment

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ContactsFragment())
                .commit()
    }
}
