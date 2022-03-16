package com.GDSC_IUCA.iuca_tour.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.GDSC_IUCA.iuca_tour.databinding.ActivityRecyclerBinding
import com.GDSC_IUCA.iuca_tour.models.DataSource
import java.lang.StringBuilder


class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private lateinit var blogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }

    private fun initRecyclerView() {


        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            //layoutManager, Grid, StaggeredGrid

            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)

            blogAdapter = BlogRecyclerAdapter()
            //??
            adapter = blogAdapter
            //??
        }

//        alternative version of ini

//        binding.recyclerView.layoutManager = LinearLayoutManager(this@RecyclerActivity)
//        blogAdapter = BlogRecyclerAdapter()
//        binding.recyclerView.adapter = blogAdapter

    }
}