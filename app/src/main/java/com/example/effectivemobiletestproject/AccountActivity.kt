package com.example.effectivemobiletestproject

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
                    startActivity(
                        android.content.Intent(
                            this,
                            MainActivity::class.java
                        )
                    )
                    finish()
                    true
                }

                R.id.navigation_favorites -> {
                    startActivity(
                        android.content.Intent(
                            this,
                            FavoritesActivity::class.java
                        )
                    )
                    finish()
                    true
                }

                R.id.navigation_account -> true // уже на этом экране

                else -> false
            }
        }
    }
}


