package com.hfad.alarmclock.ui.fragments.editalarm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.alarmclock.R

class EditAlarmFragment : Fragment() {

    companion object {
        fun newInstance() = EditAlarmFragment()
    }

    private lateinit var viewModel: EditAlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_alarm, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditAlarmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}