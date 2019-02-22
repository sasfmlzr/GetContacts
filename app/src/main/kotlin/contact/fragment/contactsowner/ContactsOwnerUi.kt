package contact.fragment.contactsowner

import android.content.Context
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import contact.R
import contact.api.model.contact.OwnerContacts
import contact.architecture.Navigator
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.fragment.contactsowner.list.ContactsOwnerListAdapter
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel
import contact.pipe.contactsowner.RequestObserveContactsOwnerEvent
import javax.inject.Inject

class ContactsOwnerUi @Inject constructor(
        private val context: Context,
        private val logger: Logger
) : Ui<ContactsOwnerModel>() {

    @BindView(R.id.recycler_view_owner_contacts)
    lateinit var contactRV: RecyclerView

    private val expMgr = RecyclerViewExpandableItemManager(null)

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        contactRV.layoutManager = LinearLayoutManager(context)
        eventSource.onNext(RequestObserveContactsOwnerEvent())

        (contactRV.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    override fun render(model: ContactsOwnerModel) {
        when (model.eventModel) {
            is ObserveContactsOwnerEventModel -> {
                configureAdapter(model.eventModel.contacts!!)
            }
        }
    }

    private fun configureAdapter(listOwnerContacts: List<OwnerContacts>) {
        val adapter = expMgr.createWrappedAdapter(
                ContactsOwnerListAdapter(listOwnerContacts){ nameOwner ->
                    logger.d("WTF", "ROUTE TO LOCATION $nameOwner")
                    Navigator(contactRV.findNavController()).navigateContactOwnerToLocationFragment()
                })

        contactRV.adapter = adapter
        expMgr.attachRecyclerView(contactRV)
    }
}
