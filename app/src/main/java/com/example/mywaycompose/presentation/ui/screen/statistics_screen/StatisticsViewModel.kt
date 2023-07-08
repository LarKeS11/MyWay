package com.example.mywaycompose.presentation.ui.screen.statistics_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaycompose.domain.usecase.remote_service.UseGetAuthFirebaseSession
import com.example.mywaycompose.domain.usecase.statistics.UseGetAllTasksStatistic
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticValueFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UseGetEffectiveStatisticsByDaysFromFirebase
import com.example.mywaycompose.domain.usecase.statistics.UseGetTasksStatisticByDate
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.state.CompareTasksStatisticState
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.state.EffectiveStatisticsState
import com.example.mywaycompose.presentation.ui.screen.statistics_screen.state.TasksStatisticsState
import com.example.mywaycompose.utils.Constants.FirstStatistics
import com.example.mywaycompose.utils.Constants.SecondStatistics
import com.example.mywaycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useGetTasksStatisticByDate: UseGetTasksStatisticByDate,
    private val useGetAllTasksStatistic: UseGetAllTasksStatistic,
    private val useGetAuthFirebaseSession: UseGetAuthFirebaseSession,
    private val useGetEffectiveStatisticValueFromFirebase: UseGetEffectiveStatisticValueFromFirebase,
    private val useGetEffectiveStatisticsByDaysFromFirebase: UseGetEffectiveStatisticsByDaysFromFirebase
):ViewModel() {

    private val _yourselfTasksStatisticState =  mutableStateOf(TasksStatisticsState())
    val yourselfTasksStatisticState = _yourselfTasksStatisticState

    private val _usuallyTasksStatisticState = mutableStateOf(TasksStatisticsState())
    val usuallyTasksStatisticState = _usuallyTasksStatisticState

    private val _compareTasksStatistic = mutableStateOf(CompareTasksStatisticState())
    val compareTasksStatistic = _compareTasksStatistic

    private val _effectiveValueState = mutableStateOf("")
    val effectiveValueState = _effectiveValueState

    private val _effectiveStatisticsState = mutableStateOf(EffectiveStatisticsState())
    val effectiveStatisticsState = _effectiveStatisticsState

    val _authSession = useGetAuthFirebaseSession.execute()

    init {
        getTasksStatistics(FirstStatistics)
        getTasksStatistics(SecondStatistics)
        getEffectiveValue()
        getEffectiveStatistics()
    }

    fun getCompareStatistic(){
        val blueStat = _yourselfTasksStatisticState.value.tasksStatistic.reversed()[0]
        val redStat = _usuallyTasksStatisticState.value.tasksStatistic.reversed()[0]
        compareTasksStatistic.value = CompareTasksStatisticState(tasksStatistic = Pair(blueStat,redStat))
    }

    private fun getEffectiveStatistics(){
        useGetEffectiveStatisticsByDaysFromFirebase.execute().onEach { res ->
            when(res){
                is Resource.Loading -> _effectiveStatisticsState.value = EffectiveStatisticsState(isLoading = true)
                is Resource.Success -> effectiveStatisticsState.value = EffectiveStatisticsState(data = res.data!!)
                else -> {}
            }


        }.launchIn(viewModelScope)
    }

    private fun getEffectiveValue(){
        useGetEffectiveStatisticValueFromFirebase.execute().onEach {
            _effectiveValueState.value = it.data.toString()
        }.launchIn(viewModelScope)
    }

    private fun getTasksStatistics(kind:Int){

        useGetAllTasksStatistic.invoke(kind).onEach { res ->
            when(res){
                is Resource.Loading -> {
                    when(kind){
                        FirstStatistics -> _yourselfTasksStatisticState.value = TasksStatisticsState(isLoading = true)
                        SecondStatistics -> _usuallyTasksStatisticState.value = TasksStatisticsState(isLoading = true)
                    }
                }
                is Resource.Success -> {

                   val splitArray =  res.data!!.foldIndexed(ArrayList<ArrayList<Pair<String, Float>>>(res.data.size / 2)) { index, acc, item ->
                        if (index % 7 == 0) {
                            acc.add(ArrayList(2))
                        }
                        acc.last().add(item)
                        acc
                    }

                    when(kind){
                        FirstStatistics -> {
                            _yourselfTasksStatisticState.value =
                                TasksStatisticsState(tasksStatistic = splitArray.toList().map { it.reversed() }.reversed())
                            getCompareStatistic()
                        }
                        SecondStatistics -> {
                            _usuallyTasksStatisticState.value =
                                TasksStatisticsState(tasksStatistic =  splitArray.toList().map { it.reversed() }.reversed())
                            getCompareStatistic()
                        }
                    }
                }
                is Resource.Error -> {
                    when(kind){
                        FirstStatistics -> _yourselfTasksStatisticState.value = TasksStatisticsState(error = res.message!!)
                        SecondStatistics -> _usuallyTasksStatisticState.value = TasksStatisticsState(error = res.message!!)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

}