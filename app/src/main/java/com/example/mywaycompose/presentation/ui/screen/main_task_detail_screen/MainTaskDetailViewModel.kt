package com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.model.MainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseDeleteMainTask
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskById
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetMainTaskStatistic
import com.example.mywaycompose.domain.usecase.subtasks.UseGetSubtasksByMainTaskId
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state.MainTaskDetailState
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state.MainTaskStatisticState
import com.example.mywaycompose.presentation.ui.screen.main_task_detail_screen.state.SubtasksState
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTaskDetailViewModel @Inject constructor(
    private val useGetMainTaskById: UseGetMainTaskById,
    private val useGetSubtasksByMainTaskId: UseGetSubtasksByMainTaskId,
    private val useGetMainTaskStatistic: UseGetMainTaskStatistic,
    private val useDeleteMainTask: UseDeleteMainTask
):ViewModel() {

    private val _mainTaskDetailState = mutableStateOf(MainTaskDetailState())
    val mainTaskDetailState = _mainTaskDetailState

    private val _subtasksState = mutableStateOf(SubtasksState())
    val subtasksState = _subtasksState

    private val _mainTaskStatisticState = mutableStateOf(MainTaskStatisticState())
    val mainTaskStatisticState = _mainTaskStatisticState

    private val _toMainTasks = mutableStateOf(false)
    val toMainTasks = _toMainTasks

    private val _toEditMainTask = mutableStateOf(false)
    val toEditMainTask = _toEditMainTask

    private val _activeMainTaskOptionsState = mutableStateOf(false)
    val activeMainTaskOptionsState = _activeMainTaskOptionsState

    fun getSubtasks(id:Int){

        useGetSubtasksByMainTaskId.invoke(id).onEach {res ->
            when(res){
                is Resource.Loading -> _subtasksState.value = SubtasksState(isLoading = true)
                is Resource.Success -> _subtasksState.value = SubtasksState(tasks = res.data!!)
                is Resource.Error -> _subtasksState.value = SubtasksState(error = res.message!!)
            }
        }.launchIn(viewModelScope)

    }

    fun getTaskStatistic(id:Int){

        useGetMainTaskStatistic.invoke(id).onEach { res ->
            when(res){
                is Resource.Loading -> _mainTaskStatisticState.value = MainTaskStatisticState(isLoading = true)
                is Resource.Success -> {
                    val splitArray =  res.data!!.foldIndexed(ArrayList<ArrayList<Pair<String, Float>>>(res.data.size / 2)) { index, acc, item ->
                        if (index % 7 == 0) {
                            acc.add(ArrayList(2))
                        }
                        acc.last().add(item)
                        acc
                    }
                    _mainTaskStatisticState.value = MainTaskStatisticState(stat = splitArray)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getTaskById(id:Int){
        useGetMainTaskById.invoke(id).onEach { res ->
            when(res){
                is Resource.Loading -> _mainTaskDetailState.value = MainTaskDetailState(isLoading = true)
                is Resource.Success -> {
                    _mainTaskDetailState.value = MainTaskDetailState(task = res.data)
                }
                is Resource.Error -> _mainTaskDetailState.value = MainTaskDetailState(error = res.message!!)
            }

        }.launchIn(viewModelScope)
    }

    fun deleteTask(mainTask: MainTask){
        viewModelScope.launch {
            useDeleteMainTask.execute(mainTask)
            toMainTasks()
        }
    }

    fun toMainTasks(){
        _toMainTasks.value = true
    }

    fun toEditMainTask(bool:Boolean){
        _toEditMainTask.value = bool
    }

    fun switchOptionsMenuActive(){
        _activeMainTaskOptionsState.value = !activeMainTaskOptionsState.value
    }


}