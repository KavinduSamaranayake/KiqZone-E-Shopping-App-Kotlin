package com.saegis.kiqzone.Helper

import android.content.Context
import android.widget.Toast
import com.saegis.kiqzone.Domain.Products




class ManagmentCart(private val context: Context) {
//    private val tinyDB = TinyDB(context)

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
//            listpop[n].numberInCart = item.numberInCart
        } else {
            listpop.add(item)
        }
        tinyDB.putListObject("CartList", listpop)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }




}


