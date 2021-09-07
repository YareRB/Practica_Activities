package com.salle.practica_activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.moshi.Moshi

class activity_description : AppCompatActivity() {

    private var image: Image = Image()

    private val PREFS = "MY_PREFERENCES"
    private val IMAGE_PREFS = "IMAGE_PREFS"
    private lateinit var preferences: SharedPreferences //variable global

    private val moshi = Moshi.Builder().build() //JSON

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        preferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE) //cuales son preferencias

        InitView()
    }

     private lateinit var imgObjectD: ImageView
     private lateinit var imgFavorite: ImageView
     private lateinit var txvDescription: TextView

     private fun InitView(){

         imgObjectD = findViewById(R.id.imgObjectD)
         imgFavorite = findViewById(R.id.imgFavorite)
         txvDescription = findViewById(R.id.txvDescription)

         image = intent.getParcelableExtra("Image")?: Image()

         showInformation()
         listeners()
     }

    private fun showInformation() {
        imgObjectD.setImageResource(image.info!!.resource)
        txvDescription.setText(image.info!!.description)

        var imageFavorite : Int = getPreferences()
        if(imageFavorite == image.id){
            imgFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            imgFavorite.setImageResource(R.drawable.ic_no_favorite)
        }
    }


    private fun listeners() {
        imgObjectD.setOnClickListener() {
            startActivity(Intent(this, activity_full::class.java).apply {
                putExtra("Image", image)
            })
        }

        imgFavorite.setOnClickListener(){
            var idFavorite : Int = getPreferences()
            if(idFavorite == image.id){
                //quita el fav
                savePreference(-1)}
            else{
                savePreference(image.id) //agrega el fav
            }
        }
    }

    private fun getPreferences() =
        preferences.getString(IMAGE_PREFS, null)?.let {
            return@let try {
                moshi.adapter(Int::class.java).fromJson(it) // guardar en json, y lo regresamos
            } catch (e: Exception) {
                -1
            }
        } ?: -1

    private fun savePreference( value: Int? = -1) {

        preferences.edit().putString(IMAGE_PREFS, moshi.adapter(Int::class.java).toJson(value))
            .apply()//se apliquen los cambios

        showInformation()
    }
}