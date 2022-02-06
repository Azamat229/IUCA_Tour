package com.GDSC_IUCA.iuca_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.GDSC_IUCA.iuca_tour.databinding.ActivityHallDescriptionAltBinding

class HallDescriptionActivityAlt : AppCompatActivity(R.layout.activity_hall_description_alt) {
    private lateinit var binding: ActivityHallDescriptionAltBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityHallDescriptionAltBinding.inflate(layoutInflater)


    }


}