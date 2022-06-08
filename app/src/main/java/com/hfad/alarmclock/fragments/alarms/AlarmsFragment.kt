package com.hfad.alarmclock.fragments.alarms

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hfad.alarmclock.R
import com.hfad.alarmclock.fragments.Alarm

/**
 * A fragment representing a list of Items.
 */
class AlarmsFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarms_list, container, false)

        // Set the adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val alarmAdapter = AlarmRecyclerViewAdapter {
                    val action = AlarmsFragmentDirections.actionAlarmsFragmentToEditAlarmFragment()
                    this.findNavController().navigate(action)
                }
                alarmAdapter.submitList(
                    listOf(
                        Alarm(0, "11"),
                        Alarm(1, "22"),
                        Alarm(2, "33"),
                        Alarm(3, "44"),
                    )
                )
                adapter = alarmAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AlarmsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}