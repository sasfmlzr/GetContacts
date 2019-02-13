package contact.fragment.contactsowner.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import contact.R
import contact.api.model.contact.OwnerContacts

class ContactsOwnerListAdapter constructor(private val onClickItem: (OwnerContacts) -> Unit) :
        ListAdapter<OwnerContacts, ContactsOwnerListAdapter.ViewHolder>
        (ContactsOwnerListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsOwnerListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ContactsOwnerListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contactsOwner: OwnerContacts, onClickItem: (OwnerContacts) -> Unit) {
            val name = itemView.findViewById<TextView>(R.id.contact_item_name)
            val date = itemView.findViewById<TextView>(R.id.contact_item_last_date)
            with(itemView) {
                name.text = contactsOwner.id
                setOnClickListener { onClickItem(contactsOwner) }
            }
        }
    }
}
