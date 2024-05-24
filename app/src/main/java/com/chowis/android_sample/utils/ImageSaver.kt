package com.chowis.android_sample.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ImageSaver  {
    @RequiresApi(Build.VERSION_CODES.O)
    fun saveImage(context: Context, bitmap: Bitmap, captor: String): String {
        val current = LocalDateTime.now()
        val formattedDate = current.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val formattedTime = current.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val filePath = "Pictures/NEWCND/" + formattedDate
        val fileName = "newcnd_" + captor + "_" + formattedTime
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val imageCollection: Uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, fileName + ".jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.WIDTH, bitmap.width)
                    put(MediaStore.Images.Media.HEIGHT, bitmap.height)
                    put(MediaStore.Images.Media.RELATIVE_PATH, filePath)
                }
                context.contentResolver.insert(imageCollection, values)?.also {
                    context.contentResolver.openOutputStream(it).use { outputStream ->
                        if(!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream!!)) {
                            throw IOException("Failed to save image")
                        }
                    }
                } ?: throw IOException("Failed to create mediastore")
            } else {
                val outputStream: OutputStream
                if (!File(filePath).exists()) {
                    File(filePath).mkdirs()
                }
                val imageFile = File("$filePath/$fileName.jpg")
                try {
                    outputStream = FileOutputStream(imageFile)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: KotlinNullPointerException) {
                    e.printStackTrace()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "$filePath/$fileName"
    }
}