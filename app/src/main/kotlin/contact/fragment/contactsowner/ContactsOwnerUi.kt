package contact.fragment.contactsowner

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import contact.R
import contact.api.model.contact.OwnerContacts
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

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        contactRV.layoutManager = LinearLayoutManager(context)
        eventSource.onNext(RequestObserveContactsOwnerEvent())
    }

    override fun render(model: ContactsOwnerModel) {
        when (model.eventModel) {
            is ObserveContactsOwnerEventModel -> {
                configureAdapter(model.eventModel.contacts!!)
            }
        }
        logger.d("ContactsOwnerUi", "RENDER EXECUTED")
    }

    private fun configureAdapter(listOwnerContacts: List<OwnerContacts>) {
        val adapter = ContactsOwnerListAdapter { logger.d("WTF", "ROUTE TO NEXT") }
        adapter.submitList(listOwnerContacts)
        contactRV.adapter = adapter
    }
}
