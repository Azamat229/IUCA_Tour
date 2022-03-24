package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentOpeningBinding

class OpeningFragment : Fragment(R.layout.fragment_opening) {
    private lateinit var binding: FragmentOpeningBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOpeningBinding.bind(view)

        binding.textView4.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_openingFragment_to_languageFragment)
        }

        binding.textView11.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_openingFragment_to_startExcurtionFragment)
        }
    }
}