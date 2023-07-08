
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.presentation.theme.MainThemeColor
import com.example.mywaycompose.presentation.theme.ThemeColors
import com.example.mywaycompose.presentation.theme.TitleMyWayColor
import com.example.mywaycompose.presentation.theme.monsterrat
import com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.views.ClickableMainTaskView

@Composable
fun ChooseMainTaskAlertView(
    mainTasks:List<MainTask>,
    colors: ThemeColors,
    onTap:(Int) -> Unit,
    onDismiss:() -> Unit

) {
    
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = colors.main_background
        ) {
            Column() {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Select goal:",
                    fontFamily = monsterrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    color = colors.title_color,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(25.dp))
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 15.dp)
                ){

                    items(mainTasks,{it.id!!}){res ->
                        ClickableMainTaskView(res, colors ){
                            onTap(it)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
    
}