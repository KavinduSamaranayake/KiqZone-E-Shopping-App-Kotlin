package com.saegis.kiqzone.Helper

import android.content.Context
import android.widget.Toast
import com.saegis.kiqzone.Domain.Products




class ManagmentCart(private val context: Context) {
    private val tinyDB = TinyDB(context)

    fun insertProduct(item: Products) {
        val listpop = listCart.toMutableList() // Convert to mutable list to avoid immutability issues
        var existAlready = false
        var n = 0
        for (i in listpop.indices) {
            if (listpop[i].title == item.title) {
                existAlready = true
                n = i
                break
            }
        }

        if (existAlready) {

            //
            listpop[n].numberInCart = item.numberInCart
        } else {
            listpop.add(item)
        }
        tinyDB.putListObject("CartList", listpop)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }
    val listCart: ArrayList<Products>
        get() {
            val list = tinyDB.getListObject<Products>("CartList")
            return if (list == null) arrayListOf() else list
        }

    val totalFee: Double
        get() {
            val listItem = listCart
            var fee = 0.0
            for (item in listItem) {
                fee += (item.price * item.numberInCart)
            }
            return fee
        }

    fun minusNumberItem(
        listItem: ArrayList<Products>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listItem[position].numberInCart == 1) {
            listItem.removeAt(position)
        } else {
            listItem[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItem)
        changeNumberItemsListener.change()
    }

    fun plusNumberItem(
        listItem: ArrayList<Products>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        listItem[position].numberInCart++
        tinyDB.putListObject("CartList", listItem)
        changeNumberItemsListener.change()
    }



}


