package com.hfad.alarmclock.ui.fragments.editalarm

import androidx.lifecycle.ViewModel
import com.hfad.alarmclock.data.database.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditAlarmViewModel @Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {
    // TODO: Implement the ViewModel
}