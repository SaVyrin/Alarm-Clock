package com.hfad.alarmclock.ui.fragments.alarms

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hfad.alarmclock.databinding.FragmentAlarmsListItemBinding
import com.hfad.alarmclock.ui.fragments.Alarm

class AlarmRecyclerViewAdapter(
    private val switchClickListener: (taskId: Long) -> Unit,
    private val navigateClickListener: (taskId: Long) -> Unit
) :
    ListAdapter<Alarm, AlarmRecyclerViewAdapter.AlarmViewHolder>(AlarmsDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, switchClickListener, navigateClickListener)
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
            switchClickListener: (taskId: Long) -> Unit,
            navigateClickListener: (taskId: Long) -> Unit
        ) {
            with(binding) {
                alarmTitleTv.text = alarm.title
                alarmTimeTv.text = alarm.time
                alarmDateTv.text = alarm.date
                alarmSwitch.isChecked = alarm.isActive
                alarmSwitch.setOnClickListener { switchClickListener(alarm.id) }
                root.setOnClickListener { navigateClickListener(alarm.id) }
            }
        }
    }
}