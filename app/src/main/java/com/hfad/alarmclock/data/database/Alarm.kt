package com.hfad.alarmclock.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "alarm_table")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "time")
    var time: String = "00:00",

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "isActive")
    var isActive: Boolean = false,

    @Ignore
    var changeStatus: Boolean = false
)