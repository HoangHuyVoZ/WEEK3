package com.example.week3.ui.main.adapter


import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.feed.Itinerary
import com.example.week3.model.interest.InterestData
import com.example.week3.utils.Constants
import com.example.week3.utils.OnClickItem
import com.example.week3.utils.View.Companion.gone
import kotlinx.android.synthetic.main.item_interest.view.*
import kotlinx.android.synthetic.main.item_itinerary.view.*
import kotlinx.android.synthetic.main.item_list_iti.view.*

class ListItineraryAdapter ( private val list: ArrayList<Itinerary>):
    RecyclerView.Adapter<ListItineraryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: Itinerary, position: Int) {

            Glide.with(itemView)
                .load(list.photos.url.medium)
                .error(R.drawable.noimage)
                .into(itemView.imag_iti)
            itemView.tv_title_iti_list.text = list.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_iti,
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
