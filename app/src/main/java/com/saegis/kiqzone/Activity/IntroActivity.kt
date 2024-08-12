package com.saegis.kiqzone.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.saegis.kiqzone.R

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.parseColor("#ffe4B5")


        val win = window
        win.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_intro)
        val SignInText = findViewById<TextView>(R.id.loginText)
        SignInText.setOnClickListener { // Start the SignInActivity
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        val signUpText = findViewById<TextView>(R.id.registerText)
        signUpText.setOnClickListener { // Start the SignInActivity
            val intent = Intent(this@IntroActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}