package com.exemple.crossswords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exemple.crossswords.databinding.ActivityDojoBinding

class Dojo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: ActivityDojoBinding

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDojoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}