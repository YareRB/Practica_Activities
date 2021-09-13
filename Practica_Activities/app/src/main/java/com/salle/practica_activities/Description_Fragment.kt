package com.salle.practica_activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.moshi.Moshi


class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private var image: Image = Image()

    private val PREFS = "MY_PREFERENCES"
    private val IMAGE_PREFS = "IMAGE_PREFS"
    private lateinit var preferences: SharedPreferences //variable global

    private val moshi = Moshi.Builder().build() //JSON

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = activity?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)!! //cuales son preferencias

        image =  requireArguments().getParcelable("Image")!!

        InitView()
    }

    private lateinit var imgObjectD: ImageView
    private lateinit var imgFavorite: ImageView
    private lateinit var txvDescription: TextView

    private fun InitView(){

        imgObjectD = requireView().findViewById(R.id.imgObjectD)
        imgFavorite = requireView().findViewById(R.id.imgFavorite)
        txvDescription = requireView().findViewById(R.id.txvDescription)

        showInformationImage()
        listeners()
    }

    private fun showInformationImage() {
        imgObjectD.setImageResource(image.imageResource!!)
        txvDescription.setText(image.info!!)

        var imageFavorite : Int = getImagePreferences()
        //var soundFavorite : Int = getSoundsPreferences()

       if(imageFavorite == image.id){
            imgFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            imgFavorite.setImageResource(R.drawable.ic_no_favorite)
        }
    }

    private fun listeners() {
        imgObjectD.setOnClickListener() {
            replaceFragment(Full_Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Image", image)
                }
            })
        }

        imgFavorite.setOnClickListener(){
            var idFavorite : Int = getImagePreferences()
            if(idFavorite == image.id){
                //quita el fav
                saveImagePreference(-1)}
            else{
                saveImagePreference(image.id) //agrega el fav
            }
        }
    }

   private fun getImagePreferences() =
        preferences.getString(IMAGE_PREFS, null)?.let {
            return@let try {
                moshi.adapter(Int::class.java).fromJson(it) // guardar en json, y lo regresamos
            } catch (e: Exception) {
                -1
            }
        } ?: -1

    private fun saveImagePreference( value: Int? = -1) {

        preferences.edit().putString(IMAGE_PREFS, moshi.adapter(Int::class.java).toJson(value))
            .apply()//se apliquen los cambios

        showInformationImage()
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