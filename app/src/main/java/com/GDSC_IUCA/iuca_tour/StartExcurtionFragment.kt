package com.GDSC_IUCA.iuca_tour

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentStartExcurtionBinding
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.Dispatchers.Main

class StartExcurtionFragment : Fragment(R.layout.fragment_start_excurtion) {


    private lateinit var binding: FragmentStartExcurtionBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartExcurtionBinding.bind(view)

        // Navigation ->
        binding.button3.setOnClickListener {


            Navigation.findNavController(view)
                .navigate(R.id.action_startExcurtionFragment_to_hallFragment)
        }


        // Slider ...
        val imageList = ArrayList<SlideModel>() // Create image list

        // imageList.add(SlideModel("String Url" or R.drawable)
        // imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))

//        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        val imageS = binding.imageSlider
        imageS.setImageList(imageList)

    }
}