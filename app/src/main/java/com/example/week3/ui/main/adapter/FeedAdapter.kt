package com.example.week3.ui.main.adapter

import com.example.week3.model.feed.FeedData


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.R
import com.example.week3.model.feed.Itinerary
import com.example.week3.model.feed.Tag
import com.example.week3.utils.View.Companion.gone
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.visible
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.item_itinerary.view.*
import kotlinx.android.synthetic.main.item_itinerary.view.tv_user_po
import kotlinx.android.synthetic.main.item_poi.view.*
import kotlinx.android.synthetic.main.item_post.view.*


class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_ONE = 0
        const val VIEW_TYPE_TWO = 1
        const val VIEW_TYPE_THREE = 2
    }

    private var list = ArrayList<FeedData>()

    private inner class ViewIntiViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val viewPool = RecyclerView.RecycledViewPool()
            val feed = list[position]
            itemView.tv_title_Iti.text = feed.title
            itemView.tv_user_po.text = feed.user?.username
            itemView.tv_des_iti.text = feed.description
            itemView.tv_heard_iti.text = feed.likeCounter.toString()
            itemView.tv_comment_iti.text = feed.commentCounter.toString()
            if(feed.isLiked){
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart)
                    .into(itemView.like_ite)
            }else{
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart_noselect)
                    .into(itemView.like_ite)
            }
            if(feed.isString){
                itemView.btnString.isSelected = true
                itemView.btnString.text=feed.strungCounter.toString()
                itemView.btnString.setTextColor( ContextCompat.getColorStateList(
                    itemView.context,
                    R.color.colorWhite
                ))
                itemView.btnSave.setCompoundDrawablesWithIntrinsicBounds(
                    itemView.context.getDrawable(R.drawable.ic_string_selected), null, null, null
                )
            }else{
                itemView.btnString.isSelected = false
                itemView.btnString.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorPurple
                    )
                )
                itemView.btnString.setCompoundDrawablesWithIntrinsicBounds(
                    itemView.context.getDrawable(R.drawable.ic_string), null, null, null
                )
            }

            Glide.with(itemView)
                .load(feed.user?.profilePhoto)
                .error(R.drawable.profile_selector)
                .into(itemView.image_user_iti)
            if(feed.itineraries!=null){
                itemView.recyclerViewIti.apply {
                    layoutManager =
                        LinearLayoutManager(
                            itemView.recyclerViewIti.context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    adapter = ListItineraryAdapter(feed.itineraries as ArrayList<Itinerary>)
                    setRecycledViewPool(viewPool)
                }
            }

        }
    }


    private inner class ViewPostViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val feed = list[position]
            val viewPool = RecyclerView.RecycledViewPool()
            Glide.with(itemView)
                .load(feed.user?.profilePhoto)
                .error(R.drawable.profile_selector)
                .into(itemView.image_user_post)
            itemView.tv_time.text = feed.updatedAt
            itemView.tv_user_post.text = feed.user?.username
            itemView.tv_des_post.text = feed.description

            itemView.tv_post_location.text = feed.place?.address
            if (feed.isSaved) {
                itemView.btnSave.isSelected = true
                itemView.btnSave.text = feed.saveCounter.toString()
                itemView.btnSave.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorWhite
                    )
                )
                itemView.btnSave.setCompoundDrawablesWithIntrinsicBounds(
                    itemView.context.getDrawable(R.drawable.ic_save_selected), null, null, null
                )
            } else {
                itemView.btnSave.isSelected = false
                itemView.btnSave.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.colorPurple
                    )
                )
                itemView.btnSave.setCompoundDrawablesWithIntrinsicBounds(
                    itemView.context.getDrawable(R.drawable.ic_save), null, null, null
                )
            }
            if (feed.isLiked) {
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart)
                    .into(itemView.like_Post)
            } else {
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart_noselect)
                    .into(itemView.like_Post)
            }
            if (feed.tags != null) {
                itemView.recyclerViewPost_tag.apply {
                    layoutManager =
                        GridLayoutManager(
                            itemView.recyclerViewPost_tag.context,
                            4,
                            GridLayoutManager.VERTICAL,
                            false
                        )
                    adapter = ListTagAdapter(feed.tags as ArrayList<Tag>)
                    setRecycledViewPool(viewPool)
                }
            }

            itemView.tv_heard_post.text = feed.likeCounter.toString()
            itemView.tv_comment_post.text = feed.commentCounter.toString()
            if (feed.videos != null) {
                Glide.with(itemView)
                    .load(feed.videos.thumbs)
                    .error(R.drawable.noimage)
                    .into(itemView.image_post)

                itemView.image_post_list1.gone()
                itemView.image_post2_list1.gone()
                itemView.image_grad_lis1.gone()
                itemView.image_post_list2.gone()
                itemView.image_post2_list2.gone()
                itemView.image_post2_list3.gone()
                itemView.image_grad_lis2.gone()
                itemView.image_post3_grad.gone()
                itemView.count_list.gone()
            }
            if (feed.photos == null) {
                itemView.videos.gone()
                itemView.image_post_list1.gone()
                itemView.image_post2_list1.gone()
                itemView.image_grad_lis1.gone()
                itemView.image_post_list2.gone()
                itemView.image_post2_list2.gone()
                itemView.image_post2_list3.gone()
                itemView.image_grad_lis2.gone()
                itemView.image_post3_grad.gone()
                itemView.count_list.gone()
                itemView.image_post.gone()
            } else {
                when {
                    feed.photos?.size == 1 -> {

                        Glide.with(itemView)
                            .load(feed.photos[0].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post)

                        itemView.videos.gone()
                        itemView.image_post_list1.gone()
                        itemView.image_post2_list1.gone()
                        itemView.image_grad_lis1.gone()
                        itemView.image_post_list2.gone()
                        itemView.image_post2_list2.gone()
                        itemView.image_post2_list3.gone()
                        itemView.image_grad_lis2.gone()
                        itemView.image_post3_grad.gone()
                        itemView.count_list.gone()
                    }
                    feed.photos?.size == 2 -> {
                        itemView.image_post.gone()
                        itemView.image_post_list2.gone()
                        itemView.image_post2_list2.gone()
                        itemView.image_post2_list3.gone()
                        itemView.image_grad_lis2.gone()
                        itemView.image_post3_grad.gone()
                        itemView.count_list.gone()
                        itemView.videos.gone()

                        Glide.with(itemView)
                            .load(feed.photos[0].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post_list1)
                        Glide.with(itemView)
                            .load(feed.photos[1].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post2_list1)

                    }
                    feed.photos?.size == 3 -> {
                        itemView.image_post.gone()
                        itemView.image_post_list1.gone()
                        itemView.image_post2_list1.gone()
                        itemView.image_grad_lis1.gone()
                        itemView.videos.gone()

                        itemView.image_post3_grad.gone()
                        itemView.count_list.gone()
                        Glide.with(itemView)
                            .load(feed.photos[0].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post_list2)
                        Glide.with(itemView)
                            .load(feed.photos[1].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post2_list2)
                        Glide.with(itemView)
                            .load(feed.photos[2].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post2_list3)
                    }
                    feed.photos?.size!! >= 4 -> {
                        itemView.image_post.gone()
                        itemView.image_post_list1.gone()
                        itemView.image_post2_list1.gone()
                        itemView.image_grad_lis1.gone()
                        itemView.videos.gone()

                        Glide.with(itemView)
                            .load(feed.photos[0].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post_list2)
                        Glide.with(itemView)
                            .load(feed.photos[1].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post2_list2)
                        Glide.with(itemView)
                            .load(feed.photos[2].url.original)
                            .error(R.drawable.noimage)
                            .into(itemView.image_post2_list3)

                    }
                }
            }
        }
    }


    private inner class ViewPoiViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val feed = list[position]
            val viewPool = RecyclerView.RecycledViewPool()
            itemView.tv_title_poi.text = feed.title
            itemView.tv_loaction.text = feed.address
            itemView.tv_heard_poi.text = feed.likeCounter.toString()
            itemView.tv_comment_poi.text = feed.commentCounter.toString()
            if(feed.isLiked){
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart)
                    .into(itemView.heard_poi)
            }else{
                Glide.with(itemView)
                    .load(R.drawable.ic_ico_heart_noselect)
                    .into(itemView.heard_poi)
            }
            if (feed.photos != null) {

                Glide.with(itemView)
                    .load(feed.user?.profilePhoto)
                    .error(R.drawable.profile_selector)
                    .into(itemView.image_user_poi)
                itemView.tv_user_po.text = feed.user?.username
                itemView.tv_heard_poi.text = feed.likeCounter.toString()
                itemView.tv_comment_poi.text = feed.commentCounter.toString()
                Glide.with(itemView)
                    .load(feed.photos?.get(0)?.url?.medium)
                    .error(R.drawable.noimage)
                    .into(itemView.image_poi)
                if(feed.tags!=null){
                    itemView.recyclerView_tag.apply {
                        layoutManager =
                            GridLayoutManager(itemView.recyclerView_tag.context, 4, GridLayoutManager.VERTICAL, false)
                        adapter = ListTagAdapter(feed.tags as ArrayList<Tag>)
                        setRecycledViewPool(viewPool)
                    }
                }
                itemView.image_poi_no.gone()
                itemView.image_location_no.gone()
            } else {
                itemView.image_user_poi.gone()
                itemView.tv_user_po.gone()
                itemView.image_poi.gone()
                itemView.recyclerView_tag.gone()
                itemView.start_poi.gone()
                itemView.image_grad_poi.gone()

                itemView.image_poi_no.visible()
                itemView.image_location_no.visible()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> ViewIntiViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_itinerary, parent, false)
            )

            1-> ViewPostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            )

            2 -> ViewPoiViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_poi, parent, false)
            )
            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = list?.get(position)
        when (holder) {
            is ViewIntiViewHolder -> holder.bind(  position)
            is ViewPostViewHolder -> holder.bind( position)
            is ViewPoiViewHolder -> holder.bind( position)
            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            "itinerary" -> VIEW_TYPE_ONE

            "post" -> VIEW_TYPE_TWO
            "poi" -> VIEW_TYPE_THREE
            else -> throw IllegalArgumentException("Invalid type of data $position")


        }
    }

    fun addList(items: MutableList<FeedData>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}





