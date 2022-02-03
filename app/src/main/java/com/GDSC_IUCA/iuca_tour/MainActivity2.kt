package com.GDSC_IUCA.iuca_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(R.layout.activity_main2) {
    private lateinit var  binding: ActivityMain2Binding
    private lateinit var mToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        var mToolBar = binding.mainToolbar
        setSupportActionBar(mToolBar)
    }


}