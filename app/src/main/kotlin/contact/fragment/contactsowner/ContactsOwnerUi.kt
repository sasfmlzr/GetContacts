package contact.fragment.contactsowner

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import contact.R
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.pipe.contacts.*
import contact.pipe.contactsowner.*
import javax.inject.Inject

class ContactsOwnerUi @Inject constructor(
        private val logger: Logger
) : Ui<ContactsOwnerModel>() {

    @BindView(R.id.contacts)
    lateinit var contactView: TextView

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(ContactsOwnerInitEvent())
    }

    override fun render(model: ContactsOwnerModel) {
        when (model.eventModel) {
            is ContactsOwnerInitEventModel -> contactView.text = model.eventModel.contactsResult
            is ContactsOwnerPushEventModel -> contactView.text = model.eventModel.contactsPushResult
            is ObserveContactsOwnerEventModel -> contactView.text = model.eventModel.toString()
        }
        logger.d("ContactsOwnerUi", "RENDER EXECUTED")
    }

    @OnClick(R.id.button)
    fun pushButton() {
        eventSource.onNext(RequestObserveContactsOwnerEvent())
    }
}
