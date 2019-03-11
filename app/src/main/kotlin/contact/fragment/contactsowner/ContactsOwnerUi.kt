package contact.fragment.contactsowner

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import contact.R
import contact.api.model.contact.OwnerContacts
import contact.architecture.base.ui.BaseModel
import contact.architecture.base.ui.Ui
import contact.fragment.contactsowner.list.ContactsOwnerListAdapter
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel
import contact.pipe.contactsowner.RequestObserveContactsOwnerEvent
import contact.pipe.contactsowner.RouteToNavigatorFragmentEvent
import javax.inject.Inject

class ContactsOwnerUi @Inject constructor(
        private val context: Context
) : Ui() {

    @BindView(R.id.recycler_view_owner_contacts)
    lateinit var contactRV: RecyclerView

    private lateinit var expMgr: RecyclerViewExpandableItemManager

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        expMgr = RecyclerViewExpandableItemManager(null)
        contactRV.layoutManager = LinearLayoutManager(context)
        eventSource.onNext(RequestObserveContactsOwnerEvent())

        (contactRV.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    override fun render(model: BaseModel) {
        when (model.eventModel) {
            is ObserveContactsOwnerEventModel -> {
                val contacts = model.eventModel.contacts!!
                if(contacts.isEmpty()){
                    extensions?.showMessage("Server is empty")
                }
                configureAdapter(model.eventModel.contacts!!)
            }
        }
    }

    private fun configureAdapter(listOwnerContacts: List<OwnerContacts>) {
        val adapter = expMgr.createWrappedAdapter(
                ContactsOwnerListAdapter(listOwnerContacts) { nameOwner, locationSize ->
                    if (locationSize > 0) {
                        eventSource.onNext(RouteToNavigatorFragmentEvent(nameOwner))
                    } else {
                        extensions?.showMessage("No locations")
                    }
                })

        contactRV.adapter = adapter
        expMgr.attachRecyclerView(contactRV)
    }

    override fun onDestroy() {
        super.onDestroy()
        expMgr.release()
    }
}
