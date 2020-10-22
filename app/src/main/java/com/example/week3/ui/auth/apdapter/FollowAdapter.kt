package com.example.week3.ui.auth.apdapter

import android.annotation.SuppressLint
import com.example.week3.model.follow.FollowData
import kotlinx.android.synthetic.main.item_follow.view.*


import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.follow.Follow
import com.example.week3.model.interest.InterestData
import com.example.week3.utils.Constants
import com.example.week3.utils.OnClickItem
import kotlinx.android.synthetic.main.item_interest.view.*

class FollowAdapter( val onClickItem: OnClickItem) : RecyclerView.Adapter<FollowAdapter.ViewHolder>() {
    var list = ArrayList<Follow>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.tv_user
        var image1: ImageView = itemView.imageUser
        var imageUser: ImageView = itemView.imageFollow
        var image2: ImageView = itemView.imageUser2
        var image3: ImageView = itemView.imageUser3
        var tv_follow: TextView=itemView.btn_next_Fol

        fun bind(list: Follow, position: Int) {
            title.text = list.username
            if(list.profilePhoto==null){
                Glide.with(itemView)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSDGQO5-8CPA9Hzl5_wLkAf6VtlMw52q7IwRw&usqp=CAU")
                    .into(imageUser)
            }else{
                Glide.with(itemView)
                    .load(list.profilePhoto)
                    .into(imageUser)
            }
            if(list.photos==null){
                Glide.with(itemView)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhBXLbaCErzySoQH09QWCzLLZpb7Rc0okbJQ&usqp=CAU")
                    .into(image2)
            }else{
                Glide.with(itemView)
                    .load(list.photos[0].url.medium)
                    .into(image2)
            }
            if(list.photos!=null) {
                if (list.photos.size==2) {
                    Glide.with(itemView)
                        .load(list.photos[1].url.medium)
                        .into(image1)
                } else {
                    Glide.with(itemView)
                        .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhBXLbaCErzySoQH09QWCzLLZpb7Rc0okbJQ&usqp=CAU")
                        .into(image1)
                }
                if (list.photos.size==3) {
                    Glide.with(itemView)
                        .load(list.photos[2].url.medium)
                        .into(image3)
                } else {
                    Glide.with(itemView)
                        .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhBXLbaCErzySoQH09QWCzLLZpb7Rc0okbJQ&usqp=CAU")
                        .into(image3)
                }
            }else{
                Glide.with(itemView)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhBXLbaCErzySoQH09QWCzLLZpb7Rc0okbJQ&usqp=CAU")
                    .into(image3)
                Glide.with(itemView)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhBXLbaCErzySoQH09QWCzLLZpb7Rc0okbJQ&usqp=CAU")
                    .into(image1)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_follow,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = list?.get(position)
        holder.bind(element as Follow, position)

        if(element.checkfollow){
            holder.tv_follow.text =" Unfollow"
            holder.tv_follow.backgroundTintList =  ContextCompat.getColorStateList(
                holder.itemView.context, R.color.colorPurple)
        }else{
            holder.tv_follow.text =" Follow"
            holder.tv_follow.backgroundTintList =  ContextCompat.getColorStateList(
                holder.itemView.context, R.color.colorGrayEnable)
        }

        holder.tv_follow.setOnClickListener {
            element.checkfollow = !element.checkfollow


            onClickItem.OnClickItem(element.id)

            if(element.checkfollow){
                holder.tv_follow.text =" Unfollow"
                holder.tv_follow.backgroundTintList =  ContextCompat.getColorStateList(
                    holder.itemView.context, R.color.colorPurple)
            }else{
                holder.tv_follow.text =" Follow"
                holder.tv_follow.backgroundTintList =  ContextCompat.getColorStateList(
                    holder.itemView.context, R.color.colorGrayEnable)
            }
        }



    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(items: MutableList<Follow>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

}

