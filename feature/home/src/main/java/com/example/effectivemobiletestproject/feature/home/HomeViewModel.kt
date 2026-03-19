package com.example.effectivemobiletestproject.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestproject.core.network.repository.CoursesRepository
import com.example.effectivemobiletestproject.core.data.repository.SelectedCourseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val coursesRepository: CoursesRepository,
    private val selectedCourseRepository: SelectedCourseRepository
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseItem>>(emptyList())
    val courses: LiveData<List<CourseItem>> = _courses

    fun loadCourses() {
        if (_courses.value?.isNotEmpty() == true) return

        viewModelScope.launch {
            try {
                val loadedCourses = coursesRepository.getCourses()
                
                val selectedCourseIds = selectedCourseRepository.getAllSelectedCourseIds().first().toSet()
                
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

    fun sortByPublishDateDesc() {
        val current = _courses.value ?: return
        _courses.value = current.sortedByDescending { it.publishDate }
    }

    fun toggleBookmark(courseId: Int) {
        val current = _courses.value ?: return
        
        viewModelScope.launch {
            try {
                val item = current.find { it.id == courseId } ?: return@launch
                val newIsSelected = !item.isSelected
                
                if (newIsSelected) {
                    selectedCourseRepository.insertSelectedCourse(courseId)
                } else {
                    selectedCourseRepository.deleteSelectedCourse(courseId)
                }
                
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
