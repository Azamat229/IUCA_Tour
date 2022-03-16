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
import java.util.concurrent.TimeUnit


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private lateinit var binding: FragmentMainPageBinding

    private lateinit var layout: ConstraintLayout
    private val constraintSetOld: ConstraintSet = ConstraintSet()
    private val constraintSetNew: ConstraintSet = ConstraintSet()
    private var altLayout = false
    private var idOfPlaces: Int = 1


    // Retrofit stuff
    lateinit var viewModel: MainViewModel

    // Media player
    private lateinit var mediaPlayer: MediaPlayer
    private var totalTime: Int = 0
    lateinit var runnable: Runnable
    private var handler = Handler()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPageBinding.bind(view)


        //mainP.<-id

        // Retrofit stuff
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        viewModel.getItemPlace(idOfPlaces)

        viewModel.myResponseItem.observe(viewLifecycleOwner, Observer { response ->

            if (response.isSuccessful) {


                binding.titlePlace.text = response.body()?.name

                val imageList = ArrayList<SlideModel>() // Create image list

                response.body()?.images?.forEach {
                    //    < - - - - SLIDER IMAGES- - - - >

                    val t = it.replace("[]", "")
                    var t2 = "http://159.89.97.31$t"
                    imageList.add(SlideModel(t2))
                }

                binding.descriptionText.text = response.body()?.desc

                val imageS = binding.imageSlider
                imageS.setImageList(imageList)

                var audio: String = response.body()?.audio.toString()
                audio = "http://159.89.97.31$audio"
                Log.d("response MEADI", audio)

                // < - - - MEDIA PLAYER - - - >
                val url = audio
                mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.setDataSource(url)
                mediaPlayer.isLooping = true
                mediaPlayer.prepare()
                totalTime = mediaPlayer.duration

                val duration = mediaPlayer.duration
                val sDuration: String = convertFormat(duration)
                binding.tvDue.text = sDuration

                binding.playBtn.setOnClickListener {

                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                        binding.playBtn.setImageResource(R.drawable.ic_stop)
                    } else {
                        mediaPlayer.pause()
                        binding.playBtn.setImageResource(R.drawable.ic_play_btn_6)
                    }
                }

                binding.ahead15.setOnClickListener {
                    var currentPosition = mediaPlayer.currentPosition
                    val duration: Int = mediaPlayer.duration
                    if (mediaPlayer.isPlaying && duration != currentPosition) {
                        currentPosition += 15000
                        binding.tvPass.setText(convertFormat(currentPosition)).toString()
                        mediaPlayer.seekTo(currentPosition)
                    }
                }

                binding.back15.setOnClickListener {
                    var currentPosition = mediaPlayer.currentPosition
                    val duration: Int = mediaPlayer.duration
                    if (mediaPlayer.isPlaying && duration != currentPosition) {
                        currentPosition -= 15000
                        binding.tvPass.setText(convertFormat(currentPosition)).toString()
                        mediaPlayer.seekTo(currentPosition)
                    }
                }

                binding.seekBar.progress = 0
                binding.seekBar.max = mediaPlayer.duration
                binding.seekBar.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                        if (changed) {
                            mediaPlayer.seekTo(position)
                        }
                        binding.tvPass.text = convertFormat(mediaPlayer.currentPosition)
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }

                })

                runnable = Runnable {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(runnable, 500)
                }
                handler.postDelayed(runnable, 1000)
                mediaPlayer.setOnCompletionListener {
                    binding.playBtn.setImageResource(R.drawable.ic_play_btn_6)
                    binding.seekBar.progress = 0
                }

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


    private fun convertFormat(duration: Int): String {
        return String.format(
            "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration.toLong()))
        )
    }
}