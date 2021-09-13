package com.salle.practica_activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Full_Fragment : Fragment(R.layout.fragment_full_) {
    private var image: Image = Image()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image =  requireArguments().getParcelable("Image")!!

        InitView()
    }
    private lateinit var imgObjectFull: ImageView

    private fun InitView(){

        imgObjectFull = requireView().findViewById(R.id.imgObjectFull)

        imgObjectFull.setImageResource(image.imageResource!!)
    }

}