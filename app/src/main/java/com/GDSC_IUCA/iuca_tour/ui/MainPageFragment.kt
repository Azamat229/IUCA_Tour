package com.GDSC_IUCA.iuca_tour.ui


import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.transition.*
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMainPageBinding
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.denzcoskun.imageslider.models.SlideModel
import java.io.IOException


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var layout: ConstraintLayout
    private val constraintSetOld: ConstraintSet = ConstraintSet()
    private val constraintSetNew: ConstraintSet = ConstraintSet()
    private var altLayout = false
    lateinit var runnable: Runnable
    private var handler = Handler()

    // Retrofit stuff
    lateinit var viewModel: MainViewModel

    lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)


        // Retrofit stuff
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPlace()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->


            if (response.isSuccessful) {
//                Log.d("response", response.body()?.get(0)?.id.toString())
//                Log.d("response", response.body()?.get(0)?.name.toString())
//                response.body()?.get(0)?.let { Log.d("response", it.name) }
//                response.body()?.get(0)?.let { Log.d("response", it.desc) }
                binding.titlePlace.text = response.body()?.get(0)?.name.toString()
//                binding.descriptionText.text = response.body()?.get(0)?.desc.toString()
                val test = response.body()?.get(0)?.images?.get(0)
                val test2 = test?.replace("[]", "")
                val test3 = "http://159.89.97.31:8000$test2"

                binding.descriptionText.text = test3

                //    < - - - - SLIDER IMAGES- - - - >
                val imageList = ArrayList<SlideModel>() // Create image list

                imageList.add(SlideModel(test3))

                imageList.add(SlideModel(test3))


                val imageS = binding.imageSlider
                imageS.setImageList(imageList)

                var audio: String = response.body()?.get(0)?.audio.toString()
                audio = "http://159.89.97.31:8000$audio"
                Log.d("response MEADI", audio)

                // < - - - MEDIA PLAYER - - - >

                val url = audio
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()




                binding.playBtn.setOnClickListener {

                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                        binding.playBtn.setImageResource(R.drawable.ic_stop)
                    } else {
                        mediaPlayer.pause()
                        binding.playBtn.setImageResource(R.drawable.ic_play_btn_6)
                    }
                }



                binding.seekBar.progress = 0
                binding.seekBar.max = mediaPlayer.duration

                binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                    override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                        if(changed){
                            mediaPlayer.seekTo(position)
                        }
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }

                })

                runnable = Runnable {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(runnable, 1000)
                }
                handler.postDelayed(runnable, 1000)
                mediaPlayer.setOnCompletionListener {
                    binding.playBtn.setImageResource(R.drawable.ic_play_btn_6)
                    binding.seekBar.progress = 0
                }

                // Method to initialize seek bar and audio stats



            } else {
                Log.d("response", response.errorBody().toString())
            }

        })


        // < - - -  SCROLLING - - - >
        binding.descriptionText.movementMethod = ScrollingMovementMethod()


        //    < - - - ANIMATION  - - - >
        layout = binding.layout
        constraintSetOld.clone(layout)
        constraintSetNew.clone(activity, R.layout.fragment_main_page_alt)





        binding.expandCollabseBtn.setOnClickListener {

            val changeBounds: Transition = ChangeBounds()
            changeBounds.interpolator = OvershootInterpolator()

            TransitionManager.beginDelayedTransition(layout, changeBounds)

            altLayout = if (!altLayout) {
                constraintSetNew.applyTo(layout)
                true
            } else {
                constraintSetOld.applyTo(layout)
                false
            }
        }


        binding.nextStationBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainPageFragment_to_testFragment)
        }




    }



//
//    // Creating an extension property to get the media player time duration in seconds
//    val MediaPlayer.seconds:Int
//        get() {
//            return this.duration / 1000
//        }
//    // Creating an extension property to get media player current position in seconds
//    val MediaPlayer.currentSeconds:Int
//        get() {
//            return this.currentPosition/1000
//        }





}