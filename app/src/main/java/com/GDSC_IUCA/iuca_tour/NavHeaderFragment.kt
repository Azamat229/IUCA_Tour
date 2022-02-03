package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.GDSC_IUCA.iuca_tour.databinding.FragmentNavHeaderBinding
import androidx.appcompat.app.AppCompatActivity





class NavHeaderFragment : Fragment(R.layout.nav_header) {
    private lateinit var binding: FragmentNavHeaderBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNavHeaderBinding.bind(view)




    }
}