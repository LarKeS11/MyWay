package com.example.mywaycompose.data.remote.firebase

import com.example.mywaycompose.data.repository.firebase_models.*
import com.example.mywaycompose.domain.model.Idea
import com.example.mywaycompose.utils.Constants.EFFECTIVE_STAT_HISTORY_KIND
import com.example.mywaycompose.utils.Constants.EFFECTIVE_STAT_KIND
import com.example.mywaycompose.utils.Constants.IDEAS_KIND
import com.example.mywaycompose.utils.Constants.LONG_TASKS_KIND
import com.example.mywaycompose.utils.Constants.LONG_TASKS_STAT_KIND
import com.example.mywaycompose.utils.Constants.MAIN_TASKS_KIND
import com.example.mywaycompose.utils.Constants.START_DAY_USING_KIND
import com.example.mywaycompose.utils.Constants.SUBTASK_TASKS_KIND
import com.example.mywaycompose.utils.Constants.TASKS_KIND
import com.example.mywaycompose.utils.Constants.TASK_STAT_KIND
import com.example.mywaycompose.utils.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseService(
    private val signInClient: GoogleSignInClient,
    private val database: FirebaseDatabase,
    private val firebaseStorageReference: StorageReference
):FirebaseServiceInterface {

    private val auth = Firebase.auth
    private val tasksDatabaseReference = database.getReference("tasks_database")
    private val mainTasksDatabaseReference = database.getReference("main_tasks_database")
    private val subTasksDatabaseReference = database.getReference("sub_tasks_database")
    private val tasksStatisticsDatabaseReference = database.getReference("tasks_stat_database")
    private val mainTasksImageStorageReference = firebaseStorageReference.child("main_tasks_image_storage")
    private val idsDatabaseReference = database.getReference("ids")
    private val effectiveDatabaseReference = database.getReference("effective_database")
    private val effectiveHistoryDatabaseReference = database.getReference("effective_history_database")
    private val ideasDatabaseReference = database.getReference("ideas_database")
    private val longTasksDatabaseReference = database.getReference("long_tasks")
    private val longTasksStatReference = database.getReference("long_tasks_stat")


    private fun cleanLogin(email:String):String{
        return email.split("@")[0].replace('.','@')
    }
    private val email = if(auth.currentUser != null) cleanLogin(auth.currentUser!!.email!!.split("@")[0]) else ""

    override fun googleAuthRequest(): GoogleSignInClient {
        return signInClient
    }

    override fun firebaseAuthWithGoogle(idToken: String): com.google.android.gms.tasks.Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return auth.signInWithCredential(credential)
    }

    override fun getUserAuth(): FirebaseAuth {
        return auth
    }

    override fun getTasksDatabaseReference(): DatabaseReference {
        return tasksDatabaseReference
    }

    override fun firstUserMarkup() {

    }

    override fun pushTaskToDatabase(task: FirebaseTask) {
        tasksDatabaseReference.child(email).child(task.date!!).child(task.time!!).setValue(task)
    }

    override fun pushMainTaskToDatabase(mainTask: FirebaseMainTask) {
        mainTasksDatabaseReference.child(email).child(mainTask.id.toString()).setValue(mainTask)
    }

    override fun pushMainTaskImageFirebase(image: ByteArray, id: Int) {
        mainTasksImageStorageReference.child(email).child(id.toString()).putBytes(image)
    }

    override fun pushLongTaskStatToDatabase(longTask: FirebaseLongTaskStat) {
        longTasksStatReference.child(email).child(longTask.id_task.toString()).child(longTask.date!!).setValue(longTask)
    }

    override fun getMainTasksDatabaseRef(): DatabaseReference {
        return mainTasksDatabaseReference.child(email)
    }

    override fun getImageReference(id: Int):StorageReference {
        return mainTasksImageStorageReference.child(email).child(id.toString())
    }

    override fun deleteTask(task: FirebaseTask) {
        tasksDatabaseReference.child(email).child(task.date!!).child(task.time!!).removeValue()
    }


    override fun deleteMainTask(mainTask: FirebaseMainTask) {
        mainTasksDatabaseReference.child(email).child(mainTask.id.toString()).removeValue()
    }

    override fun putActuallyMainTaskId(id: Int) {
        idsDatabaseReference.child(email).child("main_task_id").setValue(id)
    }

    override fun putActuallySubtaskId(id:Int) {
        idsDatabaseReference.child(email).child("sub_task_id").setValue(id)
    }

    override fun putActuallyStatisticsId(id: Int) {
        idsDatabaseReference.child(email).child("statistics_id").setValue(id)
    }

    override fun putActuallyLongTaskId(id: Int) {
        idsDatabaseReference.child(email).child("long_task_id").setValue(id)
    }

    override fun getIdsDatabaseReference(): DatabaseReference {
        return idsDatabaseReference.child(email)
    }

    override fun pushSubtaskToDatabase(subTask: FirebaseSubtask) {
        subTasksDatabaseReference.child(email).child(subTask.mainTaskId.toString()).child(subTask.id.toString()).setValue(subTask)
    }

    override fun getSubtasksDatabaseRef(): DatabaseReference {
        return subTasksDatabaseReference.child(email)
    }

    override fun deletePhotoById(id: Int) {
        mainTasksImageStorageReference.child(email).child(id.toString()).delete()
    }

    override fun cleanSubtasksByMainTaskId(id: Int) {
        subTasksDatabaseReference.child(email).child(id.toString()).removeValue()
    }

    override fun pushTaskStatistic(taskStatistic: FirebaseTaskStat) {
        tasksStatisticsDatabaseReference.child(email).push().setValue(taskStatistic)
    }

    override fun getTasksStatisticsDatabaseReference(): DatabaseReference {
        return tasksStatisticsDatabaseReference.child(email)
    }

    override fun putCurrentEffectiveValue(value: Int) {
        effectiveDatabaseReference.child(email).setValue(value.toString())
    }

    override fun deleteIdea(idea: FirebaseIdea) {
        ideasDatabaseReference.child(email).child(idea.idea!!).removeValue()
    }

    override fun putCurrentDayEffective(date: String, value: Int) {
        effectiveHistoryDatabaseReference.child(email).child(date).setValue(value)
    }


    override fun pushIdea(idea: FirebaseIdea) {
        ideasDatabaseReference.child(email).child(idea.idea!!).setValue(idea)
    }



    override fun addLongTask(firebaseLongTask: FirebaseLongTask) {
        longTasksDatabaseReference.child(email).child(firebaseLongTask.task!!).setValue(firebaseLongTask)
    }

    override fun deleteLongTask(firebaseLongTask: FirebaseLongTask) {
        longTasksDatabaseReference.child(email).child(firebaseLongTask.task!!).removeValue()
    }

    override fun getCurrentIdBySomeModel(kind:String): Flow<Int> = callbackFlow{
        var listener: ValueEventListener? = null
        idsDatabaseReference.child(email).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listener = this
                var fl = true
                for (i in snapshot.children) {
                    val key = i.key
                    if (key == kind) {
                        fl = false
                        trySend(i.value.toString().toInt())
                        channel.close()
                        idsDatabaseReference.removeEventListener(this)
                    }
                }
                if(fl){
                    trySend(0)
                    channel.close()
                    idsDatabaseReference.removeEventListener(this)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        awaitClose {
            if(listener != null) idsDatabaseReference.removeEventListener(listener!!)
        }
    }

    override fun getEffectiveStatisticsByDay(date: String): Flow<Int> = callbackFlow {
        var listener: ValueEventListener? = null

        var flag = true

        effectiveHistoryDatabaseReference.child(email).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listener = this
                for(day in snapshot.children){
                    if(day.key == date) {
                        trySend(day.value.toString().toInt())
                        flag = false
                    }
                }

                if(flag) trySend(0)

                channel.close()
                effectiveHistoryDatabaseReference.removeEventListener(this)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        awaitClose {
            if(listener != null) effectiveHistoryDatabaseReference.removeEventListener(listener!!)
        }
    }
    override fun putFirstDate(date: String) {
        database.getReference(START_DAY_USING_KIND).child(email).setValue(date)
    }
    override fun <T> getAppData(kind:String): Flow<List<T>> = callbackFlow {
        val database = database.getReference(kind)
        var listener: ValueEventListener? = null
        database.child(email).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listener = this
                when(kind){
                    TASKS_KIND -> {
                        val list = mutableListOf<FirebaseTask>()
                        for(day in snapshot.children){
                            for(task in day.children){
                                list.add(task.getValue(FirebaseTask::class.java)!!)
                            }
                        }
                        trySend(list as List<T>)
                    }
                    MAIN_TASKS_KIND -> {
                        val list = mutableListOf<Pair<Int,FirebaseMainTask>>()
                        for(task in snapshot.children){
                            list.add(Pair(task.key!!.toInt(), task.getValue(FirebaseMainTask::class.java)!!))
                        }
                        trySend(list as List<T>)
                    }
                    LONG_TASKS_STAT_KIND -> {
                        val list = mutableListOf<FirebaseLongTaskStat>()
                        for(long_task in snapshot.children){
                            for(stat in long_task.children){
                                list.add(stat.getValue(FirebaseLongTaskStat::class.java)!!)
                            }
                        }
                        trySend(list as List<T>)
                    }

                    SUBTASK_TASKS_KIND -> {
                        val list = mutableListOf<FirebaseSubtask>()
                        for(subtasks in snapshot.children){
                            for(subtask in subtasks.children){
                                list.add(subtask.getValue(FirebaseSubtask::class.java)!!)
                            }
                        }
                        trySend(list as List<T>)
                    }

                    TASK_STAT_KIND -> {
                        val list = mutableListOf<FirebaseTaskStat>()
                        for(stat in snapshot.children){
                            list.add(stat.getValue(FirebaseTaskStat::class.java)!!)
                        }
                        trySend(list as List<T>)
                    }
                    EFFECTIVE_STAT_KIND -> {
                        if(snapshot.value.toString() == "null") trySend(listOf(0) as List<T>) else trySend(listOf(snapshot.value.toString().toInt()) as List<T>)
                    }
                    START_DAY_USING_KIND -> {
                         trySend(listOf(snapshot.value.toString()) as List<T>)
                    }
                    EFFECTIVE_STAT_HISTORY_KIND -> {
                        val stat = mutableListOf<Pair<String, Int>>()

                        for(day in snapshot.children){
                            stat.add(Pair(day.key!!, day.value.toString().toInt()))
                        }

                        trySend(stat as List<T>)
                    }
                    LONG_TASKS_KIND -> {
                        val list = mutableListOf<FirebaseLongTask>()
                        for(stat in snapshot.children){
                            list.add(stat.getValue(FirebaseLongTask::class.java)!!)
                        }
                        trySend(list as List<T>)
                    }
                    IDEAS_KIND -> {
                        val out = mutableListOf<FirebaseIdea>()
                        for (idea in snapshot.children) {
                            out.add(
                                idea.getValue(FirebaseIdea::class.java)!!
                            )
                        }
                        trySend(out as List<T>)
                    }
                }
                channel.close()
                database.removeEventListener(this)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        awaitClose {
            if(listener != null) database.removeEventListener(listener!!)
        }
    }




}