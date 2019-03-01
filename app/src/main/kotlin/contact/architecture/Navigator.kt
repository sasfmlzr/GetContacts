package contact.architecture

import androidx.navigation.NavController
import javax.inject.Inject

class Navigator @Inject constructor() {

    private var nav: NavController? = null

    fun setNavigator(nav: NavController) {
        this.nav = nav
    }

    fun getNavigator(): NavController {
        return nav!!
    }

    fun clearNavigator() {
        this.nav = null
    }
}