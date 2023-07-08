package com.example.mywaycompose.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mywaycompose.data.local.database.entity.IdeaEntity

@Dao
interface IdeasDao {

    @Insert
    suspend fun addIdea(ideaEntity: IdeaEntity)

    @Delete
    suspend fun deleteIdea(ideaEntity: IdeaEntity)

    @Query("SELECT * FROM IdeaEntity")
    suspend fun getAllIdeas():List<IdeaEntity>

}