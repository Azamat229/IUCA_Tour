package com.GDSC_IUCA.iuca_tour.ui


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.transition.*
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMainPageBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var layout: ConstraintLayout
    private val constraintSetOld: ConstraintSet = ConstraintSet()
    private val constraintSetNew: ConstraintSet = ConstraintSet()
    private var altLayout = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)

        binding.expandText.movementMethod = ScrollingMovementMethod()

        //    < - - - - SLIDER - - - - >
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


        //    < - - - ANIMATION  - - - >
        layout = binding.layout
        constraintSetOld.clone(layout)
        constraintSetNew.clone(activity, R.layout.fragment_main_page_alt)



        binding.expandCollabseBtn.setOnClickListener {

            val changeBounds: Transition = ChangeBounds()
            changeBounds.interpolator = OvershootInterpolator()

            TransitionManager.beginDelayedTransition(layout, changeBounds)

            altLayout = if (!altLayout) {
                constraintSetNew.applyTo(layout)
                true
            } else {
                constraintSetOld.applyTo(layout)
                false
            }
        }
    }
}