package com.example.mywaycompose.data.local.database.service

import android.content.Context
import android.util.Log
import com.example.mywaycompose.data.local.database.dao.IdeasDao
import com.example.mywaycompose.data.local.database.dao.LongTasksDao
import com.example.mywaycompose.data.local.database.dao.MainTasksDao
import com.example.mywaycompose.data.local.database.dao.SubTasksDao
import com.example.mywaycompose.data.local.database.dao.TasksDao
import com.example.mywaycompose.data.local.database.dao.TasksStatisticsDao
import com.example.mywaycompose.data.local.database.entity.IdeaEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskEntity
import com.example.mywaycompose.data.local.database.entity.LongTaskStatEntity
import com.example.mywaycompose.data.local.database.entity.MainTaskEntity
import com.example.mywaycompose.data.local.database.entity.SubTaskEntity
import com.example.mywaycompose.data.local.database.entity.TaskEntity
import com.example.mywaycompose.data.local.database.entity.TaskStatisticEntity
import com.example.mywaycompose.domain.model.LongTask
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DatabaseService @Inject constructor(
    private val tasksDao: TasksDao,
    private val mainTasksDao: MainTasksDao,
    private val subTasksDao: SubTasksDao,
    private val tasksStatisticsDao: TasksStatisticsDao,
    private val longTasksDao: LongTasksDao,
    private val context: Context,
    private val ideasDao: IdeasDao
): DatabaseServiceInterface {


    override suspend fun addTask(task: TaskEntity) {
        tasksDao.addTask(task)
    }

    override suspend fun getTasks(date:String): List<TaskEntity> {
        return sortTasksByTime(tasksDao.getTasksByDate(date))
    }

    override suspend fun getTaskById(id: Int): TaskEntity {
        return tasksDao.getTaskById(id)
    }

    override suspend fun updateTask(task: TaskEntity) {
        Log.d("ergfdsdfgf",task.grade.toString())
        tasksDao.updateTask(task)
    }

    override suspend fun isThereTheSameTask(time: String, date: String):Boolean {
        val task = tasksDao.isThereTheSame(time, date)
        return (task == null)
    }

    private fun getSeconds(time:LocalTime):Int{
        val h = time.hour
        val m = time.minute
        return (h * 3600) + (m * 60)
    }

    override suspend fun sortTasksByTime(tasks: List<TaskEntity>): List<TaskEntity> {
        Log.d("wedfwdfedgf",tasks[0].grade.toString())

        return tasks.sortedBy {
            Log.d("wedfwdfedgf",it.grade.toString())
            val parser = DateTimeFormatter.ofPattern(if(it.time.length == 5) "HH:mm" else "H:mm")
            getSeconds(LocalTime.parse(it.time, parser))
        }

    }

    override suspend fun deleteTask(task: TaskEntity) {
        tasksDao.removeTask(task)
    }

    override suspend fun getAllTasks(): List<TaskEntity> {
        return tasksDao.getAllTasks()
    }

    override suspend fun cleanTasksDatabase() {
        tasksDao.cleanTasksDatabase()
    }

    override suspend fun addMainTask(mainTaskEntity: MainTaskEntity) {
        Log.d("wergvdswerg",mainTaskEntity.id.toString())
        mainTasksDao.addMainTask(mainTaskEntity)
    }

    override suspend fun getAllMainTasks(): List<MainTaskEntity> {
        return mainTasksDao.getAllMainTasks()
    }

    override suspend fun getMainTaskById(id:Int): MainTaskEntity {
        return mainTasksDao.getMainTaskById(id)
    }

    override suspend fun getMainTaskByTitle(title: String): MainTaskEntity {
        return mainTasksDao.getMainTaskByTitle(title)
    }

    override suspend fun deleteMainTask(task: MainTaskEntity) {
        mainTasksDao.deleteMainTask(task)
    }

    override suspend fun updateMainTask(task: MainTaskEntity) {
        mainTasksDao.updateMainTask(task)
    }

    override suspend fun deleteAllMainTasks() {
        mainTasksDao.deleteAllMainTasks()
    }


    override suspend fun addSubtask(subTaskEntity: SubTaskEntity) {
        subTasksDao.addSubtaskTask(subTaskEntity)
    }

    override suspend fun getSubtasksByMainTaskId(id: Int):List<SubTaskEntity> {
        return subTasksDao.getSubtasksByMainTaskId(id)
    }

    override suspend fun getSubtaskById(id: Int): SubTaskEntity {
        val it = subTasksDao.getSubtaskById(id)
        Log.d("vvvvdfsdf","${it.id} ${it.title} ${it.color} ${it.mainTaskId}")

        return it
    }

    override suspend fun deleteSubtasksByMainTaskId(id: Int) {
        subTasksDao.deleteSubtasksByMainTaskId(id)
    }

    override suspend fun addTaskToStatistic(taskStatisticEntity: TaskStatisticEntity) {
        tasksStatisticsDao.addTaskToStatistic(taskStatisticEntity)
    }

    override suspend fun getTasksStatisticByDate(date:String): List<TaskStatisticEntity> {
        return tasksStatisticsDao.getTasksStatisticByDate(date)
    }

    override suspend fun getAllTasksStatistic(): List<TaskStatisticEntity> {
        return tasksStatisticsDao.getAllTasksStatistic()
    }

    override suspend fun getTasksStatisticByMainTaskId(id:Int): List<TaskStatisticEntity> {
        return tasksStatisticsDao.getStatisticByMainTaskId(id)
    }

    override suspend fun insertLongTaskStat(longTaskStat: LongTaskStatEntity) {
        Log.d("outpusdf","###########")
        longTasksDao.insertLongTaskStat(longTaskStat)
    }

    override suspend fun getLongTaskStatById(id: Int): List<LongTaskStatEntity> {
        return longTasksDao.getLongTaskStatById(id)
    }

    override suspend fun deleteAllTables() {
        context.deleteDatabase("myway_database")
    }

    override suspend fun addIdea(ideaEntity: IdeaEntity) {
        ideasDao.addIdea(ideaEntity)
    }

    override suspend fun getIdeas(): List<IdeaEntity> {
        return ideasDao.getAllIdeas()
    }

    override suspend fun deleteIdea(ideaEntity: IdeaEntity) {
        ideasDao.deleteIdea(ideaEntity)
    }

    override suspend fun addLongTask(longTaskEntity: LongTaskEntity) {

        Log.d("fgdfgdfgsdfgdf",longTaskEntity.id.toString())

        longTasksDao.insertLongTask(longTaskEntity)
    }

    override suspend fun getLongTasks(): List<LongTaskEntity> {
        return longTasksDao.getLongTasks()
    }

    override suspend fun deleteLongTask(longTask: LongTaskEntity) {
        longTasksDao.deleteLongTask(longTask)
    }

    override suspend fun findSameLongTaskStat(id: Int, date: String):Boolean {
        return longTasksDao.findSameStat(id, date) != null
    }
}