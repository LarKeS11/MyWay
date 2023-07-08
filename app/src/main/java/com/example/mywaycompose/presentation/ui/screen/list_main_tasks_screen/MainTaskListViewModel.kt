package com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.usecase.main_tasks.UseGetAllMainTasks
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.presentation.ui.screen.list_main_tasks_screen.state.MainTasksState

import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainTaskListViewModel @Inject constructor(
    private val useGetAllMainTasks: UseGetAllMainTasks,
    private val useGetAuthFirebaseSession: UseGetAuthFirebaseSession
): ViewModel() {

    private val _mainTasksState = mutableStateOf(MainTasksState())
    val mainTasksState = _mainTasksState

    private val _hasBeenSelected = MutableStateFlow<Int?>(null)
    val hasBeenSelected = _hasBeenSelected

    val _authSession = useGetAuthFirebaseSession.execute()

    init {
        getMainTasks()
    }

    private fun getMainTasks(){
        useGetAllMainTasks.invoke().onEach { res ->
            when(res){
                is Resource.Loading -> _mainTasksState.value = MainTasksState(isLoading = true)
                is Resource.Success -> _mainTasksState.value = MainTasksState(success = res.data!!)
                is Resource.Error -> _mainTasksState.value = MainTasksState(error = res.message!!)
            }
        }.launchIn(viewModelScope)
    }

    fun movingToMainTaskDetail(id:Int){
        _hasBeenSelected.value = id
    }

}