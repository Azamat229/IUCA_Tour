package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.StartExcurtionFragment
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentNavHeaderBinding
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.denzcoskun.imageslider.models.SlideModel

class NavHeaderFragment : Fragment(R.layout.fragment_nav_header) {
    private lateinit var binding: FragmentNavHeaderBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var listOfPlaceNames: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNavHeaderBinding.bind(view)

        listOfPlaceNames = ArrayList()
        val repository1 = Repository()
        val viewModelFactory1 = MainViewModelFactory(repository1)
        viewModel = ViewModelProvider(this, viewModelFactory1).get(MainViewModel::class.java)

        // Shared preference
        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)

        val str = sharedPre?.getString("setOrderedPlaces", null)
        if (str != null) {
            Log.d("Set oder places234", str)
        } else {
            Log.d("Set order", 1.toString())}
        val chars: List<Char> = str!!.toList()


        viewModel.getPlace()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { res ->
            if (res.isSuccessful) {
                chars.forEach {
                    listOfPlaceNames.add(res.body()!![it.digitToInt()-1].name)
                }
            }else Log.d("RESPONSE ERROR", res.errorBody().toString())

            Log.d("COMPLETED NAME's LIST", listOfPlaceNames.toString())

            // Simple adapter
            val data = (0 until listOfPlaceNames.size).map {

                Log.d("LIST SIZE", listOfPlaceNames[it])

                mapOf(
                    StartExcurtionFragment.KEY_TITLE to "${it+1}. ${listOfPlaceNames.get(it)}"
                )
            }
            val adapter = SimpleAdapter(
                context,
                data,
                R.layout.item_custem,
                arrayOf(StartExcurtionFragment.KEY_TITLE),
                intArrayOf(R.id.tv_title)
            )

//            binding.listView.adapter = adapter
            // Slider code
            var imageList = ArrayList<SlideModel>() // Create image list



        })
    }




}