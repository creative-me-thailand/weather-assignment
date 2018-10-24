package com.chanothai.assignment.presenter.model

import android.os.Parcel
import android.os.Parcelable

data class AvatarModel(
        val id: String,
        val name: String,
        val image: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AvatarModel> {
        override fun createFromParcel(parcel: Parcel): AvatarModel {
            return AvatarModel(parcel)
        }

        override fun newArray(size: Int): Array<AvatarModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class AvatarsModel(
        val avatars: ArrayList<AvatarModel>
)