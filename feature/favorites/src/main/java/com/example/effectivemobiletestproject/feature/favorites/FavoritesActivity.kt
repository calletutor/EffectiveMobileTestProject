package com.example.effectivemobiletestproject.feature.favorites

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomBar)
        bottomBar.selectedItemId = R.id.navigation_favorites

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.home.MainActivity"))
                    finish()
                    true
                }

                R.id.navigation_favorites -> true // уже на этом экране

                R.id.navigation_account -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.account.AccountActivity"))
                    finish()
                    true
                }

                else -> false
            }
        }
    }
}


