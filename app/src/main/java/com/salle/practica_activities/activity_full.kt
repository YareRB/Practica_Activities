package com.salle.practica_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class activity_full : AppCompatActivity() {

    private var image: Image = Image()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full)
        InitView()
    }

    private lateinit var imgObjectFull: ImageView

    private fun InitView(){

        imgObjectFull = findViewById(R.id.imgObjectFull)

        image = intent.getParcelableExtra("Image")?: Image()

        imgObjectFull.setImageResource(image.info!!.resource)
    }



}