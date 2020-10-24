package com.example.week3.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.feed.Itinerary
import com.example.week3.model.feed.Tag
import kotlinx.android.synthetic.main.item_itinerary.view.*
import kotlinx.android.synthetic.main.item_lisst_tag.view.*
import kotlinx.android.synthetic.main.item_list_iti.view.*
import kotlinx.android.synthetic.main.item_list_iti.view.tv_title_iti_list

class ListTagAdapter ( private val list: ArrayList<Tag>):
    RecyclerView.Adapter<ListTagAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: Tag, position: Int) {

            itemView.tv_tag.text= list.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_lisst_tag,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(list[position],position)


    }


    override fun getItemCount(): Int {
        return list.size
    }




}
