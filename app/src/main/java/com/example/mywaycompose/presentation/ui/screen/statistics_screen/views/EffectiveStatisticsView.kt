package com.example.mywaycompose.presentation.ui.screen.statistics_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.StatisticsValueTextColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.views.getLineCharData
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.views.horizontalOffset
import com.example.mywaycompose.utils.Constants
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer

@Composable
fun EffectiveStatistics(
    data:List<Pair<String, Int>>,
    colors:ThemeColors
) {

    val lineChartData = data.map { LineChartData.Point(it.second.toFloat(), it.first) }

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(start = 5.dp, end = 20.dp)
    ) {

        LineChart(
            linesChartData = listOf(getLineCharData(lineChartData,colors,  Constants.FirstStatistics)),
            horizontalOffset = horizontalOffset,
            pointDrawer = NoPointDrawer,
            xAxisDrawer = SimpleXAxisDrawer(
                labelTextColor = colors.title_color,
                labelTextSize = 11.sp,
                axisLineColor = colors.title_color
            ),
            yAxisDrawer = SimpleYAxisDrawer(
                labelTextColor = colors.title_color,
                labelTextSize = 11.sp,
                axisLineColor = colors.title_color
            )
        )
    }

}