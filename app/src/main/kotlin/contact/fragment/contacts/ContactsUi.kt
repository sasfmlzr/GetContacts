package contact.fragment.contacts

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
import javax.inject.Inject

class ContactsUi @Inject constructor(
        private val logger: Logger
) : Ui<ContactsModel>() {

    @BindView(R.id.contacts)
    lateinit var contactView: TextView

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(ContactsInitEvent())
    }

    override fun render(model: ContactsModel) {
        when (model.eventModel) {
            is ContactsInitEventModel -> contactView.text = model.eventModel.contactsResult
            is ContactsPushEventModel -> contactView.text = model.eventModel.contactsPushResult
            is ObserveContactsEventModel -> contactView.text = model.eventModel.toString()
        }
        logger.d("ContactsUi", "RENDER EXECUTED")
    }

    @OnClick(R.id.button)
    fun pushNutton() {
        eventSource.onNext(RequestObserveContactsEvent())
    //    eventSource.onNext(ContactsPushEvent())
    }
}
