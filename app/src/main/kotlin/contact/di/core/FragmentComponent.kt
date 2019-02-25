package contact.di.core

import contact.di.base.FragmentScope
import contact.fragment.contactsowner.ContactsOwnerFragment
import contact.fragment.location.LocationFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentComponent {
    fun inject(fragment: ContactsOwnerFragment)
    fun inject(fragment: LocationFragment)
}
