package contact.fragment.contactsowner.list

import androidx.recyclerview.widget.DiffUtil
import contact.api.model.contact.OwnerContacts

class ContactsOwnerListDiffUtil : DiffUtil.ItemCallback<OwnerContacts>() {
    override fun areItemsTheSame(p0: OwnerContacts, p1: OwnerContacts): Boolean {
        return p0 == p1
    }

    override fun areContentsTheSame(p0: OwnerContacts, p1: OwnerContacts): Boolean {
        return p0 == p1
    }
}
