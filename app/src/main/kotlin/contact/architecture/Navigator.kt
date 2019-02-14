package contact.architecture

import contact.fragment.contactsowner.ContactsOwnerFragmentDirections

import androidx.navigation.NavController

class Navigator(private val router: NavController) {

    fun navigateContactOwnerToLocationFragment() {
        val direction = ContactsOwnerFragmentDirections
                .actionShowLocationFragment()
        router.navigate(direction)
    }

}