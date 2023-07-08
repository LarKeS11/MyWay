package com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.usecase.ideas.UseAddIdea
import com.example.mywaycompose.domain.usecase.ideas.UseGetIdeas
import com.example.mywaycompose.domain.usecase.local_service.UseGetActuallySubtaskId
import com.example.mywaycompose.domain.usecase.main_tasks.UseAddFullMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetAllMainTasks
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallySubtaskId
import com.example.mywaycompose.domain.usecase.subtasks.UseAddSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteIdea
import com.example.mywaycompose.domain.usecase.subtasks.UsePushIdeaToFirebase
import com.example.mywaycompose.domain.usecase.tasks.UseAddTask
import com.example.mywaycompose.domain.usecase.tasks.UseCheckSameTasks
import com.example.mywaycompose.domain.usecase.tasks.UsePushTaskToFirebase
import com.example.mywaycompose.presentation.ui.screen.ideas_pull_screen.state.IdeasPullState
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullIdeasViewModel @Inject constructor(
    private val useGetAuthFirebaseSession: UseGetAuthFirebaseSession,
    private val savedStateHandle: SavedStateHandle,
    private val useAddIdea: UseAddIdea,
    private val useGetIdeas: UseGetIdeas,
    private val useAddFullMainTask: UseAddFullMainTask,
    private val usePushIdeaToFirebase: UsePushIdeaToFirebase,
    private val useDeleteIdea: UseDeleteIdea,
    private val useGetActuallyMainTaskId: UseGetMainTaskId,
    private val usePutActuallyMainTaskId: UsePutActuallyMainTaskId,
    private val useGetAllMainTasks: UseGetAllMainTasks,
    private val useAddSubtask: UseAddSubtask,
    private val useGetActuallySubtaskId: UseGetActuallySubtaskId,
    private val usePutActuallySubtaskId: UsePutActuallySubtaskId,
    private val useAddTask: UseAddTask,
    private val usePushTaskToFirebase: UsePushTaskToFirebase,
    private val useCheckSameTasks: UseCheckSameTasks
):ViewModel() {

    val user = useGetAuthFirebaseSession.execute().currentUser

    private val _showTaskForm = savedStateHandle.getStateFlow("showTaskForm",false)
    val showTaskForm = _showTaskForm

    private val _task = savedStateHandle.getStateFlow("task","")
    val task = _task

    private val _ideas = savedStateHandle.getStateFlow("ideas", emptyList<Idea>())
    val ideas = _ideas

    private val _mainTaskId = savedStateHandle.getStateFlow<Int?>("mainTaskId",null)
    val mainTaskId = _mainTaskId

    private val _mainTasks = savedStateHandle.getStateFlow("main_tasks", listOf<MainTask>())
    val mainTasks = _mainTasks

    private val _showAlertDialog = savedStateHandle.getStateFlow("showAlertDialog", false)
    val showAlertDialog = _showAlertDialog

    private val _subtaskMainTaskId = savedStateHandle.getStateFlow<Int?>("subtaskMainTaskId",null)
    val subtaskMainTaskId = _subtaskMainTaskId

    private val _currentIdea = savedStateHandle.getStateFlow<String?>("current_idea",null)
    val currentIdea = _currentIdea

    private val _selectedTaskDay = savedStateHandle.getStateFlow("task_day","")
    val selectedTaskDay = _selectedTaskDay

    private val _showTimePicker = savedStateHandle.getStateFlow("show_time_picker", false)
    val showTimePicker = _showTimePicker

    private val _timeError = savedStateHandle.getStateFlow("time_error", "")

    init {
        getIdeas()
        getMainTasks()
    }

    fun resetState(){
        savedStateHandle["showTaskForm"] = false
        savedStateHandle["task"] = ""
        savedStateHandle["ideas"] = emptyList<Idea>()
        savedStateHandle["mainTaskId"] = null
        savedStateHandle["time_error"] = ""
    }

    val state = combine(showTaskForm,task,ideas, mainTaskId, _timeError){showTaskForm, task, ideas, mainTaskId, timeError ->
        IdeasPullState(
            showTaskForm = showTaskForm,
            task = task,
            ideas = ideas,
            mainTaskId = mainTaskId,
            timeError = timeError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),IdeasPullState())

    fun switchFormTask(){
        savedStateHandle["showTaskForm"] = !showTaskForm.value
        if(!showTaskForm.value) cleanTask()
    }

    fun switchShowingAlertDialog(){
        savedStateHandle["showAlertDialog"] = !showAlertDialog.value
    }

    fun switchShowingTimePicker(){
        savedStateHandle["show_time_picker"] = !showTimePicker.value
    }

    private fun cleanTask(){
        savedStateHandle["task"] = ""
    }

    fun changeTaskValue(task:String){
        savedStateHandle["task"] = task
    }

    fun setSubTaskMainTaskId(id:Int?){
        switchShowingAlertDialog()
        savedStateHandle["subtaskMainTaskId"] = id
    }

    fun addIdea(task:String){
        if(task.isNotEmpty()) {
            viewModelScope.launch {
                useAddIdea.execute(
                    Idea(idea = task)
                )
                usePushIdeaToFirebase.execute(Idea(idea = task))
                switchFormTask()
                getIdeas()
            }
        }
    }

    fun setCurrentIdea(str:String){
        savedStateHandle["current_idea"] = str
    }

    private fun getMainTasks(){
        useGetAllMainTasks.invoke().onEach { res ->
            when(res){
                is Resource.Success -> savedStateHandle["main_tasks"] = res.data!!.filter { !it.doubts }
            }
        }.launchIn(viewModelScope)
    }

    private fun getIdeas(){
        useGetIdeas.invoke().onEach { res ->
            when(res){
                is Resource.Loading -> {}
                is Resource.Success -> savedStateHandle["ideas"] = res.data
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun setTaskDay(day:String){
        savedStateHandle["task_day"] = day
    }

    fun addSubtask(color:String, title:String){
        val id = useGetActuallySubtaskId.execute()
        usePutActuallySubtaskId.execute(id)
        viewModelScope.launch {
            useAddSubtask.execute(SubTask(
                id = id,
                mainTaskId = subtaskMainTaskId.value!!,
                title = title,
                color = color
                )
            )
            savedStateHandle["subtaskMainTaskId"] = null
            cleanIdea()
        }

    }

    fun addTask(hours:String, minutes:String) {
        val time = "${if (hours.length == 1)  "0${hours}" else hours}:${if (minutes.length == 1) "0${minutes}" else minutes}"
        viewModelScope.launch {

            if(!useCheckSameTasks.execute(time, selectedTaskDay.value)){
                savedStateHandle["time_error"] = "There is already the same task!"
                return@launch
            }

            val task = Task(
                task = currentIdea.value!!,
                time = time,
                date = selectedTaskDay.value,
                grade = 0,
                status = false
            )
            useAddTask.execute(task)
            usePushTaskToFirebase.execute(task)
            switchShowingTimePicker()
            cleanIdea()
        }

    }

    fun addMainTask(
        idea:Idea
    ){
        val id = useGetActuallyMainTaskId.execute()
        usePutActuallyMainTaskId.execute(id)

        useAddFullMainTask.invoke(
            MainTask(
                id = id,
                title = idea.idea,
                icon = "",
                imageSrc = "",
                doubts = true,
                idIdea = idea.id
            )
        ).onEach { res ->
            when(res){
                is Resource.Success -> {
                    savedStateHandle["mainTaskId"] = res.data!!.toInt()
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun deleteIdea(idea:Idea){
        viewModelScope.launch {
            useDeleteIdea.execute(idea)
            getIdeas()
        }
    }

    fun cleanIdea(){
        val currentIdeaId = ideas.value.filter { it.idea == currentIdea.value!!}
        deleteIdea(Idea(id = currentIdeaId[0].id, idea = currentIdea.value!!))
    }

}