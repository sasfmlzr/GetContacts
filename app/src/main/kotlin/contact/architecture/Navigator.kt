package contact.architecture

import contact.fragment.contactsowner.ContactsOwnerFragmentDirections

import androidx.navigation.NavController

class Navigator(private val router: NavController) : Router {

    override fun navigateContactOwnerToLocationFragment(id: String) {
        val direction = ContactsOwnerFragmentDirections
                .actionShowLocationFragment()
        direction.arguments.putString("nameOwner", id)
        router.navigate(direction)
    }

}
