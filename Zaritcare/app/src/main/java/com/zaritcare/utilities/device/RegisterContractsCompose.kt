package com.zaritcare.utilities.device

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun resizePhoto(
    bitmap: Bitmap,
    ladoLargo: Int
): Bitmap {
    val w = bitmap.width
    val h = bitmap.height
    var newW: Int
    var newH: Int
    if (w > h) {
        val aspRat = h.toFloat() / w.toFloat()
        newW = ladoLargo
        newH = (newW * aspRat).toInt()
    } else {
        val aspRat = w.toFloat() / h.toFloat()
        newH = ladoLargo
        newW = (newH * aspRat).toInt()
    }
    return  Bitmap.createScaledBitmap(bitmap, newW, newH, false)
}

private fun Context.toImageBitmap(uri: Uri): ImageBitmap {
    val PREFERRED_IMAGE_SIZE = 300
    val ONE_MB_TO_KB = 1024
    val baos = ByteArrayOutputStream()
    val contextResolver = this.contentResolver
    val source = ImageDecoder.createSource(contextResolver, uri)
    var selectedImageBitMap =  ImageDecoder.decodeBitmap(source)
    selectedImageBitMap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
    if (baos.toByteArray().size / ONE_MB_TO_KB > PREFERRED_IMAGE_SIZE) {
        selectedImageBitMap = resizePhoto(
            bitmap = selectedImageBitMap,
            ladoLargo = PREFERRED_IMAGE_SIZE
        )
    }
    return selectedImageBitMap.asImageBitmap()
}

// Uso:
//      selectorDeImagenes.launch("image/*")
@Composable
fun registerImageSelectorWithGetContent(
    onChangePhoto: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            onChangePhoto(context.toImageBitmap(uri))
        }
    }
}

@Composable
fun registerGoogleLauncher(
    signInWithGoogle: (idToken: String, onNavigateToResults: () -> Unit, onNavigateToSplash: () -> Unit) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToSplash: () -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                signInWithGoogle(account.idToken!!, onNavigateToResults, onNavigateToSplash)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("GoogleLauncher", "Error: ${e.message}")
            }
        } else {
            Log.d("GoogleLauncher", "Error: ${result.resultCode}")
        }
    }
}

// Para hacer fotos con TakePicture, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.camera.any"
//      android:required="true" />
// <uses-permission android:name="android.permission.CAMERA" />
//
// En el manifest, dentro de <application>:
// <provider
//     android:name="androidx.core.content.FileProvider"
//     android:authorities="${applicationId}.provider"
//     android:exported="false"
//     android:grantUriPermissions="true">
//     <meta-data
//         android:name="android.support.FILE_PROVIDER_PATHS"
//         android:resource="@xml/path_provider" />
// </provider>
//
// En res/xml/path_provider.xml:
// <?xml version="1.0" encoding="utf-8"?>
// <paths>
//     <external-cache-path
//         name="my_images"
//         path="/"/>
// </paths>
// Uso:
//      hacerFoto.launch(android.Manifest.permission.CAMERA)

@Composable
fun registerTakePhotoWithTakePicture(
    onChangePhoto: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {

    val context = LocalContext.current
    val ficheroTemporal = File.createTempFile(
        "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}_",
        ".jpg",
        context.externalCacheDir
    )
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        ficheroTemporal
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                onChangePhoto(context.toImageBitmap(uri))
            }
        }
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            cameraLauncher.launch(uri)
        }
    }
}

// Para hacer fotos con el intent de la cámara, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.camera.any"
//      android:required="true" />
// <uses-permission android:name="android.permission.CAMERA" />
// Uso:
//      hacerFoto.launch(android.Manifest.permission.CAMERA)
@Composable
fun registerTakePhotoWithIntent(
    onChangePhoto: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val androidBitmap = result.data?.extras?.get("data") as Bitmap
                onChangePhoto(androidBitmap!!.asImageBitmap())
            }
        }

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
            cameraLauncher.launch(cameraIntent)
        }
    }
}

// Para hacer llamadas telefónicas, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.telephony"
//      android:required="true" />
// <uses-permission android:name="android.permission.CALL_PHONE"/>
// Uso:
//      telefono.launch(android.Manifest.permission.CALL_PHONE)
@Composable
fun registerCallByPhoneIntent(
    telefono: String
): ManagedActivityResultLauncher<String, Boolean> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            Intent(Intent.ACTION_CALL).also { callIntent ->
                callIntent.data = Uri.parse("tel:$telefono")
                context.startActivity(callIntent)
            }
        }
    }
}

fun Context.sendEmail(
    email: String,
    subject: String = "Sin asunto",
    text: String = "Sin texto",
    forceChoice: Boolean = false
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    val chooser = if (forceChoice) {
        Intent.createChooser(intent, "Enviar correo")
    } else null

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(chooser ?: intent)
    }
}

// Para hacer llamadas telefónicas, hay que añadir en el manifest:
// <uses-permission android:name="android.permission.READ_CONTACTS/>
// Uso:
//      val registroSeleccionContacto
//          = registroSelectorTelefonoContacto { telefono ->
//              ...
//      }
//      ...
//      registroSeleccionContacto.launch(android.Manifest.permission.READ_CONTACTS)

@Composable
fun registerSelectorPhoneContact(
    onSelectContactNumber: (String) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {

    val contexto = LocalContext.current
    val registerGetPhoneNumber = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val contactUri: Uri? = result.data?.data
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if (contactUri != null) {
                    contexto.contentResolver.query(contactUri, projection, null, null, null)
                        .use { cursor ->
                            if (cursor != null && cursor.moveToFirst()) {
                                val numberIndex =
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                val number =
                                    if (numberIndex >= 0) cursor.getString(numberIndex) else "NO NUMBER"
                                onSelectContactNumber(number)
                            }
                        }
                }
            }
        })

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            registerGetPhoneNumber.launch(intent)
        }
    }
}
