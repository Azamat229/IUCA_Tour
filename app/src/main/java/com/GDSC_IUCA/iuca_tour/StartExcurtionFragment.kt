package com.GDSC_IUCA.iuca_tour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.GDSC_IUCA.iuca_tour.ViewModel.MapViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MapViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentStartExcurtionBinding
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.GDSC_IUCA.iuca_tour.ui.MainPageActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Response
import kotlin.properties.Delegates

class StartExcurtionFragment : Fragment(R.layout.fragment_start_excurtion) {

    //Retrofit
    lateinit var viewModel: MapViewModel
    private var counter = 0
    private val presetMap: MutableMap<Int, Int> = mutableMapOf()
//    private val orderIdOfPlaces = ArrayList<String>()
    private val orderIdOfPlaces = ArrayList<String>()

    private var placesSize by Delegates.notNull<Int>()

    private lateinit var binding: FragmentStartExcurtionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartExcurtionBinding.bind(view)

        // Retrofit code
        val repository = Repository()
        val viewModelFactory = MapViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)
        viewModel.getPreset()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer{ response ->
            if(response.isSuccessful){
                Log.d("size", response.body()!!.places.size.toString())
                convertMupToList(response)
            }else{
                Log.d("body", response.body().toString())
            }
        })

        // Navigation code
        binding.button3.setOnClickListener {
            val intent = Intent (activity, MainPageActivity::class.java)
            activity?.startActivity(intent)
        }

        // Slider code
        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))

        val imageS = binding.imageSlider
        imageS.setImageList(imageList)
    }

    private fun convertMupToList(response: Response<PresetItem>) {

        placesSize = response.body()!!.places.size
        // put places to Map
        for (i in 0 until placesSize){
            presetMap.put(response.body()!!.places[i].place, response.body()!!.places[i].order)
        }

        for (i in 1 until response.body()!!.places.size+1){
            orderIdOfPlaces.add(presetMap.getValue(i).toString())
        }
        saveListSharePref(orderIdOfPlaces)
    }

    private fun saveListSharePref(orderIdOfPlaces: ArrayList<String>) {
        var orderIdOfPlaces = orderIdOfPlaces.joinToString("")
        Log.d("JoitToString", orderIdOfPlaces)

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()


        editor?.apply {

            val setA = setOf("3","1","4")

            putString("setOrderedPlaces", orderIdOfPlaces)
//            putStringSet("setOrderedPlaces", setA)
            putInt("counter", 0)
        }?.apply()
    }
}