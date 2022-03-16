package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.ViewModel.MapViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MapViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMap2Binding
import com.GDSC_IUCA.iuca_tour.repository.Repository


class Map2Fragment : Fragment() {
    private lateinit var binding: FragmentMap2Binding

    //Retrofit
    lateinit var viewModel: MapViewModel

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMap2Binding.bind(view)

        binding.loseTest.setOnClickListener {
            val intent = Intent(activity, QrActivity::class.java)
            activity?.startActivity(intent)
        }


        // Retrofit
        val repository = Repository()
        val viewModelFactory = MapViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)
        viewModel.getPreset()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer{ response ->
            if(response.isSuccessful){
                Log.d("body", response.body().toString())

                val presetMap: MutableMap<Int, String> = mutableMapOf()

                for(i in 0..2){

                    presetMap.put(response.body()!!.places[i].order, response.body()!!.places[i].place.toString())
                    Log.d("test", presetMap.values.toString() )
                    Log.d("test", presetMap.keys.toString() )
                }
                val orderIdOfPlaces = ArrayList<String>()
                for (i in 1..3){
                    orderIdOfPlaces.add(presetMap[i].toString())
                }
                for(i in orderIdOfPlaces)
                    Log.d("ResultFinalListOfPlaces", i.toString())
                saveData(orderIdOfPlaces)




                Log.d("loop", print(presetMap).toString())


                loadData()

            }else{
                Log.d("body", response.body().toString())
            }
        })


        binding.nextStationBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_testFragment_to_mainPageFragment)
        }
    }

    private fun loadData() {
        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val savedInt = sharedPre?.getInt("pref",0)

    }

    private fun saveData(orderIdOfPlaces: ArrayList<String>) {
        val setA = mutableSetOf<String>()
        setA.addAll(orderIdOfPlaces)

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()
        editor?.apply {
            putStringSet("SetOrderedPlaces", setA)
        }?.apply()
    }
}