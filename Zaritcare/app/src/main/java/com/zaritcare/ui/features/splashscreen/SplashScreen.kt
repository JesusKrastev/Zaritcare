package com.zaritcare.ui.features.splashscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zaritcare.R
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.composables.TextTile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Information(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    image: Painter
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TextTile(title = title)
        TextBody(text = text)
        Image(
            modifier = Modifier.size(200.dp),
            painter = image,
            contentDescription = "icon"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Body(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    listScreens: List<ScreenInfoUiState>
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->
        Information(
            title = listScreens[page].title,
            text = listScreens[page].text,
            image = listScreens[page].image
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .border(
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                shape = CircleShape
            ),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.Transparent
        )
    ){
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "back arrow"
        )
    }
}

@Composable
fun AdvancedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ){
        Icon(
            modifier = Modifier.graphicsLayer {
                scaleX = -1f // Reflejar horizontalmente
            },
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "front arrow"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndicatorPage(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onAdvancedClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if(pagerState.currentPage > 0) {
            BackButton(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart),
                onClick = onBackClick
            )
        }
        IndicatorPage(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            pagerState = pagerState
        )
        AdvancedButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterEnd),
            onClick = onAdvancedClick
        )
    }
}

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text("Empezar")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Footer(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onAdvancedClick: () -> Unit,
    onBackClick: () -> Unit,
    onClickStart: () -> Unit
) {
    if(pagerState.currentPage == pagerState.pageCount - 1) {
        StartButton(
            modifier = modifier.padding(16.dp),
            onClick = onClickStart
        )
    } else {
        Navigation(
            pagerState = pagerState,
            onAdvancedClick = onAdvancedClick,
            onBackClick = onBackClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(
    modifier: Modifier = Modifier,
    listScreens: List<ScreenInfoUiState>,
    pagerState: PagerState,
    onAdvancedClick: () -> Unit,
    onBackClick: () -> Unit,
    onClickStart: () -> Unit
) {
    Column {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Body(
                modifier = Modifier.weight(1f),
                pagerState = pagerState,
                listScreens = listScreens
            )
            Footer(
                pagerState = pagerState,
                onAdvancedClick = onAdvancedClick,
                onBackClick = onBackClick,
                onClickStart = onClickStart
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit
) {
    val listScreens: List<ScreenInfoUiState> = listOf(
        ScreenInfoUiState(
            title = "Evaluación del Estado Emocional",
            text = "Te permite evaluar tu estado emocional como cuidador a través de una serie de preguntas, utilizando una escala del 0 al 10.",
            image = painterResource(id = R.drawable.paperlist)
        ),
        ScreenInfoUiState(
            title = "Escala Zarit",
            text = "Te permitirá evaluar tu nivel de sobrecarga como cuidador de manera más rápida y efectiva. Es una herramienta diseñada para comprender mejor tus necesidades.",
            image = painterResource(id = R.drawable.lens)
        ),
        ScreenInfoUiState(
            title = "Informe del Estado del Cuidador",
            text = "Se genera un informe detallado del estado del cuidador basado en las evaluaciones emocionales realizadas y escala de sobrecarga ZARIT.",
            image = painterResource(id = R.drawable.report)
        )
    )
    val pagerState: PagerState = rememberPagerState(pageCount = { listScreens.size })
    val scope: CoroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Content(
            listScreens = listScreens,
            pagerState = pagerState,
            onClickStart = onClickStart,
            onAdvancedClick = {
                scope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            onBackClick = {
                scope.launch {
                    if (pagerState.currentPage > 0) {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            },
        )
    }
}