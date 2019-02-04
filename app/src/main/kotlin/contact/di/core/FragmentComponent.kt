package contact.di.core

import contact.di.base.FragmentScope
import contact.fragment.contacts.ContactsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentComponent {
    fun inject(fragment: ContactsFragment)
}
