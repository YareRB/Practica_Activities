package com.salle.practica_activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.moshi.Moshi
import android.media.MediaPlayer
import android.widget.Toast


class MainFragment : Fragment(R.layout.fragment_main) {

    private var image: Image = Image()

    private val PREFS = "MY_PREFERENCES"
    private val SOUND_PREFS = "SOUNDS_FAV"

    private val ACTUAL_IMAGE = "ACTUAL"
    private lateinit var preferences: SharedPreferences

    private val moshi = Moshi.Builder().build() //JSON

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = activity?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)!!
        initView()

    }

    private lateinit var imgObject: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: ImageView
    private lateinit var playSound : ImageView
    private lateinit var btnMoreInfo: Button

    private fun initView(){

        imgObject = requireView().findViewById(R.id.imgObject)
        btnBack = requireView().findViewById(R.id.btnBack)
        btnNext = requireView().findViewById(R.id.btnNext)
        btnMoreInfo = requireView().findViewById(R.id.btnMoreInfo)
        playSound = requireView().findViewById(R.id.playSound)

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
        imgObject.setImageResource(image.imageResource)
    }

    private fun listeners() {
        btnBack.setOnClickListener() {
            if (image.id > 0) {
                image.id--
                setImage()
            } else {
                image.id = 9
                setImage()
            }
        }

        btnNext.setOnClickListener() {
            if (image.id < 9) {
                image.id++
                setImage()
            } else {
                image.id = 0
                setImage()
            }
        }

        btnMoreInfo.setOnClickListener {

            replaceFragment(DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Image", image)
                }
            })
        }

        imgObject.setOnClickListener() {
            replaceFragment(Full_Fragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Image", image)
                }
            })
        }

        playSound.setOnClickListener(){
            var Sounds = Image.images
            var soundFavorite = getSoundPreferences()

            if(soundFavorite >-1 && soundFavorite < 11)
            { playSound(Sounds[soundFavorite].sound)
                Toast.makeText(requireContext(), "El sonido favorito es de la imagen llamada: " +
                        "${Sounds[soundFavorite].imagename}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playSound(sound: Int) = MediaPlayer.create(requireContext(), sound).start()

    private fun replaceFragment(imageFragment: Fragment) {

            activity?.supportFragmentManager?.beginTransaction()?.apply  {
                setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right
                )
                replace(R.id.container, imageFragment)
                addToBackStack(imageFragment.tag)
                commit()
            }
    }

    private fun getSoundPreferences() =
        preferences.getString(SOUND_PREFS, null)?.let {
            return@let try {
                moshi.adapter(Int::class.java).fromJson(it) // guardar en json, y lo regresamos
            } catch (e: Exception) {
                -1
            }
        } ?: -1

}