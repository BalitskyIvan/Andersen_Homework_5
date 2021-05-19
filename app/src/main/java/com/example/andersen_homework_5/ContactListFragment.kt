package com.example.andersen_homework_5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.andersen_homework_5.MainActivity.Companion.CONTACTS_EXTRA
import java.lang.RuntimeException

class ContactListFragment : Fragment() {

    private var contactList: ArrayList<Contact>? = null
    private lateinit var onButtonListener: ClickedItemHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        contactList = requireArguments().getParcelableArrayList(CONTACTS_EXTRA)

        onButtonListener = context as ClickedItemHandler

        if (contactList == null)
            throw RuntimeException("Fragment lifecycle error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (contact in contactList!!)
            initContact(contact, view)
    }

    fun initContact(contact: Contact, view: View) {
        view.findViewById<TextView>(contact.textViewNameId).text =
            contact.name + " " + contact.surname
        view.findViewById<TextView>(contact.textViewPhoneId).text = contact.phone
        view.findViewById<ConstraintLayout>(contact.viewId).setOnClickListener {
            onButtonListener.openContactDetails(contact)
        }
    }

    companion object {
        fun newInstance(contactList: ArrayList<Contact>): ContactListFragment {
            val contactListFragment = ContactListFragment()
            contactListFragment.arguments = Bundle().also {
                it.putParcelableArrayList(CONTACTS_EXTRA, contactList)
            }
            return contactListFragment
        }
    }
}