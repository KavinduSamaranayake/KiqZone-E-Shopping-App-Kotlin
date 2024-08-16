package com.saegis.kiqzone.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saegis.kiqzone.Domain.Products
import com.saegis.kiqzone.Helper.ChangeNumberItemsListener
import com.saegis.kiqzone.Helper.ManagmentCart
import com.saegis.kiqzone.R

class CartAdapter(
    var list: ArrayList<Products>,
    context: Context?,
    var changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.viewholder>() {
    private val managmentCart = context?.let { ManagmentCart(it) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)

        return viewholder(inflate)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.title.text = list[position].title
        holder.feeEachItem.text = " $ " + (list[position].numberInCart * list[position].price)
        holder.totalEachItem.text =
            list[position].numberInCart.toString() + " * $" + (list[position].price
                    )
        holder.num.text = list[position].numberInCart.toString() + ""

        Glide.with(holder.itemView.context)
            .load(list[position].imagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.plusItem.setOnClickListener {
            managmentCart?.plusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }

        holder.minusItem.setOnClickListener {
            managmentCart?.minusNumberItem(list, position) {
                notifyDataSetChanged()
                changeNumberItemsListener.change()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTxt)
        var feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        var plusItem: TextView = itemView.findViewById(R.id.plusCartBtn)
        var minusItem: TextView = itemView.findViewById(R.id.minusCartBtn)
        var pic: ImageView = itemView.findViewById(R.id.pic)
        var totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        var num: TextView = itemView.findViewById(R.id.numberItemTxt)
    }
}
