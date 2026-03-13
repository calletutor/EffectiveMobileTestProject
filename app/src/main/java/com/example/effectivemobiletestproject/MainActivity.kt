package com.example.effectivemobiletestproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestproject.data.remote.ApiClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomBar)
        bottomBar.selectedItemId = R.id.navigation_home

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true // уже на главном

                R.id.navigation_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    finish()
                    true
                }

                R.id.navigation_account -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    finish()
                    true
                }

                else -> false
            }
        }

        // Настройка списка курсов
        val recyclerView = findViewById<RecyclerView>(R.id.rvCourses)
        val adapter = CoursesAdapter()
        recyclerView.adapter = adapter

        // Загрузка данных из API
        lifecycleScope.launch {
            try {
                val courses = ApiClient.coursesRepository.getCourses()
                adapter.submitList(courses)
            } catch (e: Exception) {
                e.printStackTrace()
                // при желании можно показать ошибку пользователю
            }
        }
    }
}
