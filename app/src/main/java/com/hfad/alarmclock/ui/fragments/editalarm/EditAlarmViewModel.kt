package com.hfad.alarmclock.ui.fragments.editalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.data.database.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAlarmViewModel @Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            alarmDao.insert(alarm)
        }
    }
}