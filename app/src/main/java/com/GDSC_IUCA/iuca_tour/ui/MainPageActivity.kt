package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMainPageBinding
import com.budiyev.android.codescanner.CodeScanner

import com.google.android.material.navigation.NavigationView

import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.GDSC_IUCA.iuca_tour.MainActivity
import com.GDSC_IUCA.iuca_tour.StartExcurtionFragment
import com.GDSC_IUCA.iuca_tour.ViewModel.ActivityViewModel
import com.GDSC_IUCA.iuca_tour.adapter.ListBaseAdapter
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import kotlin.system.exitProcess

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding
    private lateinit var listOfPlaceNames: ArrayList<String>

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var codeScanner: CodeScanner
    private lateinit var adapter: ListBaseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)

        binding.btnExit.setOnClickListener {
            finishAffinity()

        }
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.mainPageFragment, R.id.mainPageAltFragment),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)



        listOfPlaceNames = ArrayList()


//        // Shared preference
        val sharedPre = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val counter = sharedPre?.getInt("counter", 0)
        val setOrderedPlaces = sharedPre?.getString("setOrderedPlaces", null)
        val listOrderedPlaces: List<Char> = setOrderedPlaces!!.toList()
        val idOfCurrentPlace = listOrderedPlaces.elementAt(counter!!.toInt())

        Log.d("ID OF CUR PLACE", idOfCurrentPlace.toString())
        Log.d("LIST OF PLACE", listOrderedPlaces.toString())

        val viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        viewModel.getPost1()
    }



    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_main2)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}