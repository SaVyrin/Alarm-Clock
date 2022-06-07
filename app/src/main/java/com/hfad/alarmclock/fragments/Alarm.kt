package com.hfad.alarmclock.fragments

data class Alarm(
    val id: Long = 0L,
    val time: String = "00:00",
    val description: String = ""
)