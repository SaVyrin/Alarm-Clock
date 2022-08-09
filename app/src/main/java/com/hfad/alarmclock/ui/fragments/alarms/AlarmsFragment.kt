package com.hfad.alarmclock.ui.fragments.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.hfad.alarmclock.databinding.FragmentAlarmsBinding
import com.hfad.alarmclock.data.database.Alarm
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmsFragment : Fragment() {

    private var _binding: FragmentAlarmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()
    }

    private fun setRecyclerViewAdapter() {
        with(binding.list) {
            val alarmAdapter = AlarmRecyclerViewAdapter(
                switchClickListener = { alarmId ->
                    // TODO change alarm status
                },
                navigateClickListener = {
                    val action = AlarmsFragmentDirections.actionAlarmsFragmentToEditAlarmFragment()
                    this.findNavController().navigate(action)
                }
            )
            adapter = alarmAdapter

            alarmAdapter.submitList(
                listOf(
                    Alarm(1, "11", "06:35", "пт, 10 июня", true),
                    Alarm(2, "22", "07:35", "пт, 10 июня", false),
                    Alarm(3, "33", "07:36", "пт, 10 июня", false),
                    Alarm(4, "44", "08:35", "пт, 10 июня", false),
                    Alarm(5, "55", "09:35", "пт, 10 июня", false),
                    Alarm(6, "66", "10:40", "пт, 10 июня", true),
                    Alarm(7, "77", "10:35", "пт, 10 июня", true),
                    Alarm(8, "88", "11:35", "пт, 10 июня", true),
                    Alarm(9, "99", "12:35", "пт, 10 июня", true),
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}