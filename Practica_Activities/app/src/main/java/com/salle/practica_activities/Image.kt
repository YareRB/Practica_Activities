package com.salle.practica_activities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Image (
    var id: Int = 0,
    var imagename: String = "",
    var imageResource : Int = 0,
    var info:  Int = 0,
    var sound : Int = 0
): Parcelable {
    companion object {
        val images = arrayOf(
            Image(0, "Cerdo", R.drawable.cerdo, R.string.text_cerdo, R.raw.sound_cerdo),
            Image(1, "Facebook", R.drawable.ic_facebook, R.string.text_facebook,R.raw.sound_facebook),
            Image(2, "Perro", R.drawable.perro, R.string.text_perro, R.raw.sound_perro),
            Image(3, "Android", R.drawable.ic_android, R.string.text_android, R.raw.sound_android),
            Image(4, "Girafa", R.drawable.girafa, R.string.tetx_girafa, R.raw.sound_girafa),
            Image(5, "Linux", R.drawable.ic_linux, R.string.text_linux, R.raw.sound_linux),
            Image(6, "Pez", R.drawable.pez, R.string.text_pez, R.raw.sound_pez),
            Image(7, "Windows", R.drawable.ic_windows, R.string.text_windows, R.raw.sound_windows),
            Image(8, "Oso", R.drawable.oso, R.string.text_oso,R.raw.sound_oso),
            Image(9, "Netflix", R.drawable.ic_netflix, R.string.text_netflix, R.raw.sound_netflix),
        )
    }
        fun setImage(id: Int): Image = images[id]
}