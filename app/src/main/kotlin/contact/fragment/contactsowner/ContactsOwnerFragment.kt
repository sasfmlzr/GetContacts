package contact.fragment.contactsowner

import contact.R
import contact.architecture.base.BaseFragment
import contact.di.core.FragmentComponent
import contact.pipe.contactsowner.ContactsOwnerFunnel
import contact.pipe.contactsowner.ContactsOwnerPipeline
import contact.pipe.contactsowner.ContactsOwnerState
import javax.inject.Inject

class ContactsOwnerFragment : BaseFragment<ContactsOwnerState, ContactsOwnerModel, ContactsOwnerUi>() {

    override val layoutId: Int = R.layout.fragment_contacts_owner

    @Inject
    override lateinit var presenter: ContactsOwnerPresenter
    @Inject
    override lateinit var pipeline: ContactsOwnerPipeline
    @Inject
    override lateinit var ui: ContactsOwnerUi

    override fun funnel(): ContactsOwnerFunnel = ContactsOwnerFunnel(ContactsOwnerState.idle())

    override fun inject(component: FragmentComponent) = component.inject(this)
}
