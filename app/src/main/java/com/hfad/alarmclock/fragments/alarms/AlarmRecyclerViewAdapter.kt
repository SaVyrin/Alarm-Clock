package com.hfad.alarmclock.fragments.alarms

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hfad.alarmclock.databinding.FragmentAlarmsListItemBinding
import com.hfad.alarmclock.fragments.Alarm

class AlarmRecyclerViewAdapter(
    private val clickListener: (taskId: Long) -> Unit
) :
    ListAdapter<Alarm, AlarmRecyclerViewAdapter.AlarmViewHolder>(AlarmsDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class AlarmViewHolder(
        private val binding: FragmentAlarmsListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): AlarmViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentAlarmsListItemBinding.inflate(layoutInflater, parent, false)
                return AlarmViewHolder(binding)
            }
        }

        fun bind(
            alarm: Alarm,
            clickListener: (taskId: Long) -> Unit
        ) {
            with(binding) {
                alarmTitleTv.text = alarm.title
                alarmTimeTv.text = alarm.time
                alarmDateTv.text = alarm.date
                alarmSwitch.isChecked = alarm.isActive
                root.setOnClickListener { clickListener(alarm.id) }
            }
        }
    }
}