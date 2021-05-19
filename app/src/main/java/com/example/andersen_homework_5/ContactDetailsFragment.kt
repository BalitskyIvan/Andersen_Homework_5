package com.example.andersen_homework_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.andersen_homework_5.MainActivity.Companion.CONTACTS_ITEM_EXTRA
import java.lang.RuntimeException

class ContactDetailsFragment : Fragment() {

    private var contact: Contact? = null
    private lateinit var onButtonListener: ClickedItemHandler
    private var editName: EditText? = null
    private var editSurname: EditText? = null
    private var editPhone: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contact = requireArguments().getParcelable(CONTACTS_ITEM_EXTRA)

        onButtonListener = context as ClickedItemHandler

        if (contact == null)
            throw RuntimeException("Fragment lifecycle error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editName = view.findViewById(R.id.nameEditText)
        editSurname = view.findViewById(R.id.surnameEditText)
        editPhone = view.findViewById(R.id.phoneEditText)

        editName?.setText(contact?.name)
        editSurname?.setText(contact?.surname)
        editPhone?.setText(contact?.phone)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            updateContact()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        updateContact()
    }

    private fun updateContact() {
        contact?.name = editName?.text.toString()
        contact?.surname = editSurname?.text.toString()
        contact?.phone = editPhone?.text.toString()
        onButtonListener.updateContact(contact!!)
    }

    companion object {
        fun newInstance(contact: Contact): ContactDetailsFragment {
            val contactDetailsFragment = ContactDetailsFragment()
            contactDetailsFragment.arguments = Bundle().also {
                it.putParcelable(CONTACTS_ITEM_EXTRA, contact)
            }
            return contactDetailsFragment
        }
    }
}