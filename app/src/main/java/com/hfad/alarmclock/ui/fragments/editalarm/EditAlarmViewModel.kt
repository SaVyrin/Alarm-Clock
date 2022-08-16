package com.hfad.alarmclock.ui.fragments.editalarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.data.database.AlarmDao
import kotlinx.coroutines.launch

class EditAlarmViewModel(
    alarmId: Long,
    private val alarmDao: AlarmDao
) : ViewModel() {

    val alarm: LiveData<Alarm> = when (alarmId >= 0) {
        true -> alarmDao.get(alarmId)
        else -> MutableLiveData(Alarm())
    }

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            alarmDao.insert(alarm)
        }
    }
}