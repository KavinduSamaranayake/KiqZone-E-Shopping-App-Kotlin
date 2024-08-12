package com.saegis.kiqzone.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Source
import com.saegis.kiqzone.Admin.AdminDashboard
import com.saegis.kiqzone.R

class LoginActivity : BaseActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var gotoRegister: TextView
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.LoginBtn)
        gotoRegister = findViewById(R.id.dontHaveRegister)

        // Create a notification channel
        createNotificationChannel()

//        loginBtn.setOnClickListener {
//            if (checkField(email) && checkField(password)) {
//                fAuth.signInWithEmailAndPassword(
//                    email.text.toString(), password.text.toString()
//                ).addOnSuccessListener { authResult ->
//                    Toast.makeText(this@LoginActivity, "Login Successfully.", Toast.LENGTH_SHORT).show()
//                    checkUserAccessLevel(authResult.user!!.uid)
//                    // Show discount notification
//                    showDiscountNotification()
//                }.addOnFailureListener { e ->
//                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        loginBtn.setOnClickListener {
            if (checkField(email) && checkField(password)) {
                fAuth.signInWithEmailAndPassword(
                    email.text.toString(), password.text.toString()
                ).addOnSuccessListener { authResult ->
                    Toast.makeText(this@LoginActivity, "Login Successfully.", Toast.LENGTH_SHORT).show()
                    checkUserAccessLevel(authResult.user!!.uid)
                    showDiscountNotification()
                }.addOnFailureListener { e ->
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


        findViewById<ImageView>(R.id.loginBackBtn).setOnClickListener {
            startActivity(Intent(this@LoginActivity, IntroActivity::class.java))
        }

        gotoRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun checkUserAccessLevel(uid: String) {
        val df = fStore.collection("Users").document(uid)

        df.get().addOnSuccessListener { documentSnapshot ->
            Log.d("TAG", "onSuccess: ${documentSnapshot.data}")
            when {
                documentSnapshot.getString("isAdmin") != null -> {
                    startActivity(Intent(applicationContext, AdminDashboard::class.java))
                }
                documentSnapshot.getString("isUser") != null -> {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                }
            }
            finish()
        }

    }

//    private fun checkUserAccessLevel(uid: String) {
//        // Ensure Firebase Firestore offline persistence is enabled
//        FirebaseFirestore.getInstance().firestoreSettings = FirebaseFirestoreSettings.Builder()
//            .setPersistenceEnabled(true)
//            .build()
//
//        // Ensure user is authenticated
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser == null) {
//            // Handle user not authenticated scenario
//            Log.d("TAG", "User is not authenticated")
//            return
//        }
//
//        val df = fStore.collection("Users").document(uid)
//
//        df.get(Source.CACHE).addOnSuccessListener { documentSnapshot ->
//            if (documentSnapshot.exists()) {
//                Log.d("TAG", "onSuccess: ${documentSnapshot.data}")
//                when {
//                    documentSnapshot.getString("isAdmin") != null -> {
//                        startActivity(Intent(applicationContext, AdminDashboard::class.java))
//                    }
//                    documentSnapshot.getString("isUser") != null -> {
//                        startActivity(Intent(applicationContext, HomeActivity::class.java))
//                    }
//                }
//                finish()
//            } else {
//                Log.d("TAG", "Document does not exist")
//            }
//        }.addOnFailureListener { exception ->
//            Log.e("TAG", "Error getting document: ", exception)
//            // Handle the error
//        }
//    }



    private fun checkField(textField: EditText): Boolean {
        return if (textField.text.toString().isEmpty()) {
            textField.error = "Error"
            false
        } else {
            true
        }
    }

    override fun onStart() {
        super.onStart()
        if (fAuth.currentUser != null) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DiscountChannel"
            val descriptionText = "Channel for discount notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("01", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    private fun showDiscountNotification() {
        Log.d("Notification", "Attempting to show notification")

        val builder = NotificationCompat.Builder(this, "01")
            .setSmallIcon(R.drawable.discount_percent_svgrepo) // Ensure this resource is correct
            .setContentTitle("Exclusive Discounts!")
            .setContentText("Check out our latest discounts available now.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(this@LoginActivity)
        notificationManager.notify(1, builder.build())

        Log.d("Notification", "Notification shown")
    }

}
