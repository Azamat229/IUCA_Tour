package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.GDSC_IUCA.iuca_tour.databinding.FragmentHallBinding

class HallDescription : Fragment(R.layout.fragment_hall_description) {


    private lateinit var binding: FragmentHallBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHallBinding.bind(view)

    }


}