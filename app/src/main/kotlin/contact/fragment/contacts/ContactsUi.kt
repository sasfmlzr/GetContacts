package contact.fragment.contacts

import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import contact.R
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.pipe.contacts.ContactsInitEvent
import contact.pipe.contacts.ContactsInitEventModel
import contact.pipe.contacts.ContactsPushEvent
import contact.pipe.contacts.ContactsPushEventModel
import javax.inject.Inject

class ContactsUi @Inject constructor(
        private val logger: Logger
) : Ui<ContactsModel>() {

    @BindView(R.id.contacts)
    lateinit var contactView: TextView
    @BindView(R.id.button)
    lateinit var button: Button

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(ContactsInitEvent())
        logger.d("asdsa", "Asdsadas")
    }

    override fun render(model: ContactsModel) {
        when (model.result) {
            is ContactsInitEventModel -> contactView.text = model.result.contactsResult
            is ContactsPushEventModel -> contactView.text = model.result.contactsPushResult
        }
        logger.d("ContactsUi", "RENDER EXECUTED")
    }

    @OnClick(R.id.button)
    fun pushNutton(){
        eventSource.onNext(ContactsPushEvent())
    }
}
