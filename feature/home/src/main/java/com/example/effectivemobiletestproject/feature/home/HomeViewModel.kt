package com.example.effectivemobiletestproject.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestproject.core.network.ApiClient
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана списка курсов.
 * Инкапсулирует загрузку данных и бизнес-логику (MVVM).
 */
class HomeViewModel : ViewModel() {

    private val _courses = MutableLiveData<List<CourseItem>>(emptyList())
    val courses: LiveData<List<CourseItem>> = _courses

    /**
     * Загрузка списка курсов из репозитория.
     */
    fun loadCourses() {
        if (_courses.value?.isNotEmpty() == true) return

        viewModelScope.launch {
            try {
                val loadedCourses = ApiClient.coursesRepository.getCourses()
                val items = loadedCourses.map { course ->
                    CourseItem(course = course, isSelected = false)
                }
                _courses.value = items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Сортировка по дате публикации (убывание).
     */
    fun sortByPublishDateDesc() {
        val current = _courses.value ?: return
        _courses.value = current.sortedByDescending { it.publishDate }
    }

    /**
     * Локальное переключение состояния закладки.
     */
    fun toggleBookmark(courseId: Int) {
        val current = _courses.value ?: return
        _courses.value = current.map { item ->
            if (item.id == courseId) {
                item.copy(isSelected = !item.isSelected)
            } else {
                item
            }
        }
    }
}


