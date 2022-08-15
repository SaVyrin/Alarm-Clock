package com.hfad.alarmclock.ui.fragments.alarms

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import com.hfad.alarmclock.databinding.FragmentAlarmsListItemBinding
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.utils.AlarmsDiffItemCallback

class AlarmRecyclerViewAdapter(
    private val switchClickListener: (alarmId: Long) -> Unit,
    private val navigateClickListener: (alarmId: Long) -> Unit
) :
    ListAdapter<Alarm, AlarmRecyclerViewAdapter.AlarmViewHolder>(AlarmsDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, switchClickListener, navigateClickListener)
    }

    fun setChangeStatus() {

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
            switchClickListener: (alarmId: Long) -> Unit,
            navigateClickListener: (alarmId: Long) -> Unit
        ) {
            setAlarmTitle(alarm)
            setAlarmDate(alarm)
            setAlarmStatus(alarm)
            setChangeStatus(alarm)
            setAlarmStatusSwitchClickListener(alarm, switchClickListener)
            setAlarmNavigateClickListener(alarm, navigateClickListener)
        }

        private fun setAlarmTitle(alarm: Alarm) {
            binding.alarmTimeTv.text = alarm.time
        }

        private fun setAlarmDate(alarm: Alarm) {
            binding.alarmDateTv.text = alarm.date
        }

        private fun setAlarmStatus(alarm: Alarm) {
            binding.alarmSwitch.isChecked = alarm.isActive
        }

        private fun setChangeStatus(alarm: Alarm) {
            binding.selectCheckbox.isVisible = alarm.changeStatus
        }

        private fun setAlarmStatusSwitchClickListener(
            alarm: Alarm,
            switchClickListener: (alarmId: Long) -> Unit
        ) {
            binding.alarmSwitch.setOnClickListener { switchClickListener(alarm.id) }
        }

        private fun setAlarmNavigateClickListener(
            alarm: Alarm,
            navigateClickListener: (alarmId: Long) -> Unit
        ) {
            binding.root.setOnClickListener { navigateClickListener(alarm.id) }
        }
    }
}