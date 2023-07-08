package com.example.mywaycompose.presentation.ui.screen.edit_task_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.model.LongTask
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.model.SubTask
import com.example.mywaycompose.domain.model.Task
import com.example.mywaycompose.domain.model.toDateString
import com.example.mywaycompose.domain.usecase.local_service.UseCheckCorrectTime
import com.example.mywaycompose.domain.usecase.local_service.UseCompareDateWithCurrent
import com.example.mywaycompose.domain.usecase.local_service.UseGetActualityDate
import com.example.mywaycompose.domain.usecase.long_tasks.UseAddLongTask
import com.example.mywaycompose.domain.usecase.long_tasks.UseGetActuallyLongTaskId
import com.example.mywaycompose.domain.usecase.long_tasks.UsePutActuallyLongTaskId
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetAllMainTasks
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskById
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtaskById
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksByMainTaskId
import com.example.mywaycompose.domain.usecase.tasks.UseDeleteTask
import com.example.mywaycompose.domain.usecase.tasks.UseGetTaskById
import com.example.mywaycompose.domain.usecase.tasks.UsePushTaskToFirebase
import com.example.mywaycompose.domain.usecase.tasks.UseUpdateTask
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state.MainTasksState
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state.SubTasksByMainTaskState
import com.example.mywaycompose.presentation.ui.screen.edit_task_screen.state.TaskState
import com.example.mywaycompose.utils.Constants.EqualsCurrentDate
import com.example.mywaycompose.utils.Constants.MoreThenCurrentDate
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val useGetTaskById: UseGetTaskById,
    private val useGetAllMainTasks: UseGetAllMainTasks,
    private val useUpdateTask: UseUpdateTask,
    private val useGetSubtasksByMainTaskId: UseGetSubtasksByMainTaskId,
    private val useGetActualityDate: UseGetActualityDate,
    private val useCompareDateWithCurrent: UseCompareDateWithCurrent,
    private val usePushTaskToFirebase: UsePushTaskToFirebase,
    private val savedStateHandle: SavedStateHandle,
    private val useAddLongTask: UseAddLongTask,
    private val useDeleteTask: UseDeleteTask,
    private val useGetSubtaskById: UseGetSubtaskById,
    private val useGetMainTaskById: UseGetMainTaskById,
    private val useGetActuallyLongTaskId: UseGetActuallyLongTaskId,
    private val usePutActuallyLongTaskId: UsePutActuallyLongTaskId,
    private val useCheckCorrectTime: UseCheckCorrectTime

):ViewModel() {

    private val _idMainTask = savedStateHandle.getStateFlow<Int?>("main_task_id",null)
    private val _idSubTask = savedStateHandle.getStateFlow<Int?>("subtask_id",null)
    private val _showRangePicker = savedStateHandle.getStateFlow("range_picker",false)
    private val _firstRangeDate = savedStateHandle.getStateFlow<String?>("first_range_date",null)
    private val _secondRangeDate = savedStateHandle.getStateFlow<String?>("second_range_date",null)
    private val _gradeTask = savedStateHandle.getStateFlow("task_grade",0)
    private val _activeDatePicker = savedStateHandle.getStateFlow("active_date_picker", false)
    private val _error = savedStateHandle.getStateFlow("error","")

    val state = combine(_idMainTask, _idSubTask, _showRangePicker, _firstRangeDate, _secondRangeDate, _gradeTask, _activeDatePicker, _error){
            params ->
        EditTaskState(
            idMainTask = params[0] as Int?,
            idSubtask = params[1] as Int?,
            showRangePicker = params[2] as Boolean,
            firstRangeDate = params[3] as String?,
            secondRangeDate = params[4] as String?,
            taskGrade = params[5] as Int,
            activeDatePicker = params[6] as Boolean,
            error = params[7] as String
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EditTaskState())


    private val _timeForm = mutableStateOf(TextFieldValue(""))
    val timeFormState = _timeForm
    private val _taskForm = mutableStateOf("")
    val taskFormState = _taskForm


    private val _mainTasksState = mutableStateOf(MainTasksState())
    val mainTasksState = _mainTasksState

    private val _taskState = mutableStateOf(TaskState())
    val taskState = _taskState

    private val _subTasksState = mutableStateOf(SubTasksByMainTaskState())
    val subTasksState = _subTasksState


    private val _hasBeenEdited = MutableStateFlow(false)
    val hasBeenEdited = _hasBeenEdited

    private var _subtask:SubTask? = null
    private var _mainTask:MainTask? = null

    private val _loadFirstMainTask = mutableStateOf<Int?>(null)
    val loadFirstMainTask = _loadFirstMainTask


    init {
        getMainTasks()
    }


    fun setupUpdateTask(){

        if(_idMainTask.value != null) getMainTaskById()
        if(_idSubTask.value != null) getSubtaskById()
        if(_idMainTask.value == null && _idSubTask.value == null) updateTask()

    }

    private fun updateTask(){

        val lastTask = taskState.value.task!!

        if(timeFormState.value.text.isEmpty() && !useCheckCorrectTime.execute(timeFormState.value.text)){
            savedStateHandle["error"] = "Incorrect time"
            return
        }
        if(taskFormState.value.isEmpty()){
            savedStateHandle["error"] = "Empty task"
            return
        }

        if(
            ((_idMainTask.value != null && _mainTask != null)||(_idMainTask.value == null))
            &&
            ((_idSubTask.value != null && _subtask != null)||(_idSubTask.value == null))
        )  {
            if(_firstRangeDate.value != null){
                viewModelScope.launch {
                    addLongTask()
                    useDeleteTask.execute(taskState.value.task!!)
                }
                return
            }


            val task = Task(
                id = lastTask.id,
                task = taskFormState.value.ifEmpty { lastTask.task },
                time = timeFormState.value.text.ifEmpty { lastTask.time },
                date = lastTask.date,
                idBigTask = _idMainTask.value,
                idSubTask = _idSubTask.value,
                grade = _gradeTask.value,
                status = false,
                mainTaskImage = if(_mainTask != null) _mainTask!!.imageSrc else null,
                mainTaskTitle = if(_mainTask != null) _mainTask!!.title else null,
                subtaskTitle = if(_subtask != null) _subtask!!.title else null,
                subtaskColor = if(_subtask != null) _subtask!!.color else null
            )

            usePushTaskToFirebase.execute(task)
            useUpdateTask.invoke(task).onEach { res ->
                when(res){
                    is Resource.Success -> {
                        doneEdit()
                    }
                    is Resource.Error -> return@onEach
                    is Resource.Loading -> return@onEach
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun getMainTaskById(){
        useGetMainTaskById.invoke(_idMainTask.value!!).onEach {res ->
            when(res){
                is Resource.Success -> {
                    _mainTask = res.data
                    updateTask()
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
    private fun getSubtaskById(){
        useGetSubtaskById.invoke(_idSubTask.value!!).onEach {res ->
            when(res){
                is Resource.Success -> {
                    _subtask = res.data
                    updateTask()
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun addLongTask(){
        val id  = useGetActuallyLongTaskId.execute()
        usePutActuallyLongTaskId.execute(id)
        val lastTask = taskState.value.task!!
        val longTask = LongTask(
            id = id,
            task =  taskFormState.value.ifEmpty { lastTask.task },
            startDate = _firstRangeDate.value,
            endDate = _secondRangeDate.value,
            idMainTask = _idMainTask.value,
            idSubtask = _idSubTask.value,
            mainTaskImage = if(_mainTask != null) _mainTask!!.imageSrc else null,
            mainTaskTitle = if(_mainTask != null) _mainTask!!.title else null,
            subtaskTitle = if(_subtask != null) _subtask!!.title else null,
            subtaskColor = if(_subtask != null) _subtask!!.color else null

        )
        viewModelScope.launch {
            useAddLongTask.execute(longTask)
            doneEdit()
        }
    }



    fun setTime(time:TextFieldValue){
        _timeForm.value = time
    }
    fun setTask(task:String){
        _taskForm.value = task
    }

    fun getActuallyDate():String{
        return useGetActualityDate.execute().toDateString()
    }
    fun compareDate(date: LocalDate): Boolean {
        return useCompareDateWithCurrent.execute(
            date,
            MoreThenCurrentDate
        ) || useCompareDateWithCurrent.execute(date, EqualsCurrentDate)
    }

    fun getSubTasks(id:Int){
         useGetSubtasksByMainTaskId.invoke(id).onEach { res ->
            when(res){
                is Resource.Loading -> _subTasksState.value = SubTasksByMainTaskState(isLoading = true)
                is Resource.Success -> {
                    _subTasksState.value = SubTasksByMainTaskState(subtasks = res.data!!)
                }
                is Resource.Error -> {
                    _subTasksState.value = SubTasksByMainTaskState(error = res.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTask(id:Int){
        useGetTaskById.invoke(id).onEach { res ->
            when(res){

                is Resource.Loading -> _taskState.value = TaskState(isLoading = true)
                is Resource.Success -> {
                    _timeForm.value = TextFieldValue(res.data!!.time)
                    _taskForm.value = res.data.task
                    _taskState.value = TaskState(task = res.data)
                    _loadFirstMainTask.value = taskState.value.task!!.idBigTask
                }
                is Resource.Error -> _taskState.value = TaskState(error = res.message!!)

            }
        }.launchIn(viewModelScope)
    }

    private fun getMainTasks(){
        useGetAllMainTasks.invoke().onEach { res ->
            when(res){

                is Resource.Loading -> _mainTasksState.value = MainTasksState(isLoading = true)
                is Resource.Success -> {
                    val list = res.data!!.toMutableList()
                    list.add(0, MainTask(-5,"","",""))
                    _mainTasksState.value = MainTasksState(tasks = list)
                }
                is Resource.Error -> _mainTasksState.value = MainTasksState(error = res.message!!)

            }
        }.launchIn(viewModelScope)
    }

    fun blockFirstMainTask(){
        _loadFirstMainTask.value = null
    }

    fun setSubtaskId(id:Int?){
        Log.d("sdfsdfsdf", id.toString())
        savedStateHandle["subtask_id"] = id
    }
    fun setMainTaskId(id:Int?){
        savedStateHandle["main_task_id"] = id
        setSubtaskId(null)
    }
    fun setRangeDates(first:String = "", second:String = "", nullable:Boolean = false){
        savedStateHandle["first_range_date"] = first
        savedStateHandle["second_range_date"] = second
        if (nullable){
            savedStateHandle["first_range_date"] = null
            savedStateHandle["second_range_date"] = null
        }
    }
    fun switchDatePickerActive(active:Boolean){
        savedStateHandle["active_date_picker"] = active
    }
    fun setTaskGrade(grade:Int){
        savedStateHandle["task_grade"] = grade
    }

    fun doneEdit(){
        _hasBeenEdited.value = true
    }

}
