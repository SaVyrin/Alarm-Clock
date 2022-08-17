package com.hfad.alarmclock.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface AlarmDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(alarm: Alarm)

    @Update
    suspend fun update(alarm: Alarm)

    @Delete
    suspend fun delete(alarm: Alarm)

    @Delete
    suspend fun delete(alarms: List<Alarm>)

    @Query("SELECT * FROM alarm_table WHERE id = :alarmId")
    fun get(alarmId: Long): LiveData<Alarm>

    @Query("SELECT * FROM alarm_table")
    fun getAll(): LiveData<List<Alarm>>

    @Query("DELETE FROM alarm_table")
    suspend fun deleteAll()
}
