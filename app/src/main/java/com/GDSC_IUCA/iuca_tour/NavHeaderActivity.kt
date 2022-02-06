package com.GDSC_IUCA.iuca_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NavHeaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.my_toolbar))

    }
}