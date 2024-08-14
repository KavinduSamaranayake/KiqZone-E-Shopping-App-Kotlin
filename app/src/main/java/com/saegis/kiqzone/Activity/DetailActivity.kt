package com.saegis.kiqzone.Activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.saegis.kiqzone.Domain.Products
import com.saegis.kiqzone.Helper.ManagmentCart
import com.saegis.kiqzone.R
import com.saegis.kiqzone.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    var binding: ActivityDetailBinding? = null


    private var `object`: Products? = null
    private var num = 1

    private var managementCart: ManagmentCart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.statusBarColor = resources.getColor(R.color.black)

        intentExtra
        setVariable()
    }

    private fun setVariable() {
        managementCart = ManagmentCart(this)


        binding!!.backbtn.setOnClickListener { finish() }

        Glide.with(this@DetailActivity)
            .load(`object`!!.imagePath)
            .into(binding!!.pic)

        binding!!.priceTxt.text = "$" + `object`!!.price
        binding!!.titleTxt.text = `object`!!.title
        binding!!.descriptionTxt.text = `object`!!.description
        binding!!.rateTxt.text = `object`!!.star.toString() + " Rating"
        binding!!.ratingBar.rating = `object`!!.star.toFloat()
        binding!!.totalTxt.text = (num * `object`!!.price).toString() + "$"

        binding!!.plusBtn.setOnClickListener {
            num = num + 1
            binding!!.numText.text = "$num "
            binding!!.totalTxt.text = "$" + (num * `object`!!.price)
        }
        binding!!.minusBtn.setOnClickListener {
            if (num > 1) {
                num = num - 1
                binding!!.numText.text = "$num "
                binding!!.totalTxt.text = "$" + (num * `object`!!.price)
            }
        }

        binding!!.addBtn.setOnClickListener {
            `object`!!.numberInCart = num
            managementCart!!.insertProduct(`object`!!)
            showDiscountNotification()
        }
    }

    private val intentExtra: Unit
        get() {
            `object` = intent.getSerializableExtra("object") as Products?
        }

    @SuppressLint("MissingPermission")
    private fun showDiscountNotification() {
        Log.d("Notification", "Attempting to show notification")

        // Create the NotificationChannel if necessary (for API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Discount Channel"
            val description = "Channel for discount notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("01", name, importance)
            channel.description = description

            // Register the channel with the system
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, "01")
            .setSmallIcon(R.drawable.discount_percent_svgrepo) // Ensure this resource is correct
            .setContentTitle("Exclusive Discounts!")
            .setContentText("Add more Products latest discounts available now.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, builder.build())

        Log.d("Notification", "Notification shown")
    }
}