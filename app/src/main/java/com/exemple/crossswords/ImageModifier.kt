// ImageModifier.kt
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import org.bytedeco.javacv.*
import org.bytedecao.opencv.global.opencv_core.*
import org.bytedeco.opencv.global.opencv_imgcodecs.*
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.opencv_core.*

object ImageModifier {
    fun modifyImages(
        context: Context,
        imageResIds: IntArray,
        hakamaColor: Int? = null,
        kendogiColor: Int? = null
    ) {
        for (imageResId in imageResIds) {
            // Carregar a imagem
            val drawable: Drawable? = ContextCompat.getDrawable(context, imageResId)
            val img: Mat = drawableToMat(drawable)

            // Converter a imagem para o espaço de cores HSV
            val hsv = Mat()
            cvtColor(img, hsv, COLOR_BGR2HSV)

            // Modificar o hakama se a cor for fornecida
            hakamaColor?.let {
                // Definir o intervalo de cores para o hakama
                val lowerHakama = Scalar(0.0, 0.0, 100.0, 0.0)
                val upperHakama = Scalar(180.0, 50.0, 255.0, 0.0)

                // Criar uma máscara para o hakama
                val maskHakama = Mat()
                inRange(hsv, lowerHakama, upperHakama, maskHakama)

                // Aplicar a nova cor ao hakama
                val newColorHakama = Scalar(it.toDouble(), it.toDouble(), it.toDouble(), 0.0)
                img.setTo(newColorHakama, maskHakama)
            }

            // Modificar o kendogi se a cor for fornecida
            kendogiColor?.let {
                // Definir o intervalo de cores para o kendogi
                val lowerKendogi = Scalar(100.0, 50.0, 50.0, 0.0)
                val upperKendogi = Scalar(140.0, 255.0, 255.0, 0.0)

                // Criar uma máscara para o kendogi
                val maskKendogi = Mat()
                inRange(hsv, lowerKendogi, upperKendogi, maskKendogi)

                // Aplicar a nova cor ao kendogi
                val newColorKendogi = Scalar(it.toDouble(), it.toDouble(), it.toDouble(), 0.0)
                img.setTo(newColorKendogi, maskKendogi)
            }

            // Salvar a imagem resultante
            saveMatToFile(img, "caminho/para/imagem_modificada.png")
        }
    }

    // Método fictício para converter Drawable em Mat
    private fun drawableToMat(drawable: Drawable?): Mat {
        // Implementar a lógica de conversão
    }

    // Método fictício para salvar Mat em um arquivo
    private fun saveMatToFile(mat: Mat, path: String) {
        // Implementar a lógica de salvar
    }
}

//
//
//// SecondActivity.kt
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class SecondActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)
//
//        val imageResIds = resources.getStringArray(R.array.image_list).map { resources.getIdentifier(it, "drawable", packageName) }.toIntArray()
//        val hakamaColor = ContextCompat.getColor(this, R.color.hakama_color)
//
//        // Modificar apenas o hakama
//        ImageModifier.modifyImages(this, imageResIds, hakamaColor = hakamaColor)
//    }
//}
//
//// ThirdActivity.kt
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class ThirdActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_third)
//
//        val imageResIds = resources.getStringArray(R.array.image_list).map { resources.getIdentifier(it, "drawable", packageName) }.toIntArray()
//        val kendogiColor = ContextCompat.getColor(this, R.color.kendogi_color)
//
//        // Modificar apenas o kendogi
//        ImageModifier.modifyImages(this, imageResIds, kendogiColor = kendogiColor)
//    }
//}