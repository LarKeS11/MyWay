package com.example.mywaycompose.presentation.ui.screen.auth_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.usecase.ideas.UseAddIdea
import com.example.mywaycompose.domain.usecase.ideas.UseGetIdeasFromFirebase
import com.example.mywaycompose.domain.usecase.local_service.UseClearDatabase
import com.example.mywaycompose.domain.usecase.local_service.UseGetImageFileById
import com.example.mywaycompose.domain.usecase.local_service.UseSaveFirstDate
import com.example.mywaycompose.domain.usecase.long_tasks.*
import com.example.mywaycompose.domain.usecase.main_tasks.UseAddShortMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskFromFirebase
import com.example.mywaycompose.domain.usecase.main_tasks.UseSaveImageFromFirebase
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.remote_service.UseGetCurrentIdByModelKind
import com.example.mywaycompose.domain.usecase.remote_service.UseGetStartUsingDate
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyMainTaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallyStatisticsId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutActuallySubtaskId
import com.example.mywaycompose.domain.usecase.remote_service.UsePutStartUsingDateToFirebase
import com.example.mywaycompose.domain.usecase.statistics.UseAddTaskToStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseAddTasksStatisticsFromFirebaseToLocalDatabase
import com.example.mywaycompose.domain.usecase.statistics.UseGetTasksStatisticsFromFirebase
import com.example.mywaycompose.domain.usecase.subtasks.UseAddSubtask
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksFromFirebase
import com.example.mywaycompose.domain.usecase.tasks.UseAddTask
import com.example.mywaycompose.domain.usecase.tasks.UseGetTasksFromFirebase
import com.example.mywaycompose.presentation.ui.screen.auth_screen.state.SplashScreenState
import com.example.mywaycompose.utils.Constants.LONG_TASK_ID_KIND
import com.example.mywaycompose.utils.Constants.MAIN_TASK_ID_KIND
import com.example.mywaycompose.utils.Constants.SUBTASK_ID_KIND
import com.example.mywaycompose.utils.Constants.TASK_STAT_ID_KIND
import com.example.mywaycompose.utils.Constants.TASK_STAT_KIND
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useGetAuthFirebaseSession:UseGetAuthFirebaseSession,
    private val useGetTasksFromFirebase: UseGetTasksFromFirebase,
    private val useAddTask: UseAddTask,
    private val useClearDatabase: UseClearDatabase,
    private val useGetMainTaskFromFirebase: UseGetMainTaskFromFirebase,
    private val useAddShortMainTask: UseAddShortMainTask,
    private val useGetImageFileById: UseGetImageFileById,
    private val useSaveImageFromFirebase: UseSaveImageFromFirebase,
    private val useGetSubtasksFromFirebase: UseGetSubtasksFromFirebase,
    private val useAddSubtask: UseAddSubtask,
    private val useGetIdeasFromFirebase: UseGetIdeasFromFirebase,
    private val useAddIdea: UseAddIdea,
    private val useGetTasksStatisticsFromFirebase: UseGetTasksStatisticsFromFirebase,
    private val useAddTaskToStatistic: UseAddTaskToStatistic,
    private val useGetCurrentIdByModelKind: UseGetCurrentIdByModelKind,
    private val usePutActuallyMainTaskId: UsePutActuallyMainTaskId,
    private val usePutActuallyStatisticsId: UsePutActuallyStatisticsId,
    private val usePutActuallySubtaskId: UsePutActuallySubtaskId,
    private val useGetLongTasksFromFirebase: UseGetLongTasksFromFirebase,
    private val useAddLongTask: UseAddLongTask,
    private val usePutStartUsingDateToFirebase: UsePutStartUsingDateToFirebase,
    private val useGetStartUsingDate: UseGetStartUsingDate,
    private val useAddTasksStatisticsFromFirebaseToLocalDatabase: UseAddTasksStatisticsFromFirebaseToLocalDatabase,
    private val useSaveFirstDate: UseSaveFirstDate,
    private val usePutActuallyLongTaskId: UsePutActuallyLongTaskId,
    private val useGetLongTasksStatFromFirebase: UseGetLongTasksStatFromFirebase,
    private val useCheckLongTask: UseCheckLongTask
):ViewModel() {

    private val tasksHasBeenLoaded = savedStateHandle.getStateFlow("tasks_status",false)
    private val mainTasksHasBeenLoaded = savedStateHandle.getStateFlow("main_tasks_status",false)
    private val subtasksHasBeenLoaded = savedStateHandle.getStateFlow("subtasks_status", false)
    private val ideasHasBeenLoaded = savedStateHandle.getStateFlow("ideas_status",false)
    private val tasksStatisticsHasBeenLoaded = savedStateHandle.getStateFlow("stat_status",false)
    private val actuallyMainTaskIdHasBeenLoaded = savedStateHandle.getStateFlow("actually_main_task_id_status",false)
    private val actuallySubtaskIdHasBeenLoaded = savedStateHandle.getStateFlow("actually_subtask_id_status",false)
    private val actuallyTasksStatisticsIdHasBeenLoaded = savedStateHandle.getStateFlow("actually_tasks_statistics_id_status",false)
    private val longTasksHasBeenLoaded = savedStateHandle.getStateFlow("long_tasks_status",false)
    private val longTasksIdHasBeenLoaded = savedStateHandle.getStateFlow("long_tasks_id", false)
    private val longTasksStatHasBeenLoaded = savedStateHandle.getStateFlow("long_tasks_stat",false)

    val state = combine(
        tasksHasBeenLoaded,
        mainTasksHasBeenLoaded,
        subtasksHasBeenLoaded,
        ideasHasBeenLoaded,
        tasksStatisticsHasBeenLoaded,
        actuallyMainTaskIdHasBeenLoaded,
        actuallySubtaskIdHasBeenLoaded,
        actuallyTasksStatisticsIdHasBeenLoaded,
        longTasksHasBeenLoaded,
        longTasksIdHasBeenLoaded,
        longTasksStatHasBeenLoaded
    ){ props ->
        SplashScreenState(
            tasksHasBeenLoaded = props[0],
            mainTasksHasBeenLoaded = props[1],
            subtasksHasBeenLoaded = props[2],
            ideasHasBeenLoaded = props[3],
            tasksStatisticsHasBeenLoaded = props[4],
            actuallyMainTaskIdHasBeenLoaded = props[5],
            actuallySubtaskIdHasBeenLoaded = props[6],
            actuallyTasksStatisticsIdHasBeenLoaded =props[7],
            longTasksHasBeenLoaded = props[8],
            actuallyLongTasksIdHasBeenLoaded = props[9],
            longTasksStatHasBeenLoaded = props[10]

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashScreenState())

    private val _firstLaunch = useGetAuthFirebaseSession.execute().currentUser == null
    val firstLaunch = _firstLaunch


    init {
        viewModelScope.launch {
            useClearDatabase.execute()
        }
        if(useGetAuthFirebaseSession.execute().currentUser != null){
            useGetStartUsingDate.invoke().onEach {
                if(it == "null") usePutStartUsingDateToFirebase.execute()
                else useSaveFirstDate.execute(it)
            }.launchIn(viewModelScope)
        }
    }

    private fun setTasksLoadingDone(){
        savedStateHandle["tasks_status"] = true
    }

    private fun setMainTasksLoadingDone(){
        savedStateHandle["main_tasks_status"] = true
    }

    private fun setSubtasksLoadingDone(){
        savedStateHandle["subtasks_status"] = true
    }

    private fun setIdeasLoadingDone(){
        savedStateHandle["ideas_status"] = true
    }

    private fun setTasksStatisticsLoadingDone(){
        Log.d("edfgbgf","wdfw")
        savedStateHandle["stat_status"] = true
    }

    private fun setLongTasksLoadingDone(){
        savedStateHandle["long_tasks_status"] = true
    }

    fun loadTasks(){
        useGetTasksFromFirebase.invoke().onEach { res ->
            when(res){
                is Resource.Success -> {
                    res.data!!.forEach {task ->
                        Log.d("wedfgbfdd",task.task)
                        if(task.idBigTask != null){
                            val file = useGetImageFileById.execute(task.idBigTask)
                            task.mainTaskImage = file.absolutePath
                        }
                        useAddTask.execute(task)
                    }
                    setTasksLoadingDone()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadMainTasks(){
        useGetMainTaskFromFirebase.invoke().onEach {res ->
            when(res){
                is Resource.Success -> {
                    Log.d("fsdfsdfsdf","sdfdsfsdf")
                    res.data!!.forEach {pair_task ->
                        val task = pair_task.second
                        useSaveImageFromFirebase.execute(pair_task.first)
                        val file = useGetImageFileById.execute(pair_task.first)
                        task.imageSrc = file.absolutePath
                        useAddShortMainTask.execute(task)
                    }
                    setMainTasksLoadingDone()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadSubtasks(){
        useGetSubtasksFromFirebase.invoke().onEach {res ->
            when(res){
                is Resource.Success -> {
                    res.data!!.forEach {
                        Log.d("efgbvcdfg","${it.id} ${it.title} ${it.color} ${it.mainTaskId}")
                        useAddSubtask.execute(it)
                    }
                    setSubtasksLoadingDone()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadIdeas(){
        useGetIdeasFromFirebase.invoke().onEach {res ->
            when(res){

                is Resource.Success -> {
                    res.data!!.forEach {
                        useAddIdea.execute(it)
                    }
                    setIdeasLoadingDone()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadTasksStatistics(){
        useGetTasksStatisticsFromFirebase.invoke().onEach {res ->
            when(res){

                is Resource.Success -> {
                    res.data!!.forEach {
                        useAddTasksStatisticsFromFirebaseToLocalDatabase.execute(it)
                    }
                    setTasksStatisticsLoadingDone()
                }

            }
        }.launchIn(viewModelScope)
    }

    fun loadLongTasks(){
        useGetLongTasksFromFirebase.invoke().onEach {res ->
            when(res){
                is Resource.Success -> {
                    res.data!!.forEach {
                        useAddLongTask.execute(it)
                    }
                    setLongTasksLoadingDone()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadLongTasksStat(){
        useGetLongTasksStatFromFirebase.invoke().onEach { res ->
            when(res){
                is Resource.Success -> {
                    res.data!!.forEach{
                        useCheckLongTask.execute(it)
                    }
                    savedStateHandle["long_tasks_stat"] = true
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIds(){
        listOf(MAIN_TASK_ID_KIND, SUBTASK_ID_KIND, TASK_STAT_ID_KIND, LONG_TASK_ID_KIND).forEach { kind ->
                useGetCurrentIdByModelKind.invoke(kind).onEach {id ->
                    when(kind){
                        MAIN_TASK_ID_KIND -> {
                            usePutActuallyMainTaskId.execute(id.data!!)
                            savedStateHandle["actually_main_task_id_status"] = true
                        }
                        SUBTASK_ID_KIND -> {
                            usePutActuallySubtaskId.execute(id.data!!)
                            savedStateHandle["actually_subtask_id_status"] = true
                        }
                        TASK_STAT_ID_KIND -> {
                            usePutActuallyStatisticsId.execute(id.data!!)
                            savedStateHandle["actually_tasks_statistics_id_status"] = true
                        }
                        LONG_TASK_ID_KIND -> {
                            usePutActuallyLongTaskId.execute(id.data!!, true)
                            savedStateHandle["long_tasks_id"] = true
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }


}