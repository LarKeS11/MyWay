package com.example.mywaycompose.presentation.ui.screen.edit_main_task

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.usecase.local_service.UseGetActuallySubtaskId
import com.example.mywaycompose.domain.usecase.local_service.UseGetImageFileById
import com.example.mywaycompose.domain.usecase.main_tasks.UseAddFullMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskById
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskId
import com.example.mywaycompose.domain.usecase.main_tasks.UsePushMainTaskImageToFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UsePushMainTaskToFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseUpdateMainTask
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallySubtaskId
import com.example.mywaycompose.domain.usecase.subtasks.UseAddSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteSubtasksByMainTaskId
import com.example.mywaycompose.domain.usecase.subtasks.UseDeleteTasksDependsOnSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksByMainTaskId
import com.example.mywaycompose.presentation.ui.screen.edit_main_task.state.EditMainTaskScreenState
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMainTaskViewModel @Inject constructor(
    private val useGetMainTaskById: UseGetMainTaskById,
    private val savedStateHandle: SavedStateHandle,
    private val useAddFullMainTask: UseAddFullMainTask,
    private val usePushMainTaskToFirebase: UsePushMainTaskToFirebase,
    private val useGetMainTaskId: UseGetMainTaskId,
    private val usePutActuallyMainTaskId: UsePutActuallyMainTaskId,
    private val useGetImageFileById: UseGetImageFileById,
    private val usePushMainTaskImageToFirebase: UsePushMainTaskImageToFirebase,
    private val useAddSubtask: UseAddSubtask,
    private val useGetActuallySubtaskId: UseGetActuallySubtaskId,
    private val usePutActuallySubtaskId: UsePutActuallySubtaskId,
    private val useGetSubtasksByMainTaskId: UseGetSubtasksByMainTaskId,
    private val useUpdateMainTask: UseUpdateMainTask,
    private val useDeleteSubtasksByMainTaskId: UseDeleteSubtasksByMainTaskId,
    private val useDeleteTasksDependsOnSubtask: UseDeleteTasksDependsOnSubtask
    ):ViewModel() {


    private val _textMainTaskFormValue = savedStateHandle.getStateFlow("text_main_task_form_value","")
    private val _iconMainTaskFormValue = savedStateHandle.getStateFlow("icon_main_task_form_value","")
    private val _activeColorPicker = savedStateHandle.getStateFlow("active_color_picker",false)
    private val _selectedSubtaskFormColor = savedStateHandle.getStateFlow<String?>("selected_subtask_form_color",null)
    private val _selectedSubtaskFormTitle = savedStateHandle.getStateFlow("selected_subtask_form_title","")
    private val _subtaskError = savedStateHandle.getStateFlow("subtask_error","")
    private val _selectedMainTaskImage = savedStateHandle.getStateFlow<Uri?>("selected_main_task_image",null)
    private val _mainTaskError = savedStateHandle.getStateFlow("main_task_error","")


    private val _subtasksState = mutableStateListOf<SubTask>()
    val subtasksState = _subtasksState

    private val _hasBeenDone = mutableStateOf(false)
    val hasBeenDone = _hasBeenDone

    var oldMainTask:MainTask? = null


    val state = combine(_textMainTaskFormValue, _iconMainTaskFormValue, _activeColorPicker, _selectedSubtaskFormColor, _selectedSubtaskFormTitle, _subtaskError, _selectedMainTaskImage, _mainTaskError){
            props ->
        EditMainTaskScreenState(
            textMainTaskFormValue = props[0] as String,
            iconMainTaskFormValue = props[1] as String,
            activeColorPicker = props[2] as Boolean,
            selectedSubtaskFormColor = props[3] as String?,
            selectedSubtaskFormTitle = props[4] as String,
            subtaskError = props[5] as String,
            selectedMainTaskImage = props[6] as Uri?,
            mainTaskError = props[7] as String
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EditMainTaskScreenState())

    fun getMainTask(id:Int){
        useGetMainTaskById.invoke(id).onEach { res ->
            when(res){
                is Resource.Success -> {
                    val mainTask = res.data!!
                    oldMainTask = mainTask
                    setTextMainTaskForm(mainTask.title)
                    setIconMainTaskForm(mainTask.icon)
                    setMainTaskImage(Uri.parse(mainTask.imageSrc))

                }
                else -> {}
            }
        }.launchIn(viewModelScope)
        useGetSubtasksByMainTaskId.invoke(id).onEach {res ->
            when(res){
                is Resource.Success -> {
                    res.data!!.forEach {
                        _subtasksState.add(it)
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)

    }

    fun setTextMainTaskForm(text:String){
        savedStateHandle["text_main_task_form_value"] = text
    }

    fun setIconMainTaskForm(icon:String){
        savedStateHandle["icon_main_task_form_value"] = icon
    }

    fun switchActiveColorPicker(bool:Boolean){
        savedStateHandle["active_color_picker"] = bool
    }

    fun setSubtaskFormColor(color:String?){
        savedStateHandle["selected_subtask_form_color"] = color
    }

    fun changeSubtaskFormTitle(text:String){
        savedStateHandle["selected_subtask_form_title"] = text
    }

    private fun setMainTaskError(text:String){
        savedStateHandle["main_task_error"] = text
    }

    fun doneFilling(id:Int? = null){

        if(_textMainTaskFormValue.value.isEmpty()){
            setMainTaskError("Didn't enter goal!")
            return
        }
        if(_iconMainTaskFormValue.value.isEmpty()){
            setMainTaskError("Didn't enter icon!")
            return
        }
        if(_selectedMainTaskImage.value == null){
            setMainTaskError("Didn't select image!")
            return
        }

        val maintaskId: Int
        if(id == null) {
            maintaskId = useGetMainTaskId.execute()
            usePutActuallyMainTaskId.execute(maintaskId)
        }
        else maintaskId = id

        val maintask = MainTask(
            id = maintaskId,
            title = _textMainTaskFormValue.value,
            imageSrc = _selectedMainTaskImage.value.toString(),
            icon = _iconMainTaskFormValue.value
        )

        if(id == null) {
            useAddFullMainTask.invoke(maintask).onEach { res ->
                when (res) {
                    is Resource.Success -> {

                        val file = useGetImageFileById.execute(maintaskId)
                        usePushMainTaskImageToFirebase.execute(file, maintaskId)
                        usePushMainTaskToFirebase.execute(maintask)

                        saveSubtasks(maintaskId)
                        exitScreen()
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
        else{
            viewModelScope.launch {
                useUpdateMainTask.execute(
                    mainTask = maintask,
                    oldMainTask = oldMainTask!!
                )
                saveSubtasks(maintaskId)
                exitScreen()
            }
        }
    }

    private suspend fun saveSubtasks(maintaskId:Int){
        useDeleteSubtasksByMainTaskId.execute(maintaskId)
        subtasksState.forEach {
            val subtaskId = useGetActuallySubtaskId.execute()
            usePutActuallySubtaskId.execute(subtaskId)
            useAddSubtask.execute(
                SubTask(
                    id = subtaskId,
                    title = it.title,
                    color = it.color,
                    mainTaskId = maintaskId
                )
            )
        }
    }

    private fun exitScreen(){
        _hasBeenDone.value = true
    }

    fun addNewSubtask(){
        if(_selectedSubtaskFormTitle.value.isEmpty()) {
            setSubtaskError("Didn't select subtask!")
            return
        }
        if(_selectedSubtaskFormColor.value == null){
            setSubtaskError("Didn't select color!")
            return
        }
        _subtasksState.add(SubTask(title = _selectedSubtaskFormTitle.value,color = _selectedSubtaskFormColor.value!!, mainTaskId = -1))
        setSubtaskError("")
        changeSubtaskFormTitle("")
        setSubtaskFormColor(null)
    }

    private fun setSubtaskError(text:String){
        savedStateHandle["subtask_error"] = text
    }

    fun deleteSubtask(subtask:SubTask){
        _subtasksState.remove(subtask)
        viewModelScope.launch {
            if(subtask.id != null) useDeleteTasksDependsOnSubtask.execute(subtask.id!!)
        }
    }

    fun setMainTaskImage(uri:Uri){
        savedStateHandle["selected_main_task_image"] = uri
    }


}