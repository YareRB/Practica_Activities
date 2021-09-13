package com.salle.practica_activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi


class MainFragment : Fragment(R.layout.fragment_main) {

    private var image: Image = Image() //imagen actual
    private var soundFavorite : Image = Image() // sonido favorito

    //private val PREFS = "MY_PREFERENCES"
    //private val SOUND_PREFS = "SOUNDS_FAV"
    //private lateinit var preferences: SharedPreferences

    private val moshi = Moshi.Builder().build() //JSON

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //preferences = activity?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)!! //cuales son preferencias

        initView()
    }


    //ACTIVITY
    private lateinit var imgObject: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: ImageView
    private lateinit var btnMoreInfo: Button

    private fun initView(){

        imgObject = requireView().findViewById(R.id.imgObject)
        btnBack = requireView().findViewById(R.id.btnBack)
        btnNext = requireView().findViewById(R.id.btnNext)
        btnMoreInfo = requireView().findViewById(R.id.btnMoreInfo)

        setImage()
        listeners()
    }

    private fun setImage(){
        image.setImage(image.id).let {
            image.apply {
                imagename = it.imagename
                info = it.info
                imageResource = it.imageResource
                sound = it.sound
            }
        }
        showImageNow()
    }

    private fun showImageNow() {
        imgObject.setImageResource(image.imageResource!!)
    }

    private fun listeners(){
        btnBack.setOnClickListener(){
            if(image.id > 0){
                image.id--
                setImage()
            }else {
                image.id = 9
                setImage()
            }
        }

        btnNext.setOnClickListener(){
            if(image.id < 9){
                image.id++
                setImage()
            }else {
                image.id = 0
                setImage()
            }
        }

        btnMoreInfo.setOnClickListener{

            replaceFragment(DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Image", image)
                }
            })
        }

        imgObject.setOnClickListener(){

        }
    }

    private fun replaceFragment(imageFragment: Fragment) {

            activity?.supportFragmentManager?.beginTransaction()?.apply  {
                /*setCustomAnimations(
                    R.anim.slide_in_right,
                   // R.anim.slide_out_left,
                   // R.anim.slide_in_left,
                    R.anim.slide_out_right
                )*/
                replace(R.id.container, imageFragment)
                addToBackStack(imageFragment.tag)
                commit()
            }
    }
}