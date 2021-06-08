package com.somethingsimple.poplibs.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import com.somethingsimple.poplibs.data.model.ImageToConvert
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ImageConverter(private val context: Context) {
    var mImage: ImageToConvert? = null


    fun convertImage(outputFilename: String, quality: Int): Single<ImageToConvert?> =
        getImageInputStreamFromUri()
            .subscribeOn(Schedulers.io())
            .flatMap {
                val file = File(
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    outputFilename
                )
                mImage?.apply {
                    bitmap = BitmapFactory
                        .decodeStream(it)
                        .compressToFile(
                            file,
                            Bitmap.CompressFormat.PNG,
                            quality
                        )
                    uri = Uri.fromFile(file)
                }
                return@flatMap Single.just(
                    mImage
                )
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getImageInputStreamFromUri() =
        Single.fromCallable {
            mImage?.uri?.let { it ->
                context.contentResolver.openInputStream(
                    it
                )
            }
        }
}

// Extension function to compress and change bitmap image format programmatically
fun Bitmap.compressToFile(
    file: File,
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): Bitmap {
    val stream = ByteArrayOutputStream()
    this.compress(format, quality, stream)
    val bitmapData = stream.toByteArray()
    FileOutputStream(file).use { fileOutputStream ->
        fileOutputStream.write(bitmapData)
    }
    return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
}




