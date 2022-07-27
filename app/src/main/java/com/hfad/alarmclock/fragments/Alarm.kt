package com.hfad.alarmclock.fragments

data class Alarm(
    val id: Long = 0L,
    val title: String = "",
    val time: String = "00:00",
    val date: String = "",
    val isActive: Boolean = false
)