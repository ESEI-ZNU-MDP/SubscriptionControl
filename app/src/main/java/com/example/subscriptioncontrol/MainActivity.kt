package com.example.subscriptioncontrol // замените на ваш пакет

import Subscription
import SubscriptionAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val subscriptions = mutableListOf<Subscription>()
    private lateinit var subscriptionAdapter: SubscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.subscriptionAdapter = SubscriptionAdapter(this, subscriptions)
        val subscriptionList = findViewById<ListView>(R.id.subscriptionList);
        subscriptionList.adapter = subscriptionAdapter

        val addSubcription = findViewById<Button>(R.id.addSubcription);

        addSubcription.setOnClickListener {
            showAddSubscriptionDialog()
        }
    }

    private fun showAddSubscriptionDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_add_subscription, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Добавить подписку")

        val alertDialog = dialogBuilder.show()

        val editTextSubscription = dialogView.findViewById<EditText>(R.id.editTextSubscription)
        val editTextNumber1 = dialogView.findViewById<EditText>(R.id.editTextNumber1)
        val editTextNumber2 = dialogView.findViewById<EditText>(R.id.editTextNumber2)
        val buttonAdd = dialogView.findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val subscriptionText = editTextSubscription.text.toString()
            val number1 = editTextNumber1.text.toString().toInt()
            val number2 = editTextNumber2.text.toString().toInt()

            val subscription = Subscription(subscriptionText, number1, number2)
            subscriptions.add(subscription)
            subscriptionAdapter.notifyDataSetChanged()

            alertDialog.dismiss()
        }
    }
}
