package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    private lateinit var binding: FragmentSecondBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view)



        binding.button.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_timeFragment)
        }
    }
}