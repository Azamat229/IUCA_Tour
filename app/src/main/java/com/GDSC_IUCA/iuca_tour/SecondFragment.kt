package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.GDSC_IUCA.iuca_tour.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    val args: SecondFragmentArgs by navArgs()

    private lateinit var binding: FragmentSecondBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view)

        val lng = args.lng
        binding.button.setOnClickListener {
            val action = SecondFragmentDirections.actionSecondFragmentToTimeFragment(lng)
            Navigation.findNavController(view).navigate(action)
        }
    }
}