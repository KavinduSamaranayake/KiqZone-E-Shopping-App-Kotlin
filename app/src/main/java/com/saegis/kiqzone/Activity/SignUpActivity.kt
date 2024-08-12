package com.saegis.kiqzone.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.saegis.kiqzone.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var phone: EditText
    private lateinit var registerBtn: Button
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore

    private var valid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        username = findViewById(R.id.registerName)
        email = findViewById(R.id.registerEmail)
        password = findViewById(R.id.registerPassword)
        phone = findViewById(R.id.registerPhone)
        registerBtn = findViewById(R.id.RegisterBtn)


        registerBtn.setOnClickListener {
            if (checkFields(username, email, password, phone)) {
                fAuth.createUserWithEmailAndPassword(
                    email.text.toString(),
                    password.text.toString()
                ).addOnSuccessListener { authResult ->
                    authResult.user?.let { user ->
                        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                        val userInfo = mapOf(
                            "Username" to username.text.toString(),
                            "UserEmail" to email.text.toString(),
                            "PhoneNumber" to phone.text.toString(),
                            "isUser" to "1"
                        )
                        fStore.collection("Users").document(user.uid).set(userInfo)
                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to Create Account", Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<ImageView>(R.id.signUpBackBtn).setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
        }

        findViewById<TextView>(R.id.gotoLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun checkFields(vararg fields: EditText): Boolean {
        valid = true
        fields.forEach { field ->
            if (field.text.toString().isEmpty()) {
                field.error = "Field cannot be empty"
                valid = false
            }
        }
        return valid
    }
}