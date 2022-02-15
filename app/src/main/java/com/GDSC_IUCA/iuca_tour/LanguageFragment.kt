package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentLanguageBinding
import androidx.appcompat.app.AppCompatActivity


class LanguageFragment : Fragment(R.layout.fragment_language) {
    private lateinit var binding: FragmentLanguageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLanguageBinding.bind(view)

//        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


        binding.thirdButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_languageFragment_to_secondFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}