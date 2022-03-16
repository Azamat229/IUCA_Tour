package com.GDSC_IUCA.iuca_tour.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.models.BlogPost
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class BlogRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var item: List<BlogPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // parent get context?
        // для создания кажного элемента в Recycler view it's required for creating for each view holder in RV
        // keeps in memory and shown to user few above and below
        return BlogViewHolder(
            // create a new object from BlogViewHolder
            LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item2, parent, false)
        //LI - it takes an XML file as input and builds the View objects from it.
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        // количество элементов с данными
        // while crolling the RV onBindViewHolder upload data to current viewholder
        when(holder){
            // to bind data to particular view holder that is currently in view
            is BlogViewHolder -> {
                holder.bind(item.get(position)) // происходит связка данные и представления
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    // submit list of data to Recycler View
    fun submitList(blogList: List<BlogPost>){
        item = blogList
    }


    // View holder class
    // which data look like in RV
    // custom class which take data from dataset and display in RV
    class BlogViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        //View access to xml  tags

        private val blogTitle: TextView = itemView.findViewById(R.id.blog_title)
        private val blogImage: ImageView = itemView.findViewById(R.id.blog_image)
        private val blogAuthor: TextView = itemView.findViewById(R.id.blog_author)

        fun bind(blog: BlogPost){
            blogTitle.setText(blog.title)
            blogAuthor.setText(blog.userName)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context) // библиотека которая за на занимается асинхноными вызовами, кеширование и учит жиз цикол
                .applyDefaultRequestOptions(requestOptions)
                .load(blog.image)//data
                .into(blogImage)


        }
    }
}