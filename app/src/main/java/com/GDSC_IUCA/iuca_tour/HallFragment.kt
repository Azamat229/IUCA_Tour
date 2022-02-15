package com.GDSC_IUCA.iuca_tour

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.GDSC_IUCA.iuca_tour.databinding.FragmentHallBinding
import com.GDSC_IUCA.iuca_tour.ui.MainPageActivity


class HallFragment : Fragment(R.layout.fragment_hall) {
    private lateinit var binding: FragmentHallBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHallBinding.bind(view)

        binding.button3.setOnClickListener {
            val intent = Intent (activity, MainPageActivity::class.java)
            activity?.startActivity(intent)
        }

    }

}