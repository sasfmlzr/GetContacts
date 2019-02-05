package contact.fragment.contacts

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import contact.R
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.pipe.contacts.ContactsInitEvent
import javax.inject.Inject

class ContactsUi @Inject constructor(
        private val logger: Logger
) : Ui<ContactsModel>() {

    @BindView(R.id.contacts)
    lateinit var contactView: TextView

    override fun bindViews(view: View): Unbinder {
        val unbinder = ButterKnife.bind(this, view)
      //  logger.d("ContactsUi")
        val textView = view.findViewById<TextView>(R.id.contacts)
        return unbinder
    }

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(ContactsInitEvent())
        logger.d("asdsa", "Asdsadas")
    }

    override fun render(model: ContactsModel) {
        logger.d("ContactsUi", "RENDER EXECUTED")
        if (model.contactsResult != null) {
            contactView.text = model.contactsResult
        } else {
            contactView.text = "asfasf"
        }
    }
}
