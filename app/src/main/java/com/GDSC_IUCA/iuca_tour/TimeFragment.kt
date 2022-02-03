package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentTimeBinding


class TimeFragment : Fragment(R.layout.fragment_time) {
    private lateinit var binding: FragmentTimeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimeBinding.bind(view)
        binding.butTime1.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_timeFragment_to_startExcurtionFragment)
        }
    }
}