package com.saegis.kiqzone.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saegis.kiqzone.Activity.ListProductActivity
import com.saegis.kiqzone.Domain.Category
import com.saegis.kiqzone.R

class CategoryAdapter(private val items: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxt.text = items[position].name

        when (position) {
            0 -> holder.pic.setBackgroundResource(R.drawable.cat_0_background)
            1 -> holder.pic.setBackgroundResource(R.drawable.cat_1_background)
            2 -> holder.pic.setBackgroundResource(R.drawable.cat_2_background)
            3 -> holder.pic.setBackgroundResource(R.drawable.cat_3_background)
            4 -> holder.pic.setBackgroundResource(R.drawable.cat_4_background)
            5 -> holder.pic.setBackgroundResource(R.drawable.cat_5_background)
            6 -> holder.pic.setBackgroundResource(R.drawable.cat_6_background)
            7 -> holder.pic.setBackgroundResource(R.drawable.cat_7_background)
        }

        val drawableResourceId = context.resources.getIdentifier(items[position].imagePath, "drawable", context.packageName)

        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ListProductActivity::class.java).apply {
                putExtra("CategoryId", items[position].id)
                putExtra("CategoryName", items[position].name)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.catNameTxt)
        val pic: ImageView = itemView.findViewById(R.id.imgCat)
    }
}
