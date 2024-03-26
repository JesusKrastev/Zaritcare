package com.zaritcare.ui.composables

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.zaritcare.ui.theme.ZaritcareTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    initialValue:Int,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor:Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    text: String,
    minValue:Int = 0,
    enableDragging: Boolean = true,
    maxValue:Int = 60,
    circleRadius:Float,
    onPositionChange:(Int)->Unit
) {
    var circleCenter: Offset by remember { mutableStateOf(Offset.Zero) }
    var positionValue: Int by remember { mutableIntStateOf(initialValue) }
    var changeAngle: Float by remember { mutableFloatStateOf(0f) }
    var dragStartedAngle: Float by remember { mutableFloatStateOf(0f) }
    var oldPositionValue: Int by remember { mutableIntStateOf(initialValue) }

    Box(
        modifier = modifier
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true){
                    detectDragGestures(
                        onDragStart = {offset ->
                            if(enableDragging) {
                                dragStartedAngle = -atan2(
                                    x = circleCenter.y - offset.y,
                                    y = circleCenter.x - offset.x
                                ) * (180f / PI).toFloat()
                                dragStartedAngle = (dragStartedAngle + 180f).mod(360f)
                            }
                        },
                        onDrag = { change, _ ->
                            var touchAngle: Float = -atan2(
                                x = circleCenter.y - change.position.y,
                                y = circleCenter.x - change.position.x
                            ) * (180f / PI).toFloat()
                            touchAngle = (touchAngle + 180f).mod(360f)

                            val currentAngle: Float = oldPositionValue*360f/(maxValue-minValue)
                            changeAngle = touchAngle - currentAngle

                            val lowerThreshold: Float = currentAngle - (360f / (maxValue-minValue) * 5)
                            val higherThreshold: Float = currentAngle + (360f / (maxValue-minValue) * 5)

                            if(dragStartedAngle in lowerThreshold .. higherThreshold){
                                positionValue = (oldPositionValue + (changeAngle/(360f/(maxValue-minValue))).roundToInt())
                            }
                        },
                        onDragEnd = {
                            oldPositionValue = positionValue
                            onPositionChange(positionValue)
                        }
                    )
                }
        ){
            val width: Float = size.width
            val height: Float = size.height
            val circleThickness: Float = width / 25f
            circleCenter = Offset(x = width/2f, y = height/2f)

            drawCircle(
                style = Stroke(
                    width = circleThickness
                ),
                color = secondaryColor,
                radius = circleRadius,
                center = circleCenter
            )
            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f/maxValue) * positionValue.toFloat(),
                style = Stroke(
                    width = circleThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f)/2f,
                    (height - circleRadius * 2f)/2f
                )

            )
            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$positionValue",
                        circleCenter.x,
                        circleCenter.y,
                        Paint().apply {
                            textSize = circleRadius * 0.6f
                            textAlign = Paint.Align.CENTER
                            color = textColor.toArgb()
                            isFakeBoldText = true
                        }
                    )
                    drawText(
                        text,
                        circleCenter.x,
                        circleCenter.y + circleRadius * 0.3f,
                        Paint().apply {
                            textSize = circleRadius * 0.3f
                            textAlign = Paint.Align.CENTER
                            color = textColor.toArgb()
                            isFakeBoldText = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TimerPreview() {
    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CircularProgressIndicator(
                initialValue = 30,
                primaryColor = MaterialTheme.colorScheme.primary,
                circleRadius = 250f,
                onPositionChange = {},
                text = "segundos"
            )
        }
    }
}