package com.saegis.kiqzone.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
//import com.saegis.kiqzone.Adapter.CategoryAdapter
import com.saegis.kiqzone.Domain.Category
import com.saegis.kiqzone.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, IntroActivity::class.java))
            finish()
        }

//        initBestProduct()
//        initCategory()
        setVariable()
    }

    private fun setVariable() {
//        binding.logOutBtn.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(this@HomeActivity, IntroActivity::class.java))
//        }
//        binding.searchButton.setOnClickListener {
//            val text = binding.searchEdit.text.toString()
//            if (text.isNotEmpty()) {
//                val intent = Intent(this@HomeActivity, ListProductActivity::class.java).apply {
//                    putExtra("text", text)
//                    putExtra("isSearch", true)
//                }
//                startActivity(intent)
//            }
//        }
//        binding.cartButton.setOnClickListener {
//            startActivity(Intent(this@HomeActivity, CartActivity::class.java))
//        }
//        binding.cartNavBtn.setOnClickListener {
//            startActivity(Intent(this@HomeActivity, CartActivity::class.java))
//        }
//        binding.userBtn.setOnClickListener {
//            startActivity(Intent(this@HomeActivity, AccountActivity::class.java))
        }
    }

//    private fun initBestProduct() {
//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("Products")
//        myRef.keepSynced(true)
//
//        binding.progressBarBestProduct.visibility = View.VISIBLE
//        binding.main.visibility = View.VISIBLE
//        val list = ArrayList<Products?>()
//
//        val query = myRef.orderByChild("BestProduct").equalTo(true)
//        query.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (issue in snapshot.children) {
//                        list.add(issue.getValue(Products::class.java))
//                    }
//                    if (list.isNotEmpty()) {
//                        binding.bestProductView.layoutManager = LinearLayoutManager(
//                            this@HomeActivity,
//                            LinearLayoutManager.HORIZONTAL,
//                            false
//                        )
//                        val adapter = BestProductAdapter(list)
//                        binding.bestProductView.adapter = adapter
//                    }
//                    binding.progressBarBestProduct.visibility = View.GONE
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("FirebaseError", error.message)
//            }
//        })
//    }

    private fun initCategory() {
//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("Category")
//        myRef.keepSynced(true)
//        binding.progressBarCategory.visibility = View.VISIBLE
//        val list = ArrayList<Category?>()
//
//        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (issue in snapshot.children) {
//                        list.add(issue.getValue(Category::class.java))
//                    }
//                    if (list.isNotEmpty()) {
//                        binding.categoryView.layoutManager = GridLayoutManager(this@HomeActivity, 4)
//                        val adapter = CategoryAdapter(list as ArrayList<Category>)
//                        binding.categoryView.adapter = adapter
//                    }
//                    binding.progressBarCategory.visibility = View.GONE
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("FirebaseError", error.message)
//            }
//        })
    }

