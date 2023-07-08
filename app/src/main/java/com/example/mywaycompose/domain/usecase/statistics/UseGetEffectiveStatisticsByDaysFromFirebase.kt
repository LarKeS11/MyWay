package com.example.mywaycompose.domain.usecase.statistics

import com.example.mywaycompose.domain.repository.service.RemoteServiceRepository
import com.example.mywaycompose.domain.repository.statistics.RemoteStatisticsRepository
import com.example.mywaycompose.utils.Constants.EFFECTIVE_STAT_HISTORY_KIND
import com.example.mywaycompose.utils.Resource
import com.google.firebase.database.core.SnapshotHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UseGetEffectiveStatisticsByDaysFromFirebase(
    private val remoteServiceRepository: RemoteServiceRepository,
    private val coroutineScope: CoroutineScope
) {

    fun execute():Flow<Resource<List<Pair<String, Int>>>> = callbackFlow{

        var open_flow:ProducerScope<Resource<List<Pair<String, Int>>>>? = null

        remoteServiceRepository.getAppData<Pair<String, Int>>(EFFECTIVE_STAT_HISTORY_KIND).onEach {res ->
            open_flow = this
            trySend(Resource.Success(res.sortedBy {
                val date = LocalDate.parse(it.first.filter { !it.isWhitespace() }, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                date.toEpochDay()
            }))
            this.close()
        }.launchIn(coroutineScope)

        awaitClose{open_flow!!.close()}
    }

}