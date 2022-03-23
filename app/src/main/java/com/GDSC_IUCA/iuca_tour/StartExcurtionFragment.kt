package com.GDSC_IUCA.iuca_tour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentStartExcurtionBinding
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.GDSC_IUCA.iuca_tour.ui.MainPageActivity
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Response
import kotlin.properties.Delegates

class StartExcurtionFragment : Fragment(R.layout.fragment_start_excurtion) {
    private val args: StartExcurtionFragmentArgs by navArgs()

    //Retrofit
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel1: MainViewModel
    private val presetMap: MutableMap<Int, Int> = mutableMapOf()
    private val orderIdOfPlaces = ArrayList<String>()
    private var placesSize by Delegates.notNull<Int>()
    private lateinit var binding: FragmentStartExcurtionBinding

    private lateinit var list: ArrayList<String>
    private lateinit var listOfPlaceNames: ArrayList<String>
    private  var listOfPlaceImages = ArrayList<PlacesItem>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartExcurtionBinding.bind(view)


        // Retrofit code
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPreset(args.tourDuration)


        viewModel.myResponsePreset.observe(viewLifecycleOwner, Observer { response ->

            if (response.isSuccessful) {
                list = convertMupToList(response)
                Log.d("PLACE LIST", list.toString())


                listOfPlaceNames = ArrayList()
                listOfPlaceImages = ArrayList()
                val repository1 = Repository()
                val viewModelFactory1 = MainViewModelFactory(repository1)
                viewModel1 = ViewModelProvider(this, viewModelFactory1).get(MainViewModel::class.java)

                // Shared preference
                val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)

                val str = sharedPre?.getString("setOrderedPlaces", null)
                if (str != null) {
                    Log.d("Set oder places", str)
                } else {
                    Log.d("Set order", 1.toString())}
                val chars: List<Char> = str!!.toList()


                viewModel1.getPlace()
                viewModel1.myResponse.observe(viewLifecycleOwner, Observer { res ->
                    if (res.isSuccessful) {
                        chars.forEach {
                            listOfPlaceNames.add(res.body()!![it.digitToInt()-1].name)
                            listOfPlaceImages.add(res.body()!![it.digitToInt()-1])
                        }
                    }else Log.d("RESPONSE ERROR", res.errorBody().toString())

                    Log.d("COMPLETED NAME's LIST", listOfPlaceNames.toString())
                    Log.d("COMPLETED IMAGE's LIST", listOfPlaceImages.toString())

                    // Simple adapter
                    val data = (0 until listOfPlaceNames.size).map {

                        Log.d("LIST SIZE", listOfPlaceNames[it])

                        mapOf(
                            KEY_TITLE to "${it+1}. ${listOfPlaceNames.get(it)}"
                        )
                    }
                    val adapter = SimpleAdapter(
                        context,
                        data,
                        R.layout.item_custem,
                        arrayOf(KEY_TITLE),
                        intArrayOf(R.id.tv_title)
                    )
                    binding.listView.adapter = adapter
                    // Slider code
                    var imageList = ArrayList<SlideModel>() // Create image list

                    listOfPlaceImages.forEach {
                        //    < - - - - SLIDER IMAGES- - - - >

                        val t = it.images.get(0)
                        Log.d("IMAGE",t.toString())

                        val t2 = "http://159.89.97.31$t"
                        Log.d("IMAGES",t2)

                        imageList.add(SlideModel(t2))

                    }

                    val imageS = binding.imageSlider
                    imageS.setImageList(imageList)
                })


            } else Log.d("RESPONSE ERROR", response.errorBody().toString())
        })





        // Navigation code
        binding.button3.setOnClickListener {
            val intent = Intent(activity, MainPageActivity::class.java)
            activity?.startActivity(intent)
        }
    }


    private fun convertMupToList(response: Response<PresetItem>): ArrayList<String> {

        placesSize = response.body()!!.places.size
        // put places to Map
        for (i in 0 until placesSize) {
            presetMap.put(response.body()!!.places[i].place, response.body()!!.places[i].order)
        }

        for (i in 1 until response.body()!!.places.size + 1) {
            orderIdOfPlaces.add(presetMap.getValue(i).toString())
        }

        saveListSharePref(orderIdOfPlaces)
        return orderIdOfPlaces
    }


    private fun saveListSharePref(orderIdOfPlaces: ArrayList<String>) {
        val orderIdOfPlacesStr = orderIdOfPlaces.joinToString("")

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()

        editor?.apply {
            putString("setOrderedPlaces", orderIdOfPlacesStr)
            putInt("counter", 0)
        }?.apply()
    }

    companion object {
        @JvmStatic
        val KEY_TITLE = "title"
    }
}