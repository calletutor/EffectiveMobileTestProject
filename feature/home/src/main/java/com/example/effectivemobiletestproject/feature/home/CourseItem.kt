package com.example.effectivemobiletestproject.feature.home

import com.example.effectivemobiletestproject.core.network.dto.CourseDto

/**
 * UI-модель курса с дополнительным полем isSelected для управления закладкой
 */
data class CourseItem(
    val course: CourseDto,
    val isSelected: Boolean = false
) {
    val id: Int get() = course.id
    val title: String get() = course.title
    val text: String get() = course.text
    val price: String get() = course.price
    val rate: String get() = course.rate
    val startDate: String get() = course.startDate
    val publishDate: String get() = course.publishDate
}



