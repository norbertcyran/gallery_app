package com.example.photogallery

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class Image(val uri: Uri) : Parcelable {
    constructor(parcel: Parcel) : this(uri = parcel.readParcelable(Uri::class.java.classLoader)!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(uri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }

}
