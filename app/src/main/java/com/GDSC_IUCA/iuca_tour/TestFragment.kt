package com.GDSC_IUCA.iuca_tour

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentTestBinding
import com.GDSC_IUCA.iuca_tour.ui.QrActivity

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTestBinding.bind(view)

        binding.loseTest.setOnClickListener {
            val intent = Intent(activity, QrActivity::class.java)
            activity?.startActivity(intent)
        }
    }


}