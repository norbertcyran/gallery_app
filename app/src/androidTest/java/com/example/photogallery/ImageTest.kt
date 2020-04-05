package com.example.photogallery

import android.net.Uri
import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class ImageTest {
    private val uri: Uri = Uri.parse("testUri")
    lateinit var image: Image

    @Before fun createImage() {
        image = Image(uri)
    }

    @Test fun image_ParcelableWriteRead() {
        val parcel = Parcel.obtain()
        image.writeToParcel(parcel, image.describeContents())

        parcel.setDataPosition(0)

        val imageFromParcel = Image(parcel)

        assertThat(imageFromParcel.uri).isEqualTo(image.uri)
    }
}