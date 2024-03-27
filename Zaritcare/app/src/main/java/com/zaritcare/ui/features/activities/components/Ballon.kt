package com.zaritcare.ui.features.activities.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zaritcare.R
import com.zaritcare.ui.theme.ZaritcareTheme

@Composable
private fun BallonImage(
    modifier: Modifier = Modifier,
    image: Painter
) {
    Image(
        modifier = modifier,
        painter = image,
        contentDescription = "ballon",
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun BallonTextField(
    modifier: Modifier = Modifier,
    stressText: String,
    placeholder: String = "Escribe lo que quieras",
    textSize: TextUnit = 20.sp,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = modifier,
            value = stressText,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = textSize
            ),
            singleLine = true,
            maxLines = 1
        )
        if (stressText.isEmpty()) {
            Text(
                text = placeholder,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = textSize
                )
            )
        }
    }
}

@Composable
fun BurstBallon(
    modifier: Modifier = Modifier,
    size: Size,
    stressText: String,
    onValueChange: (String) -> Unit
) {
    var isBurst: Boolean by remember { mutableStateOf(false) }
    val composition: LottieComposition? by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.burst_ballon_animation)
    )

    if (!isBurst) {
        Box(
            modifier = modifier
                .size(width = size.width.dp, height = size.height.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            isBurst = true
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            BallonImage(
                modifier = Modifier.matchParentSize(),
                image = painterResource(id = R.drawable.ballon)
            )
            BallonTextField(
                stressText = stressText,
                textSize = (size.width * 0.17 / 2).sp,
                onValueChange = onValueChange
            )
        }
    } else {
        LottieAnimation(
            modifier = Modifier.size(width = size.width.dp, height = size.height.dp),
            composition = composition
        )
    }
}

@Preview
@Composable
fun BallonPreview() {
    var stressText: String by remember { mutableStateOf("") }

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BurstBallon(
                    size = Size(200f, 300f),
                    stressText = stressText,
                    onValueChange = {
                        stressText = it
                    }
                )
            }
        }
    }
}