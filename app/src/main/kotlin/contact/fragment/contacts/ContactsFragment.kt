package contact.fragment.contacts

import contact.R
import contact.architecture.base.BaseFragment
import contact.di.core.FragmentComponent
import contact.pipe.contacts.ContactsFunnel
import contact.pipe.contacts.ContactsPipeline
import contact.pipe.contacts.ContactsState
import javax.inject.Inject

class ContactsFragment : BaseFragment<ContactsState, ContactsModel, ContactsUi>() {

    override val layoutId: Int = R.layout.fragment_contacts

    @Inject
    override lateinit var presenter: ContactsPresenter
    @Inject
    override lateinit var pipeline: ContactsPipeline
    @Inject
    override lateinit var ui: ContactsUi

    override fun funnel(): ContactsFunnel = ContactsFunnel(ContactsState.idle())

    override fun inject(component: FragmentComponent) = component.inject(this)
}
