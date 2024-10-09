package com.exemple.crossswords

import ImageModifier
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.exemple.crossswords.databinding.ActivityCreateClanBinding



class CreateClan : AppCompatActivity() {
    private lateinit var binding: ActivityCreateClanBinding
    private var hakama = false
    private var kendogi = true
    private var simbol = false
    private var hakamaColor: Int? = null
    private var kendogiColor: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCreateClanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val samuraiImageView = binding.samuraianimation // Usando o binding para referenciar a ImageView
        samuraiImageView.setBackgroundResource(R.drawable.animation_default)
        var samuraiAnimation = samuraiImageView.background as AnimationDrawable
        samuraiAnimation.start()


        val typedArray = resources.obtainTypedArray(R.array.default_images)
        val imageIds = List(typedArray.length()) { typedArray.getResourceId(it, 0) }
        typedArray.recycle() // Não esqueça de reciclar o TypedArray

        binding.hexmagenta.setOnClickListener {

            // Chama o método de modificação de imagens
            if (hakama || kendogi) {
                // Para a animação atual
                samuraiAnimation.stop()

                // Atualiza as cores de hakama e kendogi
                hakamaColor = if (hakama) R.color.magenta else hakamaColor
                kendogiColor = if (kendogi) R.color.magenta else kendogiColor

                // Modifica as imagens com as novas cores
                ImageModifier.modifyImages(this, imageIds, hakamaColor, kendogiColor, true)

                // Reinicia a animação após a modificação das imagens
                samuraiImageView.invalidate()
                samuraiAnimation = samuraiImageView.background as AnimationDrawable
                samuraiAnimation.start()
            } else if (simbol) {
                // Atualiza a cor do ícone do clã
                binding.clanicon.imageTintList = ContextCompat.getColorStateList(this, R.color.magenta)
            }

            // Exibe um Toast para feedback
            Toast.makeText(this, "MAGENTA", Toast.LENGTH_SHORT).show()
        }



        binding.hexrosa.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexrosa.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexpurpura.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexpurpura.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexroxo.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexroxo.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexmarinho.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexmarinho.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexnaval.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexnaval.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexcinza.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexcinza.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexbordo.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexbordo.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexvermelho.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexvermelho.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexlaranja.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexlaranja.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexocre.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexocre.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexescuro.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexescuro.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexpetroleo.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexpetroleo.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexceleste.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexceleste.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexciano.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexciano.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexesmeralda.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexesmeralda.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexverde.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexverde.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexlima.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexlima.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexamarelo.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexamarelo.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexpreto.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexpreto.imageTintList.toString()}", Toast.LENGTH_LONG).show()}
        binding.hexbranco.setOnClickListener {Toast.makeText(this, "Button clicked: ${binding.hexbranco.imageTintList.toString()}", Toast.LENGTH_LONG).show()}


    }

}

