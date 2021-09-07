package com.salle.practica_activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class activity_description : AppCompatActivity() {

    private var image: Image = Image()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

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
    }


    private fun listeners() {
        imgObjectD.setOnClickListener() {
            startActivity(Intent(this, activity_full::class.java).apply {
                putExtra("Image", image)
            })
        }
    }


}