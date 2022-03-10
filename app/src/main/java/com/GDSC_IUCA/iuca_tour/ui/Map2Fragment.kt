package com.GDSC_IUCA.iuca_tour.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

                val presetMap: MutableMap<Int, Int> = mutableMapOf()

                for(i in 0..2){

                    presetMap.put(response.body()!!.places.get(i).order, response.body()!!.places[i].place)
                    Log.d("test", presetMap.values.toString())
                }
                print(presetMap)
                Log.d("loop", print(presetMap).toString())
            }else{
                Log.d("body", response.body().toString())
            }
        })
    }
}