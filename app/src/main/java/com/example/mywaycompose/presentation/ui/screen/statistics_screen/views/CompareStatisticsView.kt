package com.example.mywaycompose.presentation.ui.screen.statistics_screen.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.utils.Constants.FirstStatistics
import com.example.mywaycompose.utils.Constants.SecondStatistics
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer


fun getLineCharData(
    list:List<LineChartData.Point>,
    colors: ThemeColors,
    kind:Int
):LineChartData{
    return LineChartData(
        points = list,
        lineDrawer = SolidLineDrawer(
            color = when(kind){
                FirstStatistics -> colors.first_statistics_color
                SecondStatistics -> colors.second_statistics
                else -> MainThemeColor
                              },
            thickness = 5.dp
        ),
    )
}
var horizontalOffset by mutableStateOf(5f)


@Composable
fun CompareStatisticsView(
    data:Pair<List<Pair<String, Float>>,List<Pair<String, Float>>>,
    colors:ThemeColors
) {

    val lineChartData = data.first.map {  LineChartData.Point(it.second, it.first) }
    val lineChartDat2 = data.second.map {  LineChartData.Point(it.second, it.first) }

    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {

        LineChart(
            linesChartData = listOf(getLineCharData(lineChartData,  colors, FirstStatistics), getLineCharData(lineChartDat2,colors, SecondStatistics)),
            horizontalOffset = horizontalOffset,
            pointDrawer = NoPointDrawer,
            xAxisDrawer = SimpleXAxisDrawer(
                labelTextColor = colors.title_color,
                labelTextSize = 11.sp,
                axisLineColor =  colors.title_color
            ),
            yAxisDrawer = SimpleYAxisDrawer(
                labelTextColor =  colors.title_color,
                labelTextSize = 11.sp,
                axisLineColor =  colors.title_color
            )
        )
    }


}