package com.salle.practica_activities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Image (
    var id: Int = 0,
    var imagename: String = "",
    var info: Description ? = null,
): Parcelable {
    companion object {
        val images = arrayOf(
            Image(0, "Cerdo", info = Description.Cerdo),
            Image(1, "Facebook", info = Description.Facebook),
            Image(2, "Android", info = Description.Android)
        )
    }
        fun setImage(id: Int): Image = images[id]
}