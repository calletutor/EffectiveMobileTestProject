package com.example.effectivemobiletestproject.feature.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestproject.core.network.ApiClient
import com.example.effectivemobiletestproject.core.network.dto.CourseDto
import com.example.effectivemobiletestproject.feature.course.CourseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var courses: List<CourseDto> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomBar)
        bottomBar.selectedItemId = R.id.navigation_home

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true // уже на главном

                R.id.navigation_favorites -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.favorites.FavoritesActivity"))
                    finish()
                    true
                }

                R.id.navigation_account -> {
                    startActivity(Intent().setClassName(this, "com.example.effectivemobiletestproject.feature.account.AccountActivity"))
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

        val sortDirectionIcon = findViewById<ImageView>(R.id.ivSortDirection)

        sortDirectionIcon.setOnClickListener {
            if (courses.isNotEmpty()) {
                val sorted = courses.sortedByDescending { it.publishDate }
                adapter.submitList(sorted)
            }
        }

        // Загрузка данных из API
        lifecycleScope.launch {
            try {
                val loadedCourses = ApiClient.coursesRepository.getCourses()
                courses = loadedCourses
                adapter.submitList(loadedCourses)
            } catch (e: Exception) {
                e.printStackTrace()
                // при желании можно показать ошибку пользователю
            }
        }
    }
}


