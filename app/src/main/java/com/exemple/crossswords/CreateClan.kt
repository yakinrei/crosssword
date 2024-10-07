package com.exemple.crossswords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exemple.crossswords.databinding.ActivityCreateClanBinding

class CreateClan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: ActivityCreateClanBinding

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateClanBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}