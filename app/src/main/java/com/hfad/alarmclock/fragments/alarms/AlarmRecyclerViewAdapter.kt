package com.hfad.alarmclock.fragments.alarms

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hfad.alarmclock.databinding.FragmentAlarmsBinding
import com.hfad.alarmclock.fragments.Alarm

class AlarmRecyclerViewAdapter(val clickListener: (taskId: Long) -> Unit) :
    ListAdapter<Alarm, AlarmRecyclerViewAdapter.AlarmViewHolder>(AlarmsDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class AlarmViewHolder(val binding: FragmentAlarmsBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): AlarmViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentAlarmsBinding.inflate(layoutInflater, parent, false)
                return AlarmViewHolder(binding)
            }
        }

        fun bind(item: Alarm, clickListener: (taskId: Long) -> Unit) {
            binding.alarm = item
            binding.root.setOnClickListener { clickListener(item.id) }
        }
    }
}