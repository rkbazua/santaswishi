package com.rbazua.wishipets.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoryDao {
    @Insert
    suspend fun insertAll(vararg stories: Story): List<Long>

    @Query("SELECT * FROM story")
    suspend fun getAllStories(): List<Story>

    @Query("SELECT * FROM story WHERE pet_uid = :petuid")
    suspend fun getStory(petuid: String): Story

    @Query("DELETE FROM story")
    suspend fun deleteAllStories()
}