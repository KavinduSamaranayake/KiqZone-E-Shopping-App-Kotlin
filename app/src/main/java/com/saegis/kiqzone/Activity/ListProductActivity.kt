package com.saegis.kiqzone.Activity


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.saegis.kiqzone.Adapter.ProductListAdapter
import com.saegis.kiqzone.Domain.Products
import com.saegis.kiqzone.databinding.ActivityListProductBinding


class ListProductActivity : BaseActivity() {
    var binding: ActivityListProductBinding? = null
    private var adapterListProduct: RecyclerView.Adapter<*>? = null
    private var categoryId = 0
    private var categoryName: String? = null
    private var searchText: String? = null
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initList()
        intentExtra
//        setVariable()
    }
    private fun initList() {
        val myRef = database!!.getReference("Products")
        myRef.keepSynced(true)

        binding!!.progressBar.visibility = View.VISIBLE
        val list = ArrayList<Products?>()

        val query = if (isSearch) {
            myRef.orderByChild("Title").startAt(searchText).endAt(searchText + '\uf8ff')
        } else {
            myRef.orderByChild("CategoryId").equalTo(categoryId.toDouble())
        }

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Products::class.java))
                    }
                    // Filter out null values from the list
                    val nonNullList = list.filterNotNull()
                    if (nonNullList.isNotEmpty()) {
                        binding!!.productListView.layoutManager =
                            GridLayoutManager(this@ListProductActivity, 2)
                        adapterListProduct = ProductListAdapter(nonNullList)
                        binding!!.productListView.adapter = adapterListProduct
                    }
                    binding!!.progressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private val intentExtra: Unit
        get() {
            categoryId = intent.getIntExtra("CategoryId", 0)
            categoryName = intent.getStringExtra("CategoryName")
            searchText = intent.getStringExtra("text")
            isSearch = intent.getBooleanExtra("isSearch", false)


            binding!!.titleTxt.text = categoryName

            binding!!.backBtn.setOnClickListener { v: View? -> finish() }
        }
}