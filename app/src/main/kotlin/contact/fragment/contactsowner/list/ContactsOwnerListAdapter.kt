package contact.fragment.contactsowner.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import contact.R
import contact.api.model.contact.Contact
import contact.api.model.contact.OwnerContacts

class ContactsOwnerListAdapter(contactOwners: List<OwnerContacts>,
                               private val onClickLookLocation: (String, Int) -> Unit) :
        AbstractExpandableItemAdapter
        <ContactsOwnerListAdapter.ParentViewHolder,
                ContactsOwnerListAdapter.ChildViewHolder>() {

    private val myGroupItem = mutableListOf<MyGroupItem>()

    init {
        setHasStableIds(true)

        for (i in 0 until contactOwners.size) {
            val owner = contactOwners[i]

            val childItems = mutableListOf<MyChildItem>()
            childItems.add(MyChildItem(0.toLong(), Contact("Look location")))

            if (owner.contacts.size != 0) {
                for (j in 0 until owner.contacts.size) {
                    val contact = owner.contacts[j]
                    childItems.add(MyChildItem(j + 1.toLong(), contact))
                }
            }

            myGroupItem.add(MyGroupItem(i.toLong(),
                    owner, childItems)
            )
        }
    }

    override fun onCheckCanExpandOrCollapseGroup(holder: ParentViewHolder,
                                                 groupPosition: Int,
                                                 x: Int,
                                                 y: Int,
                                                 expand: Boolean): Boolean {
        return true
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from
        (parent?.context).inflate(R.layout.item_owner_contact,
                parent,
                false))
    }

    override fun onBindGroupViewHolder(holder: ParentViewHolder,
                                       groupPosition: Int,
                                       viewType: Int) {
        val owner = myGroupItem[groupPosition]
        holder.name.text = owner.ownerContacts.id
    }

    override fun getGroupId(groupPosition: Int): Long {
        return myGroupItem[groupPosition].id
    }

    override fun getGroupCount(): Int = myGroupItem.size


    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): ChildViewHolder {
        return ChildViewHolder(LayoutInflater.from
        (parent?.context).inflate(R.layout.item_contact,
                parent,
                false))
    }

    override fun onBindChildViewHolder(holder: ChildViewHolder,
                                       groupPosition: Int,
                                       childPosition: Int,
                                       viewType: Int) {
        val contact = myGroupItem[groupPosition].children[childPosition].contact
        holder.name.text = contact.name
        holder.phone.text = contact.phone

        if (childPosition == 0) {
            holder.itemView.setOnClickListener {
                onClickLookLocation(myGroupItem[groupPosition].ownerContacts.id,
                        myGroupItem[groupPosition].ownerContacts.locations.size)
            }
        }
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return myGroupItem[groupPosition].children[childPosition].id
    }

    override fun getChildCount(groupPosition: Int): Int = myGroupItem[groupPosition].children.size


    class ParentViewHolder(itemView: View) : AbstractExpandableItemViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.contact_item_name)!!
        val date = itemView.findViewById<TextView>(R.id.contact_last_update)!!
    }

    class ChildViewHolder(itemView: View) : AbstractExpandableItemViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.contact_item_name)!!
        val phone = itemView.findViewById<TextView>(R.id.contact_phone)!!
    }

    internal inner class MyGroupItem(val id: Long,
                                     val ownerContacts: OwnerContacts,
                                     val children: List<MyChildItem>)

    internal inner class MyChildItem(val id: Long, val contact: Contact)
}
