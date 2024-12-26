package com.example.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    //select
    @Query("Select * from notes_tbl")
    //now this is asynchronous
    fun getAllNotes(): Flow<List<Note>>

    //Insertion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    //Update
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    //Deletion
    @Query("Delete from notes_tbl")
    suspend fun deleteAll()

    //Deletion only 1
    @Delete
    suspend fun deleteNote(note:Note)



}