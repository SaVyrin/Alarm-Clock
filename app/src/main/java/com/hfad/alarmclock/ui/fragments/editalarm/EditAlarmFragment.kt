package com.hfad.alarmclock.ui.fragments.editalarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hfad.alarmclock.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_alarm, container, false)
    }
}