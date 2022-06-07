package com.hfad.alarmclock.fragments

import androidx.recyclerview.widget.DiffUtil

class AlarmsDiffItemCallback: DiffUtil.ItemCallback<Alarm>() {
    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        val timeIsSame = (oldItem.time == newItem.time)
        val descriptionIsSame = (oldItem.description == newItem.description)
        return (timeIsSame and descriptionIsSame)
    }
}