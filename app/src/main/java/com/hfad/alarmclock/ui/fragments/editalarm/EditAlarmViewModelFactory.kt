package com.hfad.alarmclock.ui.fragments.editalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.alarmclock.data.database.AlarmDao

class EditAlarmViewModelFactory(
    private val alarmId: Long,
    private val dao: AlarmDao
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditAlarmViewModel::class.java)) {
            return EditAlarmViewModel(alarmId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}