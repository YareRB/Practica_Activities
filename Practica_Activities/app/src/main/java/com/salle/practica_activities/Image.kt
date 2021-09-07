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
            Image(2, "Perro", info = Description.Perro),
            Image(3, "Android", info = Description.Android),
            Image(4, "Girafa", info = Description.Girafa),
            Image(5, "Linux", info = Description.Linux),
            Image(6, "Pez", info = Description.Pez),
            Image(7, "Windows", info = Description.Windows),
            Image(8, "Oso", info = Description.Oso),
            Image(9, "Netflix", info = Description.Netflix),
        )
    }
        fun setImage(id: Int): Image = images[id]
}