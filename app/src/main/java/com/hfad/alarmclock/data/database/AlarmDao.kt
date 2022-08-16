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

    @Delete
    suspend fun delete(alarms: List<Alarm>)

    @Query("SELECT * FROM alarm_table")
    fun getAll(): LiveData<List<Alarm>>

    @Query("DELETE FROM alarm_table")
    suspend fun deleteAll()
}
