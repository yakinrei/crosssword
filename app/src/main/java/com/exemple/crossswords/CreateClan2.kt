package com.exemple.crossswords

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.exemple.crossswords.databinding.ActivityCreateClanBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class CreateClan2 : AppCompatActivity() {
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

        var drawable = ContextCompat.getDrawable(this, R.drawable.animation_default)
        var samuraiImageView = binding.samuraianimation // Usando o binding para referenciar a ImageView
        samuraiImageView.setImageDrawable(drawable)
        var samuraiAnimation = drawable as AnimationDrawable
        samuraiAnimation.start()

        val typedArray = resources.obtainTypedArray(R.array.default_images)
        val imageIds = List(typedArray.length()) { typedArray.getResourceId(it, 0) }
        typedArray.recycle() // Não esqueça de reciclar o TypedArray

        binding.hexmagenta.setOnClickListener {
            // Chama o método de modificação de imagens
            if (hakama || kendogi) {
                samuraiAnimation.stop()

                modifyImages(this, imageIds, hakama, true)

                hakamaColor = if (hakama) R.color.magenta else hakamaColor
                kendogiColor = if (kendogi) R.color.magenta else kendogiColor


                samuraiImageView.setImageDrawable(null)


                drawable = ContextCompat.getDrawable(this, R.drawable.p1_animedefault_0)
                samuraiImageView.setImageDrawable(drawable)
//                samuraiAnimation = drawable as AnimationDrawable
//                samuraiAnimation.start()
                // Se precisar alternar entre imagens, você pode fazer isso aqui
                // Por exemplo, se você quiser mostrar a próxima imagem:
                // val nextDrawable = ContextCompat.getDrawable(this, R.drawable.p1_animedefault_1)
                // samuraiImageView.setImageDrawable(nextDrawable)

                // Reinicia a animação se necessário
                // samuraiAnimation.start() // Se você tiver uma animação
            } else if (simbol) {
                // Atualiza a cor do ícone do clã
                binding.clanicon.imageTintList =
                    ContextCompat.getColorStateList(this, R.color.magenta)
            }

            // Exibe um Toast para feedback
            Toast.makeText(this, "MAGENTA", Toast.LENGTH_SHORT).show()
        }

}




    fun modifyImages(
        context: Context,
        imageIds: List<Int>,
        hakama: Boolean,
        p1: Boolean
    ) {
        val jacketColor = if (hakama) {
            Color.parseColor("#AFAFAF") // Cor predefinida em hexadecimal
        } else {
            Color.parseColor("#142168") // Segunda cor (vermelho)
        }

        for (imageId in imageIds) {
            // Carregar a imagem a partir do recurso
            val bitmap = BitmapFactory.decodeResource(context.resources, imageId) ?: continue

            // Percorrer os pixels da imagem
            val largura = bitmap.width
            val altura = bitmap.height
            for (y in 0 until altura) {
                for (x in 0 until largura) {
                    val pixel = bitmap.getPixel(x, y)
                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)
                    // Supondo que a jaqueta seja de uma cor específica (ex.: azul)
                    if (r == 0 && g == 0 && b == 255) { // Azul
                        bitmap.setPixel(x, y, jacketColor)
                    }
                }
            }

            // Preparar o caminho para salvar a imagem alterada
            val fileName = resources.getResourceEntryName(imageId)
            val prefix = if (p1) "p1_" else "p2_"
            val outputImagePath = "$prefix$fileName.png"
            Toast.makeText(this, "ARQUIVO ${outputImagePath}", Toast.LENGTH_SHORT).show()

            // Salvar a imagem alterada
            try {
                val file = File(outputImagePath)
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                Toast.makeText(this, "SALVOU ${file}", Toast.LENGTH_SHORT).show()
                outputStream.flush()
                outputStream.close()
            } catch (e: IOException) {
                Toast.makeText(this, "NÃO SALVOU PORRA NENHUMA", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}