package com.GDSC_IUCA.iuca_tour

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentOpeningBinding
import com.GDSC_IUCA.iuca_tour.recycler.RecyclerActivity
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.GDSC_IUCA.iuca_tour.ui.MainPageActivity

class OpeningFragment : Fragment(R.layout.fragment_opening) {
    private lateinit var binding: FragmentOpeningBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOpeningBinding.bind(view)

        binding.textView4.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_openingFragment_to_languageFragment)
        }
        binding.tvRecycler.setOnClickListener {
            val intent = Intent (activity, RecyclerActivity::class.java)
            activity?.startActivity(intent)
        }



        binding.textView11.setOnClickListener{
            val intent = Intent (activity, MainPageActivity::class.java)
            activity?.startActivity(intent)

        }



  }
}