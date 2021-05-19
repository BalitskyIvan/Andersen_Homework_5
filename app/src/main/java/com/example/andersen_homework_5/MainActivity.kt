package com.example.andersen_homework_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), ClickedItemHandler {

    private var frameLayout: FrameLayout? = null
    private var contactListFragment: ContactListFragment? = null
    private var contactsList: ArrayList<Contact>? = null

    companion object {
        const val CONTACTS_ITEM_EXTRA = "ITEM_EXTRA"
        const val CONTACTS_EXTRA = "CONTACTS_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayout = findViewById(R.id.fragmentContainerView)


        contactsList = initContactList()

        supportFragmentManager.apply {
            val transaction = beginTransaction()
            contactListFragment = ContactListFragment.newInstance(contactsList!!)
            transaction.replace(R.id.fragmentContainerView, contactListFragment!!)
            transaction.commit()
        }
    }

    override fun openContactDetails(contact: Contact) {
        supportFragmentManager.apply {
            val transaction = beginTransaction()
            val contactDetailFragment = ContactDetailsFragment.newInstance(contact)
            transaction.replace(R.id.fragmentContainerView, contactDetailFragment)
            transaction.addToBackStack(null);
            transaction.commit()
        }
    }

    override fun updateContact(contact: Contact) {
        for (c in contactsList!!)
            if (contact.viewId == c.viewId) {
                c.name = contact.name
                c.surname = contact.surname
                c.phone = contact.phone
            }
    }

    fun initContactList(): ArrayList<Contact> {
        val contactList = ArrayList<Contact>()
        contactList.add(
            Contact(
                R.id.contact_1,
                R.id.name_contact_1,
                R.id.phone_contact_1,
                "Константин",
                "Борисович",
                "09329302309"
            )
        )
        contactList.add(
            Contact(
                R.id.contact_2,
                R.id.name_contact_2,
                R.id.phone_contact_2,
                "Константин",
                "Борисович",
                "09329302309"
            )
        )
        contactList.add(
            Contact(
                R.id.contact_3,
                R.id.name_contact_3,
                R.id.phone_contact_3,
                "Константин",
                "Борисович",
                "09329302309"
            )
        )
        contactList.add(
            Contact(
                R.id.contact_4,
                R.id.name_contact_4,
                R.id.phone_contact_4,
                "Акакий",
                "Акакиевич",
                "88005553535"
            )
        )
        return contactList
    }
}