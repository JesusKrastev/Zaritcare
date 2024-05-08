package com.zaritcare.utilities.images

import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.icu.text.SimpleDateFormat
import android.os.Environment
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date

fun ImageBitmap.saveAsPdf(): Boolean {
    // Convierte ImageBitmap a Bitmap
    val fileName = "ZARITCAREPDF_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}_"
    val bitmap: Bitmap = this.asAndroidBitmap()

    // Crea un nuevo documento PDF
    val document = PdfDocument()

    // Crea una nueva página en el documento
    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
    val page = document.startPage(pageInfo)

    // Dibuja el bitmap en la página
    val canvas = page.canvas
    canvas.drawBitmap(bitmap, 0f, 0f, null)

    // Finaliza la página
    document.finishPage(page)

    // Guarda el documento PDF en el directorio de documentos del dispositivo
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    val file = File(directory, "$fileName.pdf")

    return try {
        val fileOutputStream = FileOutputStream(file)
        document.writeTo(fileOutputStream)
        document.close()
        fileOutputStream.close()
        true
    } catch (e: IOException) {
        Log.e("ImageBitmap", "Error al guardar el PDF: ${e.message}")
        false
    }
}