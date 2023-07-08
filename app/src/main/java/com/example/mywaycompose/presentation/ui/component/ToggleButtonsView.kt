import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.*
import com.example.mywaycompose.utils.Constants.FirstStatistics
import com.example.mywaycompose.utils.Constants.SecondStatistics
import com.robertlevonyan.compose.buttontogglegroup.RowToggleButtonGroup

@Composable
fun ToggleButtonsView(
    kindStatistics:Int,
    colors:ThemeColors
) {
    RowToggleButtonGroup(
        primarySelection = 0,
        buttonCount = 3,
        selectedColor =
        when(kindStatistics){
            FirstStatistics -> colors.first_statistics_color
            SecondStatistics -> colors.second_statistics
            else -> Color.Transparent
                            },
        unselectedColor = colors.unselectedTabsBar,
        selectedContentColor = colors.espacially_second_task_title,
        unselectedContentColor = colors.espacially_second_task_title,
        elevation = ButtonDefaults.elevation(0.dp), // elevation of toggle group buttons
        buttonTexts = arrayOf("Day", "Month", "Year"),
        shape = RoundedCornerShape(10.dp),
        buttonHeight = 32.dp,
        border = BorderStroke(width = 0.dp, color = Color.Transparent),
    ) { index ->
        // check index and handle click
    }
}