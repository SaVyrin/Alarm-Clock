package com.hfad.alarmclock.ui.fragments.alarms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.data.database.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel
@Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {

    val alarms = alarmDao.getAll()
    var alarmsToChange = MutableLiveData<List<Alarm>>()

    fun toggleChangeStatusForAlarms() {
        val newAlarms = alarmsToChange.value?.map { alarm ->
            Alarm(
                alarm.id,
                alarm.title,
                alarm.time,
                alarm.date,
                alarm.isActive,
                !alarm.changeStatus
            )
        }
        alarmsToChange.value = newAlarms
    }

    fun deleteAlarms(alarms: List<Alarm>) {
        val alarmsToDelete = alarms.filter { it.isSelected }
        viewModelScope.launch {
            alarmDao.delete(alarmsToDelete)
        }
    }
}