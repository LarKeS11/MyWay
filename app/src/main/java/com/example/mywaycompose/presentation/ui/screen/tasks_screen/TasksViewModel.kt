package com.example.mywaycompose.presentation.ui.screen.tasks_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.model.*
import com.example.mywaycompose.domain.usecase.ideas.UseAddIdea
import com.example.mywaycompose.domain.usecase.local_service.UseCheckCorrectTime
import com.example.mywaycompose.domain.usecase.local_service.UseGetActualityDate
import com.example.mywaycompose.domain.usecase.local_service.UseGetListOfMonthDays
import com.example.mywaycompose.domain.usecase.long_tasks.UseCheckLongTask
import com.example.mywaycompose.domain.usecase.long_tasks.UseDeleteLongTask
import com.example.mywaycompose.domain.usecase.long_tasks.UseGetLongTasks
import com.example.mywaycompose.domain.usecase.long_tasks.UseGetPointsOfLongTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskById
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.statistics.UseAddTaskToStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticValueFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UsePushTaskToFirebaseStatistics
import com.example.mywaycompose.domain.usecase.statistics.UsePutStatisticEffectiveValueToFirebase
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtaskById
import com.example.mywaycompose.domain.usecase.tasks.UseAddTask
import com.example.mywaycompose.domain.usecase.tasks.UseDeleteTask
import com.example.mywaycompose.domain.usecase.tasks.UseGetTasks
import com.example.mywaycompose.domain.usecase.tasks.UseCheckSameTasks
import com.example.mywaycompose.domain.usecase.tasks.UsePushTaskToFirebase
import com.example.mywaycompose.domain.usecase.tasks.UseToCompleteTask
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.LongTasksState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.OneMainTaskState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.SubtaskTaskState
import com.example.mywaycompose.presentation.ui.screen.tasks_screen.state.TasksState
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class TasksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useGetActualityDate: UseGetActualityDate,
    private val useGetListOfMonthDays: UseGetListOfMonthDays,
    private val useGetLongTasks: UseGetLongTasks,
    private val useDeleteTask: UseDeleteTask,
    private val useGetTasks: UseGetTasks,
    private val useAddTask: UseAddTask,
    private val usePushTaskToFirebase: UsePushTaskToFirebase,
    private val useToCompleteTask: UseToCompleteTask,
    private val useAddIdea: UseAddIdea,
    private val useGetAuthFirebaseSession: UseGetAuthFirebaseSession,
    private val useCheckCorrectTime: UseCheckCorrectTime,
    private val useCheckSameTasks: UseCheckSameTasks,
    private val useDeleteLongTask: UseDeleteLongTask,
    private val useAddTaskToStatistic: UseAddTaskToStatistic,
    private val usePutStatisticEffectiveValueToFirebase: UsePutStatisticEffectiveValueToFirebase,
    private val useCheckLongTask: UseCheckLongTask,
    private val useGetPointsOfLongTask: UseGetPointsOfLongTask,
    private val usePutTaskToFirebaseStatistics: UsePushTaskToFirebaseStatistics
    ):ViewModel() {

    private val _actuallyDate = savedStateHandle.getStateFlow("actuallyDate",useGetActualityDate.execute().toDateString())
    private val _daysValuesList = savedStateHandle.getStateFlow<List<Pair<String, String>>>("days_values_list", listOf())
    private val _formTaskError = savedStateHandle.getStateFlow("formTaskError","")
    private val _showAddTaskForm = savedStateHandle.getStateFlow("add_task_form", false)


    private val _editTask = MutableStateFlow<Int?>(null)
    val editTask = _editTask

    private val _longTasksList = mutableStateListOf<LongTask>()
    val longTasksList = _longTasksList

    private val _tasksListState = mutableStateListOf<Task>()
    val tasksListState = _tasksListState

    val authSession = useGetAuthFirebaseSession.execute().currentUser

    val state = combine(_actuallyDate, _daysValuesList, _showAddTaskForm, _formTaskError){actuallyDate, daysValuesList, showAddTaskForm, formTaskError ->
        TasksState(
            actuallyDate = actuallyDate,
            daysValuesList = daysValuesList,
            showAddTaskForm = showAddTaskForm,
            formTaskError = formTaskError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksState())


    init {
        viewModelScope.launch {
            setActuallyDaysValuesList()
            getLongTasks()
            getTasks()
        }
    }

    fun actualDay():Boolean{
        return useGetActualityDate.execute().toDateString() == _actuallyDate.value
    }

    fun setActuallyDate(date:String){
        savedStateHandle["actuallyDate"] = date
        getTasks(date)
        getLongTasks()
        setActuallyDaysValuesList()
    }


    private fun setActuallyDaysValuesList(){
        savedStateHandle["days_values_list"] = useGetListOfMonthDays.execute(_actuallyDate.value.toDateServer()).map { Pair(it.first.toDateString(), it.second) }
    }

    private fun setFormError(err:String){
        savedStateHandle["formTaskError"] = err
    }

    private suspend fun checkCorrectTask(task:Task):Boolean{
        val time = task.time.split(" ").filter { it.isNotEmpty() }.joinToString(":")
        val taskcheck = !useCheckSameTasks.execute(time, date = _actuallyDate.value)
        val timecheck = !useCheckCorrectTime.execute(time)
        if(timecheck) setFormError("Incorrect time!")
        if(taskcheck) setFormError("There is already the same task!")
        if(!taskcheck && !timecheck){
            setFormError("")
            return true
        }
        return false
    }

    fun addTask(task:Task){

        viewModelScope.launch {
            val correct = checkCorrectTask(task)
            if(correct){
                viewModelScope.launch {
                    useAddTask.execute(task)
                    usePushTaskToFirebase.execute(task)
                    getTasks()
                    switchShowAddTaskForm()
                }
            }
        }

    }

    fun selectedDateEqualsActually() = _actuallyDate.value == useGetActualityDate.execute().toDateString()

    private fun getLongTasks(){
        useGetLongTasks.invoke(_actuallyDate.value).onEach { res ->
            when(res){
                is Resource.Success -> {
                    _longTasksList.removeAll{true}
                    res.data!!.forEach {
                        _longTasksList.add(it)
                    }
                }
                else -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteLongTask(longTask: LongTask){
        viewModelScope.launch {
            useDeleteLongTask.execute(longTask)
            getLongTasks()
        }
    }

    fun checkLongTask(id:Int){
        if(_actuallyDate.value == useGetActualityDate.execute().toDateString()) {
            viewModelScope.launch {
                useCheckLongTask.execute(
                    LongTaskStat(
                        idTask = id,
                        date = useGetActualityDate.execute().toDateString()
                    )
                )
            }
        }
    }

    fun getPointOfLongTask(longTask:LongTask,pointsState:MutableState<Float>){
        viewModelScope.launch {
            val points = useGetPointsOfLongTask.execute(longTask)
            pointsState.value = points
        }
    }

    fun toEditTask(id:Int){
        _editTask.value = id
    }

    fun toCompleteTask(task:Task){
        tasksListState.remove(task)
        task.status = true
        viewModelScope.launch {
            useToCompleteTask.execute(task)
            useAddTaskToStatistic.execute(TaskStatistic(
                date = task.date,
                priority = task.grade!!,
                mainTaskId = task.idBigTask ?: -1
            ))
            usePutTaskToFirebaseStatistics.execute(
                TaskStatistic(
                    date = task.date,
                    priority = task.grade!!,
                    mainTaskId = task.idBigTask ?: -1
                )
            )
            usePutStatisticEffectiveValueToFirebase.execute(
                effective = EffectiveStat(
                    priority = task.grade,
                    appliedMainTask = task.idBigTask != null,
                    appliedSubtask = task.idSubTask != null
                ),
                day = _actuallyDate.value
            )
            getTasks()
        }
    }


    fun switchShowAddTaskForm(){
        setFormError("")
        savedStateHandle["add_task_form"] = !_showAddTaskForm.value
    }

    private fun getTasks(date:String = _actuallyDate.value){
        useGetTasks.invoke(date).onEach {res ->
            when(res){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _tasksListState.removeAll{true}
                    res.data!!.forEach{
                        _tasksListState.add(it)
                    }
                }
                else -> {
                    _tasksListState.removeAll{true}
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteTask(task:Task){
        viewModelScope.launch {
            useDeleteTask.execute(task)
            getTasks()
            getLongTasks()
        }
    }

    fun taskToIdea(task:Task){
        deleteTask(task)
        viewModelScope.launch {
            useAddIdea.execute(Idea(idea = task.task))
        }
    }
}