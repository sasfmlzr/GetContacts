package contact.architecture

import contact.fragment.contactsowner.ContactsOwnerFragmentDirections
import javax.inject.Inject

class NavigationRouter @Inject constructor(private val router: Navigator) : Router {

    override fun navigateContactOwnerToLocationFragment(id: String) {
        val direction = ContactsOwnerFragmentDirections
                .actionShowLocationFragment()
        direction.arguments.putString("nameOwner", id)

        router.getNavigator().navigate(direction)
    }
}
