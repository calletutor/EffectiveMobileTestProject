package com.example.effectivemobiletestproject.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import com.example.effectivemobiletestproject.core.data.repository.SelectedCourseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана списка курсов.
 * Инкапсулирует загрузку данных и бизнес-логику (MVVM).
 */
class HomeViewModel(
    private val coursesRepository: CoursesRepository,
    private val selectedCourseRepository: SelectedCourseRepository
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseItem>>(emptyList())
    val courses: LiveData<List<CourseItem>> = _courses

    /**
     * Загрузка списка курсов из репозитория с восстановлением состояния закладок из БД.
     */
    fun loadCourses() {
        if (_courses.value?.isNotEmpty() == true) return

        viewModelScope.launch {
            try {
                // Загружаем курсы из API
                val loadedCourses = coursesRepository.getCourses()
                
                // Загружаем выбранные курсы из БД
                val selectedCourseIds = selectedCourseRepository.getAllSelectedCourseIds().first().toSet()
                
                // Создаём CourseItem с правильным состоянием isSelected
                val items = loadedCourses.map { course ->
                    CourseItem(
                        course = course,
                        isSelected = selectedCourseIds.contains(course.id)
                    )
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
     * Переключение состояния закладки с сохранением в БД.
     */
    fun toggleBookmark(courseId: Int) {
        val current = _courses.value ?: return
        
        viewModelScope.launch {
            try {
                val item = current.find { it.id == courseId } ?: return@launch
                val newIsSelected = !item.isSelected
                
                // Сохраняем или удаляем из БД
                if (newIsSelected) {
                    selectedCourseRepository.insertSelectedCourse(courseId)
                } else {
                    selectedCourseRepository.deleteSelectedCourse(courseId)
                }
                
                // Обновляем состояние в UI
                _courses.value = current.map { courseItem ->
                    if (courseItem.id == courseId) {
                        courseItem.copy(isSelected = newIsSelected)
                    } else {
                        courseItem
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


