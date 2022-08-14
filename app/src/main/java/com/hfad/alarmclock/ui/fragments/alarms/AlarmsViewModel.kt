package com.hfad.alarmclock.ui.fragments.alarms

import androidx.lifecycle.ViewModel
import com.hfad.alarmclock.data.database.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel
@Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {

    val alarms = alarmDao.getAll()
}