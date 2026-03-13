package com.example.effectivemobiletestproject.data.remote.dto

/**
 * DTO‑модели под JSON с курсами из API:
 * https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download
 */

data class CoursesResponse(
    val courses: List<CourseDto>
)

data class CourseDto(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)


