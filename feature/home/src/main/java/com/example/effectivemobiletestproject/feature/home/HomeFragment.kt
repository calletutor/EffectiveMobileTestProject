package com.example.effectivemobiletestproject.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var navigateToCourseListener: OnNavigateToCourseListener? = null
    private lateinit var adapter: CoursesAdapter
    private val viewModel: HomeViewModel by viewModel()

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

        adapter = CoursesAdapter(
            onCourseClick = { courseId, title, description, price, rate, startDate ->
                navigateToCourseListener?.onCourseSelected(courseId, title, description, price, rate, startDate)
            },
            onBookmarkToggle = { courseId, _ ->
                viewModel.toggleBookmark(courseId)
            }
        )
        recyclerView.adapter = adapter

        view.findViewById<ImageView>(R.id.ivSortDirection).setOnClickListener {
            viewModel.sortByPublishDateDesc()
        }

        // Подписываемся на данные из ViewModel
        viewModel.courses.observe(viewLifecycleOwner, Observer { items ->
            adapter.submitList(items)
        })

        // Стартовая загрузка данных
        viewModel.loadCourses()
    }

    interface OnNavigateToCourseListener {
        fun onCourseSelected(
            courseId: Int,
            title: String,
            description: String,
            price: String,
            rate: String,
            startDate: String
        )
    }
}
