package com.mrb.remember.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.mrb.remember.R.id
import com.mrb.remember.R.layout
import com.mrb.remember.R.string

class MainActivity : AppCompatActivity() {

  private lateinit var textMessage: TextView
  private val onNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        id.navigation_home -> {
          textMessage.setText(string.title_home)
          return@OnNavigationItemSelectedListener true
        }
        id.navigation_dashboard -> {
          textMessage.setText(string.title_dashboard)
          return@OnNavigationItemSelectedListener true
        }
        id.navigation_notifications -> {
          textMessage.setText(string.title_notifications)
          return@OnNavigationItemSelectedListener true
        }
      }
      false
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    val navView: BottomNavigationView = findViewById(id.nav_view)

    textMessage = findViewById(id.message)
    navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
  }

  companion object Navigator {

    fun open(context: Context) {
      val intent = Intent(context, MainActivity::class.java)
      context.startActivity(intent)
    }
  }
}
