package com.GDSC_IUCA.iuca_tour.ui


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.transition.*
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMainPageBinding
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var layout: ConstraintLayout
    private val constraintSetOld: ConstraintSet = ConstraintSet()
    private val constraintSetNew: ConstraintSet = ConstraintSet()
    private var altLayout = false
// Retrofit stuff
    lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)


        // Retrofit stuff
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPlace()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                Log.d("response", response.body()?.get(0)?.id.toString())
                Log.d("response", response.body()?.get(0)?.name.toString())
                response.body()?.get(0)?.let { Log.d("response", it.name) }
                response.body()?.get(0)?.let { Log.d("response", it.desc) }
                binding.titlePlace.text = response.body()?.get(0)?.name.toString()
//                binding.descriptionText.text = response.body()?.get(0)?.desc.toString()
                binding.descriptionText.text = response.body()?.get(0)?.images.toString()
            }else{
                Log.d("response", response.errorBody().toString())
            }

        })




        binding.descriptionText.movementMethod = ScrollingMovementMethod()

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


        binding.nextStationBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_testFragment)
        }


//        binding.nextStationBtn.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_qrCodeFragment)
//        }
    }
}