import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.CountDownLatch


object ImageModifier {

    // Cores a serem substituídas
    private const val ORIGINAL_HAKAMA_COLOR = 0xFFAFAFAF.toInt() // Cor original do hakama (cinza)
    private const val ORIGINAL_KENDOGI_COLOR = 0xFF142168.toInt() // Cor original do kendogi (azul)



    suspend fun modifyImages(
        context: Context,
        imagesid: List<Int>,
        hakamaColor: Int? = null,
        kendogiColor: Int? = null,
        p1: Boolean
    ) {
        Toast.makeText(context, "INICIANDO MODIFICAÇÕES", Toast.LENGTH_SHORT).show()

        // Usando CountDownLatch para aguardar todas as operações de carregamento
        val latch = CountDownLatch(imagesid.size)

        for (id in imagesid) {
            Log.d("ImageModifier", "Carregando imagem")
            Glide.with(context)
                .asBitmap()
                .load(id)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val modifiedBitmap = replaceColorsInBitmap(resource, hakamaColor, kendogiColor)
                        val resourceName = context.resources.getResourceEntryName(id)
                        val prefix = if (p1) "p1_" else "p2_"
                        val fileName = "$prefix${resourceName}"
                        saveBitmapToFile(context, modifiedBitmap, fileName)

                        // Contar que esta operação foi concluída
                        latch.countDown()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Implementação vazia (opcional)
                    }
                })
        }

        // Aguardar até que todas as imagens tenham sido processadas
        withContext(Dispatchers.IO) {
            latch.await()
        }

        // Aqui você pode adicionar qualquer código que precisa ser executado após todas as modificações
        Toast.makeText(context, "TODAS AS IMAGENS FORAM MODIFICADAS", Toast.LENGTH_SHORT).show()
    }

    // Função para substituir as cores específicas no Bitmap
    private fun replaceColorsInBitmap(bitmap: Bitmap, hakamaColor: Int?, kendogiColor: Int?): Bitmap {
        val modifiedBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        // Percorrendo cada pixel do bitmap
        for (x in 0 until modifiedBitmap.width) {
            for (y in 0 until modifiedBitmap.height) {
                val pixelColor = modifiedBitmap.getPixel(x, y)

                // Se for a cor do hakama (#AFAFAF), substituir pela nova cor
                if (pixelColor == ORIGINAL_HAKAMA_COLOR && hakamaColor != null) {
                    modifiedBitmap.setPixel(x, y, hakamaColor)
                }

                // Se for a cor do kendogi (#142168), substituir pela nova cor
                if (pixelColor == ORIGINAL_KENDOGI_COLOR && kendogiColor != null) {
                    modifiedBitmap.setPixel(x, y, kendogiColor)
                }
            }
        }

        return modifiedBitmap
    }

    // Função para salvar o Bitmap modificado em um arquivo
    private fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String) {
        val file = File(context.getExternalFilesDir(null), fileName)
        var fos: FileOutputStream? = null
        Toast.makeText(context, fileName, Toast.LENGTH_SHORT).show()
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            //fos.flush()
            Toast.makeText(context, "PARANAUE SALVO", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "NÃO SALVOU O PARANAUE", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                Toast.makeText(context, "NÃO SALVOU O PARANAUE 2", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}
