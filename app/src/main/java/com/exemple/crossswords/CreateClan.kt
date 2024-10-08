package com.exemple.crossswords


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.exemple.crossswords.databinding.ActivityCreateClanBinding


class CreateClan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: ActivityCreateClanBinding

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateClanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val samuraiImageView = findViewById<ImageView>(R.id.samuraianimation)
        samuraiImageView.setBackgroundResource(R.drawable.animation_default)
        val samuraiAnimation = samuraiImageView.background as AnimationDrawable
        samuraiAnimation.start()




    }
}