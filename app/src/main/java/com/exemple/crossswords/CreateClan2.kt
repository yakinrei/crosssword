package com.exemple.crossswords

import android.graphics.PorterDuff
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.exemple.crossswords.databinding.ActivityCreateClan2Binding

class CreateClan2 : AppCompatActivity() {
    private lateinit var binding: ActivityCreateClan2Binding
    private var color = "cinza"
//    private val sharedPreferences: SharedPreferences by lazy {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCreateClan2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa a animação padrão
        updateSamuraiAnimation(color)

        // Configura os listeners para cada cor
        setupColorClickListener(binding.hexmagenta, "magenta")
        setupColorClickListener(binding.hexrosa, "rosa")
        setupColorClickListener(binding.hexpurpura, "purpura")
        setupColorClickListener(binding.hexroxo, "roxo")
        setupColorClickListener(binding.hexmarinho, "marinho")
        setupColorClickListener(binding.hexnaval, "naval")
        setupColorClickListener(binding.hexcinza, "cinza")
        setupColorClickListener(binding.hexbordo, "bordo")
        setupColorClickListener(binding.hexvermelho, "vermelho")
        setupColorClickListener(binding.hexlaranja, "laranja")
        setupColorClickListener(binding.hexocre, "ocre")
        setupColorClickListener(binding.hexescuro, "floresta")
        setupColorClickListener(binding.hexpetroleo, "petroleo")
        setupColorClickListener(binding.hexceleste, "celeste")
        setupColorClickListener(binding.hexciano, "ciano")
        setupColorClickListener(binding.hexesmeralda, "esmeralda")
        setupColorClickListener(binding.hexverde, "verde")
        setupColorClickListener(binding.hexlima, "lima")
        setupColorClickListener(binding.hexamarelo, "amarelo")
        setupColorClickListener(binding.hexbranco, "branco")
        setupColorClickListener(binding.hexpreto, "preto")

        val icons = listOf(
            Pair(binding.bamboo, R.drawable.bamboo),
            Pair(binding.bonsai, R.drawable.bonsai),
            Pair(binding.wave, R.drawable.wave),
            Pair(binding.wind, R.drawable.wind),
            Pair(binding.flame, R.drawable.flame),
            Pair(binding.shuriken, R.drawable.shuriken),
            Pair(binding.fan, R.drawable.fan),
            Pair(binding.teapot, R.drawable.teapot),
            Pair(binding.sunrise, R.drawable.sunrise),
            Pair(binding.helmet, R.drawable.helmet),
            Pair(binding.lantern, R.drawable.lantern),
            Pair(binding.lotus, R.drawable.lotus),
            Pair(binding.oni, R.drawable.oni),
            Pair(binding.yunluo, R.drawable.yunluo),
            Pair(binding.trefoil, R.drawable.trefoil),
            Pair(binding.yinyang, R.drawable.yinyang),
            Pair(binding.fox, R.drawable.fox),
            Pair(binding.mouse, R.drawable.mouse),
            Pair(binding.spider, R.drawable.spider),
            Pair(binding.gecko, R.drawable.gecko)
        )

        icons.forEach { (button, iconResId) ->
            setupIconClickListener(button, iconResId)
        }



//        val editor = MainActivity.sharedPreferences.edit()
//        editor.putBoolean("newsamurai", false)
//        editor.apply()







    }

        private fun setupIconClickListener(button: View, iconResId: Int) {
            button.setOnClickListener {
                binding.clanicon.setImageResource(iconResId)
            }
        }




    private fun setupColorClickListener(button: View, colorName: String) {
        button.setOnClickListener {
            updateSamuraiAnimation(colorName)
            Toast.makeText(this, colorName.uppercase(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateSamuraiAnimation(color: String) {
        val drawableName = "${color}_animation_default"
        val drawableId = resources.getIdentifier(drawableName, "drawable", packageName)
        val drawable = ContextCompat.getDrawable(this, drawableId)
        // Supondo que você esteja dentro de uma Activity ou Fragment
        val colorid = resources.getIdentifier(color, "color", packageName)
        binding.vectorImage.setColorFilter(ContextCompat.getColor(this, colorid), PorterDuff.Mode.SRC_IN)
        if (color == "preto") {
            binding.clanicon.setColorFilter(ContextCompat.getColor(this, R.color.branco), PorterDuff.Mode.SRC_IN)
        } else {
            binding.clanicon.setColorFilter(ContextCompat.getColor(this, R.color.preto), PorterDuff.Mode.SRC_IN)
        }
        val samuraiImageView = binding.samuraianimation
        if (drawable != null) {
            val samuraiAnimation = drawable as AnimationDrawable
            samuraiAnimation.stop()
            samuraiImageView.setImageDrawable(null)
            samuraiImageView.setImageDrawable(drawable)
            samuraiAnimation.start()
        } else {
            Toast.makeText(this, "Drawable not found: $drawableName", Toast.LENGTH_SHORT).show()
        }
    }
}