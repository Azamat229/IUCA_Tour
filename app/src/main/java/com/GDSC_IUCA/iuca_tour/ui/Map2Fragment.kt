package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMap2Binding

class Map2Fragment : Fragment() {
    private lateinit var binding: FragmentMap2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMap2Binding.bind(view)

//        binding.loseTest.setOnClickListener {
//            val intent = Intent(activity, QrActivity::class.java)
//            activity?.startActivity(intent)
//        }

        saveCountData()
        binding.nextStationBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_testFragment_to_mainPageFragment)
        }
    }

    private fun saveCountData() {

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()
        var counter = sharedPre?.getInt("counter", 0)

        Log.d("Counter", counter.toString())

        counter = counter!!.toInt() + 1

        Log.d("Counter2", counter.toString())

        editor?.apply {
            putInt("counter", counter)
        }?.apply()
    }
}