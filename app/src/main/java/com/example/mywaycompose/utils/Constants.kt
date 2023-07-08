package com.example.mywaycompose.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mywaycompose.presentation.theme.ThemeColors

object Constants {
     const val MY_WAY_URL = "https://myway.site"
     const val FirstStatistics = 1
     const val SecondStatistics = 2
     const val CompareStatistic = 3
     const val MoreThenCurrentDate = 1
     const val SmallThenCurrentDate = 2
     const val EqualsCurrentDate = 3
     const val AddMainTask = 1
     const val EditMainTask = 2
     const val MAIN_TASK_ID_KIND = "main_task_id"
     const val SUBTASK_ID_KIND = "sub_task_id"
     const val TASK_STAT_ID_KIND = "statistics_id"
     const val LONG_TASK_ID_KIND = "long_task_id"
     const val TASKS_KIND = "tasks_database"
     const val MAIN_TASKS_KIND = "main_tasks_database"
     const val SUBTASK_TASKS_KIND = "sub_tasks_database"
     const val TASK_STAT_KIND = "tasks_stat_database"
     const val EFFECTIVE_STAT_KIND = "effective_database"
     const val EFFECTIVE_STAT_HISTORY_KIND = "effective_history_database"
     const val LONG_TASKS_KIND = "long_tasks"
     const val LONG_TASKS_STAT_KIND = "long_tasks_stat"
     const val IDEAS_KIND = "ideas_database"
     const val START_DAY_USING_KIND = "start_day_using_database"
     const val DAY_MAIN_THEME = "day_main_theme"
     const val NIGHT_MAIN_THEME = "night_main_theme"
     const val LONG_TASK_OPTION_BUTTONS = "long_task_option"
     const val TASK_OPTION_BUTTONS = "task_option"
     val TASKS_HORIZONTAL_PADDINGS = 16.dp
     val DAY_MAIN_THEME_COLORS = ThemeColors(
          main_background = Color(0xFFE3D4CB),
          title_color = Color(0xFF261E16),
          nav_calendar_day = Color(0x66261E16),
          selected_nav_calendar_day = Color(0xFFC48750),
          selected_menu_item = Color(0xFF261E16),
          un_selected_menu_item = Color(0x66261E16),
          task_background_color = Color(0xFFEBDED6),
          task_text_color = Color(0xE623202B),
          deadlines_calendar_color = Color(0xFFE47256),
          selected_calendar_date = Color(0xFFC48750),
          first_statistics_color = Color(0xFFE47256),
          second_statistics  = Color(0xFFE8A367),
          type = "day",
          icons = Color(0xFF23202B),
          simple_task_subground = Color(0xFFC1B6AF),
          completed_simple_task_subground = Color(0x80EBDED6),
          completed_task_background = Color(0xFF23202B),
          completed_task_title = Color(0x80261E16),
          espacially_first_task_title = Color(0xFFEBDED6),
          espacially_second_task_title = Color(0xE623202B),
          trash_color = Color(0xE6CA4444),
          form_stroke_color = Color(0x80261E16),
          form_text_color = Color(0xCC23202B),
          high_light_color = Color(0xFFC48750),
          effective_block = Color(0x80F0E5DD),
          unselectedTabsBar = Color(0xFFC9BAB1),
          subtitle = Color(0xB3000000),
          un_selected_option = Color(0x80000000),
          switcher = Color(0xFFF0E5DD),
          grade_btn = Color(0xE623202B),

     )
     val NIGHT_MAIN_THEME_COLORS = ThemeColors(
          main_background = Color(0xFF23202B),
          title_color = Color(0xFFFFFFFF),
          nav_calendar_day = Color(0xFFA2ACAE),
          selected_nav_calendar_day = Color(0xFFB46DEF),
          selected_menu_item = Color(0xE6FFFFFF),
          un_selected_menu_item = Color(0x80FFFFFF),
          task_background_color = Color(0xFF2E2938),
          task_text_color = Color(0xFFFFFFFF),
          deadlines_calendar_color = Color(0xFFE47256),
          selected_calendar_date = Color(0xFFB46DEF),
          first_statistics_color = Color(0xFF5DB5EF),
          second_statistics  = Color(0xFFB46DEF),
          type = "night",
          icons = Color(0x80FFFFFF),
          simple_task_subground = Color(0xFF23202B),
          completed_simple_task_subground = Color(0x80EBDED6),
          completed_task_background = Color(0x80C1B6AF),
          completed_task_title = Color(0x80261E16),
          espacially_first_task_title = Color(0xE623202B),
          espacially_second_task_title = Color(0xFFEBDED6),
          trash_color = Color(0xE6CA4444),
          form_stroke_color = Color(0xB3FFFFFF),
          form_text_color = Color(0x99FFFFFF),
          high_light_color = Color(0xFFA079CD),
          effective_block = Color(0x802D2937),
          unselectedTabsBar = Color(0xFF1A1820),
          subtitle = Color(0xB3FFFFFF),
          un_selected_option = Color(0x80FFFFFF),
          switcher = Color(0xFF2D2937),
          grade_btn = Color(0xFF1B1921)
     )
}

