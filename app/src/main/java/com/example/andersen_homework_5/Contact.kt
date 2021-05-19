package com.example.andersen_homework_5

import android.os.Parcel
import android.os.Parcelable

data class Contact(
    val viewId: Int,
    val textViewNameId: Int,
    val textViewPhoneId: Int,
    var name: String?,
    var surname: String?,
    var phone: String?
) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(viewId)
        parcel.writeInt(textViewNameId)
        parcel.writeInt(textViewPhoneId)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }


}
