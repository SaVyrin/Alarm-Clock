package com.hfad.alarmclock.ui.fragments.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.hfad.alarmclock.databinding.FragmentAlarmsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmsFragment : Fragment() {

    private val viewModel: AlarmsViewModel by viewModels()

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
            observeAlarms(alarmAdapter)
        }
    }

    private fun observeAlarms(adapter: AlarmRecyclerViewAdapter) {
        viewModel.alarms.observe(viewLifecycleOwner) { alarms ->
            alarms?.also {
                adapter.submitList(alarms)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}