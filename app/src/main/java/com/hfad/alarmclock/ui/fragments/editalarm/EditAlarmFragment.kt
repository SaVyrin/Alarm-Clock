package com.hfad.alarmclock.ui.fragments.editalarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hfad.alarmclock.data.database.Alarm
import com.hfad.alarmclock.databinding.FragmentEditAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAlarmFragment : Fragment() {

    private val viewModel: EditAlarmViewModel by viewModels()

    private var _binding: FragmentEditAlarmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSaveButtonClickListener()
        setNotSaveButtonClickListener()
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