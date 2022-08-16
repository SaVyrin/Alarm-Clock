package com.hfad.alarmclock.ui.fragments.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.hfad.alarmclock.R
import com.hfad.alarmclock.databinding.FragmentAlarmsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmsFragment : Fragment() {

    private val viewModel: AlarmsViewModel by viewModels()

    private var _binding: FragmentAlarmsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewAdapter: AlarmRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMenuClickListener()
        setRecyclerViewAdapter()
        observeAlarms()
        setAlarmDeleteButtonClickListener()
    }

    private fun setMenuClickListener() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.change -> {
                    recyclerViewAdapter.currentList.forEach { alarm ->
                        alarm?.also {
                            it.changeStatus = true
                        }
                    }
                    recyclerViewAdapter.notifyDataSetChanged()
                    binding.alarmDeleteBtn.isVisible = true
                    true
                }
                else -> false
            }
        }
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
            recyclerViewAdapter = alarmAdapter
            observeAlarmsToChange(alarmAdapter)
        }
    }

    private fun observeAlarmsToChange(adapter: AlarmRecyclerViewAdapter) {
        viewModel.alarmsToChange.observe(viewLifecycleOwner) { alarms ->
            alarms?.also {
                adapter.submitList(alarms)
            }
        }
    }

    private fun observeAlarms() {
        viewModel.alarms.observe(viewLifecycleOwner) { alarms ->
            alarms?.also {
                viewModel.alarmsToChange.value = alarms
            }
        }
    }

    private fun setAlarmDeleteButtonClickListener() {
        binding.alarmDeleteBtn.setOnClickListener { button ->
            // TODO вынести в отдельный метод
            viewModel.deleteAlarms(recyclerViewAdapter.currentList)
            recyclerViewAdapter.currentList.forEach { alarm ->
                alarm?.also {
                    it.changeStatus = false
                    it.isSelected = false
                }
            }
            recyclerViewAdapter.notifyDataSetChanged()
            button.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}