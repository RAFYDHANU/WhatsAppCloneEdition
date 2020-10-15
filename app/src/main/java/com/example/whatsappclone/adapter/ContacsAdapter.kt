package com.example.whatsappclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.listener.ContacsClickListener
import com.example.whatsappclone.utils.Contact
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contacs.*

class ContacsAdapter(val contacs: ArrayList<Contact>):
    RecyclerView.Adapter<ContacsAdapter.ContactsViewHolder>() {

    private var clickListener: ContacsClickListener? = null

    class ContactsViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(contact: Contact, listener: ContacsClickListener?) {
            txt_contact_name.text = contact.name
            txt_contact_number.text = contact.phone
            itemView.setOnClickListener {
                listener?.onContactClicked(contact.name, contact.phone)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contacs, parent,false))

    override fun getItemCount() = contacs.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bindItem(contacs[position], clickListener)
    }

    fun setOnItemClickListener(listener: ContacsClickListener) {
        clickListener = listener
        notifyDataSetChanged()
    }
}