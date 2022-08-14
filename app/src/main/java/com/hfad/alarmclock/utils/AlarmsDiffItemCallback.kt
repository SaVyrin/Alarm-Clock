package com.hfad.alarmclock.utils

import androidx.recyclerview.widget.DiffUtil
import com.hfad.alarmclock.data.database.Alarm

class AlarmsDiffItemCallback: DiffUtil.ItemCallback<Alarm>() {
    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem == newItem
    }
}