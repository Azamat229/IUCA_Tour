package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.GDSC_IUCA.iuca_tour.databinding.FragmentFirstBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

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