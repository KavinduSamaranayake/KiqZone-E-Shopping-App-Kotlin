package com.saegis.kiqzone.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saegis.kiqzone.Activity.DetailActivity
import com.saegis.kiqzone.Domain.Products
import com.saegis.kiqzone.R

class BestProductAdapter(private var items: ArrayList<Products?>) :
    RecyclerView.Adapter<BestProductAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_best_deal, parent, false)
        return ViewHolder(inflate)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxt.text = items[position]?.title
        holder.priceTxt.text = "$" + items[position]?.price

        holder.starTxt.text = items[position]?.star.toString()

        Glide.with(context!!)
            .load(items[position]?.imagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        var priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        var starTxt: TextView = itemView.findViewById(R.id.startTxt)
        var pic: ImageView = itemView.findViewById(R.id.pic)
    }
}
