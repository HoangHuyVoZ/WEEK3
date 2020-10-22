package com.example.week3.ui.auth.apdapter

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
import com.example.week3.model.interest.InterestData
import com.example.week3.utils.Constants
import com.example.week3.utils.OnClickItem
import kotlinx.android.synthetic.main.item_interest.view.*

class InterestAdapter(private val onClickItem: OnClickItem) :
    RecyclerView.Adapter<InterestAdapter.ViewHolder>() {
    var list = ArrayList<InterestData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.tv_interest
        var image: ImageView = itemView.imageInterest
        var checkBoxin: ImageView = itemView.checkboxIn
        var imageView: ImageView = itemView.image_check
        val checkInterest: ArrayList<Int> = ArrayList()

        fun bind(list: InterestData, position: Int) {
            title.text = list.title
            Glide.with(itemView)
                .load(list.photo.url.medium)
                .into(image)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_interest,
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
        val checkInterest: ArrayList<Int> = ArrayList()
        var count =0
        holder.bind(element as InterestData, position)
        if (element.checkUserSellect == 1) {
            holder.checkBoxin.setImageResource(R.drawable.ic_checkbox_selected);
            holder.imageView.setImageResource(R.drawable.ic_grad_checkted);
        } else {
            holder.checkBoxin.setImageResource(R.drawable.ic_checkbox);
            holder.imageView.setImageResource(R.drawable.ic_grad);
        }
        holder.itemView.setOnClickListener {
            if (element.checkUserSellect == 1) {
                element.checkUserSellect = 0
            } else {
                element.checkUserSellect = 1
            }
            count =0
            for (item in list) {
                if (item.checkUserSellect == 1) {
                    count++
                }
            }
            onClickItem.OnClickItem(count)
            Log.d("dat", "$count $checkInterest")
            if (element.checkUserSellect == 1) {
                holder.checkBoxin.setImageResource(R.drawable.ic_checkbox_selected);
                holder.imageView.setImageResource(R.drawable.ic_grad_checkted);
            } else {
                holder.checkBoxin.setImageResource(R.drawable.ic_checkbox);
                holder.imageView.setImageResource(R.drawable.ic_grad);
            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(items: MutableList<InterestData>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun count(): Int {
        var count: Int = 0
        for (item in list) {
            if (item.checkUserSellect == 1) {
                count++
            }
        }
        return count
    }

    fun addlistInterest(): ArrayList<Int>? {
        val checkInterest: ArrayList<Int> = ArrayList()
        for (item in list) {
            if (item.checkUserSellect == 1) {
                checkInterest.add(item.id)
            }
        }
        return checkInterest
    }
}
