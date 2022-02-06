package com.GDSC_IUCA.iuca_tour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.ActivityHallDescriptionBinding
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMain2Binding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationView

// - - - - - - Music player - - - - -
import android.media.MediaPlayer
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.SeekBar

// - - -- animation ex and call

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet




class HallDescriptionActivity : AppCompatActivity(R.layout.activity_hall_description) {

    private lateinit var binding: ActivityHallDescriptionBinding

    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView


    // - - - - - - Music player - - - - -
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false

    // - - - - -  Animation expend and callabse
    private lateinit var layout: ConstraintLayout
    private val constraintSetOld: ConstraintSet = ConstraintSet()
    private val constraintSetNew: ConstraintSet = ConstraintSet()
    private var altLayout = false

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHallDescriptionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        // - - - - -  Animation expend and callabse
        layout = findViewById(R.id.layout)
        constraintSetOld.clone(layout)
        constraintSetNew.clone(this, R.layout.activity_hall_description_alt)


        // - - - - Slider - - - - -
        val imageList = ArrayList<SlideModel>() // Create image list

        // imageList.add(SlideModel("String Url" or R.drawable)
        // imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.slider, ScaleTypes.FIT))
        val imageS = binding.imageSlider
        imageS.setImageList(imageList)


        // - - - - Navigation Drawer - - - - - -
        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()

        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
//        navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.myProfile -> {
//                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.people -> {
//                    Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.settings -> {
//                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }


        // - - - - Music player - - -
        // Start the media player
//        binding.playBtn.setOnClickListener {
//            if (pause) {
//                mediaPlayer.seekTo(mediaPlayer.currentPosition)
//                mediaPlayer.start()
//                pause = false
//                Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
//            } else {
//
//                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.oblivion_the_winery_dogs)
//                mediaPlayer.start()
//                Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
//
//            }
//            initializeSeekBar()
//            binding.playBtn.isEnabled = false
//            binding.pauseBtn.isEnabled = true
//            binding.stopBtn.isEnabled = true
//
//            mediaPlayer.setOnCompletionListener {
//                binding.playBtn.isEnabled = true
//                binding.pauseBtn.isEnabled = false
//                binding.stopBtn.isEnabled = false
//                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
//            }
//        }
        // Pause the media player
//        binding.pauseBtn.setOnClickListener {
//            if (mediaPlayer.isPlaying) {
//                mediaPlayer.pause()
//                pause = true
//                binding.playBtn.isEnabled = true
//                binding.pauseBtn.isEnabled = false
//                binding.stopBtn.isEnabled = true
//                Toast.makeText(this, "media pause", Toast.LENGTH_SHORT).show()
//            }
//        }

        // Pause the media player
//        binding.pauseBtn.setOnClickListener {
//            if (mediaPlayer.isPlaying) {
//                mediaPlayer.pause()
//                pause = true
//                binding.playBtn.isEnabled = true
//                binding.pauseBtn.isEnabled = false
//                binding.stopBtn.isEnabled = true
//                Toast.makeText(this, "media pause", Toast.LENGTH_SHORT).show()
//            }
//        }
        // Stop the media player
//        binding.stopBtn.setOnClickListener {
//            if (mediaPlayer.isPlaying || pause.equals(true)) {
//                pause = false
//                binding.seekBar.setProgress(0)
//                mediaPlayer.stop()
//                mediaPlayer.reset()
//                mediaPlayer.release()
//                handler.removeCallbacks(runnable)
//
//                binding.playBtn.isEnabled = true
//                binding.pauseBtn.isEnabled = false
//                binding.stopBtn.isEnabled = false
//
//                binding.tvPass.text = ""
//                binding.tvDue.text = ""
//                Toast.makeText(this, "media stop", Toast.LENGTH_SHORT).show()
//            }
//        }
        // Seek bar change listener
//        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
//                if (b) {
//                    mediaPlayer.seekTo(i * 1000)
//                }
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar) {
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar) {
//            }
//        })


    }

    // Method to initialize seek bar and audio stats
//    private fun initializeSeekBar() {
//        binding.seekBar.max = mediaPlayer.seconds
//
//        runnable = Runnable {
//            binding.seekBar.progress = mediaPlayer.currentSeconds
//
//            binding.tvPass.text = "${mediaPlayer.currentSeconds} sec"
//            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
//            binding.tvDue.text = "$diff sec"
//
//            handler.postDelayed(runnable, 1000)
//        }
//        handler.postDelayed(runnable, 1000)
//    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }


    fun swapView(v: View?) {
        val changeBounds: Transition = ChangeBounds()
        changeBounds.setInterpolator(OvershootInterpolator())

        TransitionManager.beginDelayedTransition(layout, changeBounds)

        altLayout = if (!altLayout) {
            constraintSetNew.applyTo(layout)
            true
        } else {
            constraintSetOld.applyTo(layout)
            false
        }
    }


}
