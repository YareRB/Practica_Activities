package com.salle.practica_activities

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi


class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private var image: Image = Image()

    private val PREFS = "MY_PREFERENCES"
    private val IMAGE_PREFS = "IMAGE_PREFS"
    private val SOUND_PREFS = "SOUNDS_FAV"

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
    private lateinit var  soundFavorite : ImageView

    private fun InitView(){

        imgObjectD = requireView().findViewById(R.id.imgObjectD)
        imgFavorite = requireView().findViewById(R.id.imgFavorite)
        txvDescription = requireView().findViewById(R.id.txvDescription)
        soundFavorite = requireView().findViewById(R.id.soundFavorite)

        showInformationImage()
        playSound(image.sound)
        listeners()
    }

    private fun showInformationImage() {
        imgObjectD.setImageResource(image.imageResource!!)
        txvDescription.setText(image.info!!)

        var imageFavorite : Int = getImagePreferences()
        var soundFav : Int = getSoundPreferences()

       if(imageFavorite == image.id){
            imgFavorite.setImageResource(R.drawable.ic_image_favorite)
           Toast.makeText(requireContext(), "Imagen guardada en favoritos", Toast.LENGTH_SHORT).show()

       }else{
            imgFavorite.setImageResource(R.drawable.ic_image_no_favorite)
        }
        if(soundFav == image.id){
            soundFavorite.setImageResource(R.drawable.ic_sound_favorite)
            Toast.makeText(requireContext(), "Sonido guardado en favoritos", Toast.LENGTH_SHORT).show()

            playSound(image.sound)
        }else{
            soundFavorite.setImageResource(R.drawable.ic_sound_no_favorite)
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

        soundFavorite.setOnClickListener(){
            var idFavorite : Int = getSoundPreferences()
                if(idFavorite == image.id){
                    saveSoundPreference(-1)}
                else{
                    saveSoundPreference(image.id) //agrega el fav
                }
        }
    }

    private fun playSound(sound: Int) = MediaPlayer.create(requireContext(), sound).start()

    private fun getSoundPreferences() =
        preferences.getString(SOUND_PREFS, null)?.let {
            return@let try {
                moshi.adapter(Int::class.java).fromJson(it) // guardar en json, y lo regresamos
            } catch (e: Exception) {
                -1
            }
        } ?: -1

    private fun saveSoundPreference( value: Int? = -1) {

        preferences.edit().putString(SOUND_PREFS, moshi.adapter(Int::class.java).toJson(value))
            .apply()

        showInformationImage()
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
            setCustomAnimations(
                R.anim.slide_in_right,
               // R.anim.slide_out_left,
               // R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            replace(R.id.container, imageFragment)
            addToBackStack(imageFragment.tag)
            commit()
        }
    }
}

