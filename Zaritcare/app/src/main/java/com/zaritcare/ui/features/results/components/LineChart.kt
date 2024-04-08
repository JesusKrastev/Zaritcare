package com.zaritcare.ui.features.results.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.zaritcare.models.Category
import com.zaritcare.ui.composables.TextTile
import com.zaritcare.ui.features.results.AnswerUiState
import com.zaritcare.ui.theme.ZaritcareTheme

@Composable
fun LineChartWidget(
    data: List<AnswerUiState>,
    title: String,
    range: ClosedFloatingPointRange<Float> = 0f..10f
) {
    val pointsData: List<Point> = data.mapIndexed { index, answerUiState ->
        val x: Float = (index + 1).toFloat()
        val y: Float = answerUiState.answer.toFloat()
        Point(x = x, y = y)
    }

    val xAxisData: AxisData = AxisData.Builder()
        .axisStepSize(size = 100.dp)
        .backgroundColor(color = Color.Transparent)
        .steps(data.size - 1)
        .labelData { i -> (i+1).toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.primary)
        .axisLabelColor(MaterialTheme.colorScheme.primary)
        .build()

    val yAxisData: AxisData = AxisData.Builder()
        .steps(range.endInclusive.toInt())
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val minY: Float = range.start
            val yScale: Float = (range.endInclusive - range.start) / range.endInclusive
            val labelValue: Float = minY + (i * yScale)
            labelValue.toInt().toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.primary)
        .axisLabelColor(MaterialTheme.colorScheme.primary)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(
                        color = MaterialTheme.colorScheme.primary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    SelectionHighlightPoint(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.background,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextTile(title = title)
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )
    }
}

@Composable
@Preview
fun LineChartPreview() {
    val data = listOf(
        AnswerUiState(
            id = 1,
            question = 1,
            answer = "5",
            category = Category.BIENESTAR,
            user = 1
        ),
        AnswerUiState(
            id = 2,
            question = 2,
            answer = "7",
            category = Category.BIENESTAR,
            user = 1
        ),
        AnswerUiState(
            id = 3,
            question = 3,
            answer = "3",
            category = Category.BIENESTAR,
            user = 1
        ),
        AnswerUiState(
            id = 4,
            question = 4,
            answer = "8",
            category = Category.BIENESTAR,
            user = 1
        ),
        AnswerUiState(
            id = 5,
            question = 5,
            answer = "2",
            category = Category.BIENESTAR,
            user = 1
        )
    )

    ZaritcareTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LineChartWidget(
                    data = data,
                    title = "Escala de Bienestar"
                )
            }
        }
    }
}