package com.saegis.kiqzone.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saegis.kiqzone.Adapter.CartAdapter
import com.saegis.kiqzone.Helper.ManagmentCart
import com.saegis.kiqzone.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private var binding: ActivityCartBinding? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var managmentCart: ManagmentCart? = null
    private var tax = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        calculateCart()
        initList()
    }

    private fun initList() {
        if (managmentCart!!.listCart.isEmpty()) {
            binding!!.emptyTxt.visibility = View.VISIBLE
            binding!!.scrollviewCart.visibility = View.GONE
        } else {
            binding!!.emptyTxt.visibility = View.GONE
            binding!!.scrollviewCart.visibility = View.VISIBLE
        }

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.cardView.layoutManager = linearLayoutManager
        adapter = CartAdapter(managmentCart!!.listCart, this) { calculateCart() }
        binding!!.cardView.adapter = adapter
    }

    private fun calculateCart() {
        val percentTax = 0.02 // 2% tax
        val delivery = 10.0 //10 Dollar

        tax = (Math.round(managmentCart!!.totalFee * percentTax * 100.0) / 100).toDouble()

        val total = (Math.round((managmentCart!!.totalFee + tax + delivery) * 100) / 100).toDouble()
        val itemTotal = (Math.round(managmentCart!!.totalFee * 100) / 100).toDouble()

        binding!!.totalFeeTxt.text = "$$itemTotal"
        binding!!.taxTxt.text = "$$tax"
        binding!!.deliveryTxt.text = "$$delivery"
        binding!!.totalTxt.text = "$$total"
    }

    private fun setVariable() {
        binding!!.backBtn.setOnClickListener { v: View? -> finish() }
    }
}