package com.hfad.alarmclock.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: Alarm)

    @Update
    suspend fun update(alarm: Alarm)

    @Delete
    suspend fun delete(alarm: Alarm)

    @Query("SELECT * FROM alarm_table LIMIT 1")
    fun get(): LiveData<Alarm>

    @Query("DELETE FROM alarm_table")
    suspend fun deleteAll()
}
