package com.example.effectivemobiletestproject.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestproject.core.network.ApiClient
import com.example.effectivemobiletestproject.core.network.dto.CourseDto
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var navigateToCourseListener: OnNavigateToCourseListener? = null
    private var courses: List<CourseDto> = emptyList()

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        navigateToCourseListener = context as? OnNavigateToCourseListener
    }

    override fun onDetach() {
        super.onDetach()
        navigateToCourseListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCourses)
        val adapter = CoursesAdapter(
            onCourseClick = { title, description, price, rate, startDate ->
                navigateToCourseListener?.onCourseSelected(title, description, price, rate, startDate)
            }
        )
        recyclerView.adapter = adapter

        view.findViewById<ImageView>(R.id.ivSortDirection).setOnClickListener {
            if (courses.isNotEmpty()) {
                val sorted = courses.sortedByDescending { it.publishDate }
                adapter.submitList(sorted)
            }
        }

        lifecycleScope.launch {
            try {
                val loadedCourses = ApiClient.coursesRepository.getCourses()
                courses = loadedCourses
                adapter.submitList(loadedCourses)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    interface OnNavigateToCourseListener {
        fun onCourseSelected(
            title: String,
            description: String,
            price: String,
            rate: String,
            startDate: String
        )
    }
}
