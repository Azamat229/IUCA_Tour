package com.GDSC_IUCA.iuca_tour

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentOpeningBinding
import com.ms.square.android.expandabletextview.ExpandableTextView

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

        }


        binding.textView11.setOnClickListener{
            val intent = Intent (activity, HallDescriptionActivity::class.java)
            activity?.startActivity(intent)

        }


        // Get the id of the expandable text view and set the desired text of your choice.
        val expTv: ExpandableTextView = binding.expandTextView
        expTv.setText("Expandable Text View is an android library that allows the users to create the text view which can expand and collapse to read the text description. I bet you guys have seen this in a lot of android applications but might not know the name and its purpose. Well, below is a screenshot of the Instagram application on the Play store. This feature saves a lot of space, rather than laying out the huge chunks of information and occupying the entire page we can further use this option and can utilize the space")
    }
}