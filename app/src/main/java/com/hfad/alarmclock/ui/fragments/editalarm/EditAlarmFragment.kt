package com.hfad.alarmclock.ui.fragments.editalarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.data.database.AlarmDao
import com.hfad.alarmclock.databinding.FragmentEditAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditAlarmFragment : Fragment() {

    @Inject
    lateinit var alarmDao: AlarmDao
    private lateinit var viewModelFactory: EditAlarmViewModelFactory
    private val viewModel: EditAlarmViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentEditAlarmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAlarmBinding.inflate(inflater, container, false)
        setViewModelFactory()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAlarm()
        setSaveButtonClickListener()
        setNotSaveButtonClickListener()
    }

    private fun setViewModelFactory() {
        val alarmId = EditAlarmFragmentArgs.fromBundle(requireArguments()).alarmId
        viewModelFactory = EditAlarmViewModelFactory(alarmId, alarmDao)
    }

    private fun observeAlarm() {
        viewModel.alarm.observe(viewLifecycleOwner) { alarm ->
            alarm?.also {
                val alarmHoursAndMinutes = alarm.time.split(":")
                val alarmHours = alarmHoursAndMinutes[0]
                val alarmMinutes = alarmHoursAndMinutes[1]
                binding.alarmHoursEdt.setText(alarmHours)
                binding.alarmMinutesEdt.setText(alarmMinutes)
                binding.alarmDescriptionEdt.setText(alarm.title)
            }
        }
    }

    private fun setSaveButtonClickListener() {
        binding.saveBtn.setOnClickListener {
            viewModel.saveAlarm(Alarm())
            findNavController().popBackStack()
        }
    }

    private fun setNotSaveButtonClickListener() {
        binding.notSaveBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}