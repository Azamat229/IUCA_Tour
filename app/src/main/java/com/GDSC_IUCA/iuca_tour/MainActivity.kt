package com.GDSC_IUCA.iuca_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMainBinding
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNav()
//        setSupportActionBar(findViewById(R.id.my_toolbar))
        setSupportActionBar(binding.myToolbar)



        // bottom navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView);
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        val config = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.my_toolbar).setupWithNavController(navController, config)


    }

    // hide the bottom nav bar
    private fun setupNav() {
        val navController = findNavController(R.id.fragmentContainerView)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.firstFragment -> showBottomNav()
                R.id.secondFragment -> showBottomNav()
                R.id.thirdFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE

    }
}