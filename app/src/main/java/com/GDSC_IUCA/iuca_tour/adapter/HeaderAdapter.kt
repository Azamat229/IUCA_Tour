package com.GDSC_IUCA.iuca_tour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.GDSC_IUCA.iuca_tour.R

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var flowerCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flowerNumberTextView: TextView = itemView.findViewById(R.id.mtrl_card_checked_layer_id)

        fun bind(flowerCount: Int) {
            flowerNumberTextView.text = flowerCount.toString()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderAdapter.HeaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_nav_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderAdapter.HeaderViewHolder, position: Int) {
        holder.bind(flowerCount)
    }



    override fun getItemCount(): Int {
        return 1
    }

    fun updateFlowerCount(updateFlowerCount: Int){
        flowerCount = updateFlowerCount
        notifyDataSetChanged()
    }
}