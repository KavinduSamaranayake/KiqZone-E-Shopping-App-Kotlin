package com.saegis.kiqzone.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.saegis.kiqzone.R

open class BaseActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    @JvmField
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)

        database = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()

        //        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}