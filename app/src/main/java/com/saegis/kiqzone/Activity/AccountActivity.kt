package com.saegis.kiqzone.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.saegis.kiqzone.R
import com.saegis.kiqzone.databinding.ActivityAccountBinding
import com.saegis.kiqzone.databinding.ActivityHomeBinding

class AccountActivity : AppCompatActivity() {
    private var binding: ActivityAccountBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_account)


        val backBtn = findViewById<ImageView>(R.id.backBtnTo)
        backBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }
}