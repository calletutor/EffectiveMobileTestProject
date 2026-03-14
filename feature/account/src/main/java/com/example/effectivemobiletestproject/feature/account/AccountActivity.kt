package com.example.effectivemobiletestproject.feature.account

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomBar)
        bottomBar.selectedItemId = R.id.navigation_account

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.home.MainActivity"))
                    finish()
                    true
                }

                R.id.navigation_favorites -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.favorites.FavoritesActivity"))
                    finish()
                    true
                }

                R.id.navigation_account -> true // уже на этом экране

                else -> false
            }
        }
    }
}


