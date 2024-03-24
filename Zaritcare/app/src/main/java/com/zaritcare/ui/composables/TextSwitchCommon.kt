package com.zaritcare.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaritcare.ui.theme.ZaritcareTheme

fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

@Composable
private fun TabIndicator(
    modifier: Modifier = Modifier,
    indicatorOffset: Dp,
    tabWidth: Dp
) {
    Box(
        modifier = modifier
            .offset(x = indicatorOffset)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .width(tabWidth)
            .fillMaxHeight()
    )
}

@Composable
private fun TabContent(
    modifier: Modifier = Modifier,
    indicatorOffset: Dp,
    selectedColor: Color,
    textSelectedColor: Color,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.drawWithContent {
            val padding = 8.dp.toPx()
            drawRoundRect(
                topLeft = Offset(x = indicatorOffset.toPx() + padding, padding),
                size = Size(size.width / 2 - padding * 2, size.height - padding * 2),
                color = textSelectedColor,
                cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
            )
            drawWithLayer {
                drawContent()
                drawRoundRect(
                    topLeft = Offset(x = indicatorOffset.toPx(), 0f),
                    size = Size(size.width / 2, size.height),
                    color = selectedColor,
                    cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                    blendMode = BlendMode.SrcOut
                )
            }
        }
    ) {
        content()
    }
}

@Composable
private fun TabItem(
    modifier: Modifier = Modifier,
    tabWidth: Dp,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(tabWidth)
            .fillMaxHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun TextSwitch(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    textSelectedColor: Color = MaterialTheme.colorScheme.onPrimary,
    items: List<String>,
    onSelectionChange: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp)
    ) {
        if (items.isNotEmpty()) {
            val maxWidth = this.maxWidth
            val tabWidth = maxWidth / items.size
            val indicatorOffset by animateDpAsState(
                targetValue = tabWidth * selectedIndex,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )
            TabIndicator(
                modifier = Modifier,
                indicatorOffset = indicatorOffset,
                tabWidth = tabWidth
            )
            TabContent(
                modifier = Modifier.fillMaxWidth(),
                indicatorOffset = indicatorOffset,
                selectedColor = selectedColor,
                textSelectedColor = textSelectedColor
            ) {
                items.forEachIndexed { index, text ->
                    TabItem(
                        modifier = Modifier,
                        tabWidth = tabWidth,
                        text = text,
                        onClick = { onSelectionChange(index) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TextSwitchTest() {
    val items = remember { listOf("Bienestar", "Zarit") }
    var selectedIndex by remember { mutableIntStateOf(0) }

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TextSwitch(
                    selectedIndex = selectedIndex,
                    items = items,
                    onSelectionChange = {
                        selectedIndex = it
                    }
                )
            }
        }
    }
}