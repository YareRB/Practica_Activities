package com.salle.practica_activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val KEY = "STATE_KEY"
    private var principalFragment: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FRAGMENTS
        setContentView(R.layout.activity_main)

        principalFragment = savedInstanceState?.getBoolean(KEY, true) ?: true
        if (principalFragment)
            supportFragmentManager.beginTransaction().add(R.id.container, MainFragment()).commit()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean(KEY, false)
        }
        super.onSaveInstanceState(outState)
    }

    //ACTIVITY
    /*private lateinit var imgObject: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnNext: ImageView
    private lateinit var btnMoreInfo: Button

    private fun initView(){
        imgObject = findViewById(R.id.imgObject)
        btnBack = findViewById(R.id.btnBack)
        btnNext = findViewById(R.id.btnNext)
        btnMoreInfo = findViewById(R.id.btnMoreInfo)
        listeners()
    }

    private fun setImage(){
        image.setImage(image.id).let {
            image.apply {
                imagename = it.imagename
                info = it.info
            }
        }
        showImageNow()
    }

    private fun showImageNow() {
        imgObject.setImageResource(image.info!!.resource)
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
            startActivity(Intent(this, activity_description::class.java).apply {
                putExtra("Image", image)
            })
        }

        imgObject.setOnClickListener(){
            startActivity(Intent(this, activity_full::class.java).apply {
                putExtra("Image", image)
            })
        }
    }*/

}