package com.zaritcare.utilities.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.util.Base64
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import android.graphics.pdf.PdfDocument
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date

class Images {
    companion object {
        fun convertHardwareBitmapToSoftwareBitmap(imageBitmap: ImageBitmap?): ImageBitmap? {
            return try {
                val androidBitmap = imageBitmap!!.asAndroidBitmap()

                val byteArrayOutputStream = ByteArrayOutputStream()
                androidBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()

                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
            } catch (e: Exception) {
                Log.e("Images", "Error converting ImageBitmap to Bitmap: $e")
                null
            }
        }

        @Composable
        fun composeToBitmap(composable: @Composable () -> Unit): ImageBitmap {
            val context = LocalContext.current
            val view = ComposeView(context).apply {
                setContent {
                    composable()
                }
            }

            val bitmap = Bitmap.createBitmap(500, 1000, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)

            return bitmap.asImageBitmap()
        }

        fun base64ToAndroidBitmap(base64ImageString: String): Bitmap =
        Base64.decode(base64ImageString, Base64.DEFAULT).let { decodedString ->
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString!!.size)
        }

        fun base64ToBitmap(base64ImageString: String) =
            base64ToAndroidBitmap(base64ImageString).asImageBitmap()

        fun androidBitmapToBase64(bitmap: Bitmap): String = ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, this)
        }.use { stream ->
            Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT)
        }

        fun bitmapToBase64(bitmap: ImageBitmap): String =
            androidBitmapToBase64(bitmap.asAndroidBitmap())

        fun base64ToBitmap(biteArray: ByteArray?): ImageBitmap? =
            biteArray?.let {
                BitmapFactory.decodeByteArray(biteArray, 0, biteArray.size).asImageBitmap()
            }

        fun bitmapToBase64(bitmap: ImageBitmap?): ByteArray? =
            bitmap?.let {
                val stream = ByteArrayOutputStream()
                bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 70, stream)
                stream.toByteArray()
            }

        fun blobToBase64(value: ByteArray?): String? =
            value?.let { Base64.encodeToString(value, Base64.DEFAULT) }

        fun base64ToBlob(value: String?): ByteArray? =
            value?.let { Base64.decode(value, Base64.DEFAULT) }

        fun androidBitmapFromResourceId(recurso: Int, context: Context): Bitmap =
            BitmapFactory.decodeResource(context.resources, recurso)

        fun androidBitmapFromURI(uri: Uri, context: Context): Bitmap {
            val contextResolver = context.contentResolver
            val source = ImageDecoder.createSource(contextResolver, uri)
            return ImageDecoder.decodeBitmap(source)
        }

        fun bitmapFromURI(uri: Uri, context: Context) =
            androidBitmapFromURI(uri, context).asImageBitmap()
    }
}