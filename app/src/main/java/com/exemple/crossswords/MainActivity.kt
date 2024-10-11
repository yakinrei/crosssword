package com.exemple.crossswords

import android.Manifest
import android.animation.ObjectAnimator
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.exemple.crossswords.databinding.ActivityMainBinding
import android.content.SharedPreferences


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView
    private lateinit var image4: ImageView
    private lateinit var image5: ImageView
    private lateinit var image6: ImageView
    lateinit var sharedPreferences: SharedPreferences


    private val handler = android.os.Handler()
    private var offset = 0
    private var offsetfar = 0
    private var offsetclose = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        image1 = findViewById(R.id.small1)
        image2 = findViewById(R.id.small2)
        image3 = findViewById(R.id.farfaraway)
        image4 = findViewById(R.id.farfaraway2)
        image5 = findViewById(R.id.close1)
        image6 = findViewById(R.id.close2)



        sharedPreferences = getSharedPreferences("nome_do_prefs", Context.MODE_PRIVATE)

        // Lendo o valor
        val newsamurai = sharedPreferences.getBoolean("newsamurai", true)



        val animator = ObjectAnimator.ofFloat(binding.startButton, "alpha", 1f, 0f, 1f)
        animator.duration = 3000 // Duração total da animação
        animator.repeatCount = ObjectAnimator.INFINITE // Repetir infinitamente
        animator.repeatMode = ObjectAnimator.REVERSE // Voltar ao estado original
        animator.start()

        startAnimation()
        checkLocationPermissions()
        createNotificationChannel() // Criar canal de notificação
        createNotification()

        binding.startButton.setOnClickListener {
            if (newsamurai == true) {
                val intent = Intent(this, CreateClan2::class.java)
                intent.putExtra("newsamurai", newsamurai)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, Dojo::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Parar a animação
    }

    private fun checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            onLocationPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onLocationPermissionGranted()
            } else {
                onLocationPermissionDenied()
            }
        }
    }

    private fun onLocationPermissionGranted() {
        // Implementar lógica quando a permissão de localização for concedida
    }

    private fun onLocationPermissionDenied() {
        // Implementar lógica de fallback ou aviso ao usuário
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel("your_channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, "your_channel_id")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Título da Notificação")
            .setContentText("Texto da Notificação")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun startAnimation() {
        handler.post(object : Runnable {
            override fun run() {
                val speed = 5 // Velocidade base
                offset += speed / 2// Velocidade para imagens "small"
                offsetfar += speed // Velocidade reduzida para imagens "farfar"
                offsetclose += speed * 4 // Aumentando a velocidade para imagens "close"

                image1.translationX = -offset.toFloat()
                image2.translationX = -offset.toFloat()
                image3.translationX = -offsetfar.toFloat()
                image4.translationX = -offsetfar.toFloat()
                image5.translationX = -offsetclose.toFloat()
                image6.translationX = -offsetclose.toFloat()

                // Reseta o offset quando a imagem sai da tela
                if (image1.translationX <= -image1.width) offset = 0
                if (image2.translationX <= -image2.width) offset = 0
                if (image3.translationX <= -image3.width) offsetfar = 0
                if (image4.translationX <= -image4.width) offsetfar = 0
                if (image5.translationX <= -image5.width) offsetclose = 0
                if (image6.translationX <= -image6.width) offsetclose = 0


                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                handler.postDelayed(this, 16)
            }
        })
    }


//    override fun onResume() {
//        super.onResume()
//    }
//    override fun onPause() {
//        super.onPause()
//    }
//    override fun onStop() {
//        super.onStop()
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//    override fun onRestart() {
//        super.onRestart()
//    }
//    override fun onStart() {
//        super.onStart()
//    }

}
