package com.example.effectivemobiletestproject.feature.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobiletestproject.core.data.repository.SelectedCourseRepository
import kotlinx.coroutines.launch
import com.google.android.material.card.MaterialCardView
import android.graphics.Color

class CourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments ?: return

        val courseId = args.getInt("courseId", 0)

        val tvTitle = view.findViewById<TextView>(R.id.tvCourseTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvCourseDescription)
        val tvPrice = view.findViewById<TextView>(R.id.tvCoursePrice)
        val tvRate = view.findViewById<TextView>(R.id.tvCourseRate)
        val tvStartDate = view.findViewById<TextView>(R.id.tvCourseStartDate)

        tvTitle.text = args.getString("title").orEmpty()
        tvDescription.text = args.getString("description").orEmpty()
        tvPrice.text = args.getString("price").orEmpty()
        tvRate.text = args.getString("rate").orEmpty()
        tvStartDate.text = args.getString("startDate").orEmpty()

        val btnBack = view.findViewById<ImageButton>(R.id.btnBack)
        val cardBookmark = view.findViewById<MaterialCardView>(R.id.cardBookmarkDetail)
        val ivBookmark = view.findViewById<ImageView>(R.id.ivBookmarkDetail)

        val selectedCourseRepository = SelectedCourseRepository(requireContext())
        var isSelected = false

        fun applyBookmarkState(selected: Boolean) {
            if (selected) {
                cardBookmark.setCardBackgroundColor(Color.parseColor("#FFFFC107"))
                ivBookmark.setColorFilter(Color.BLACK)
            } else {
                cardBookmark.setCardBackgroundColor(Color.parseColor("#80000000"))
                ivBookmark.setColorFilter(Color.WHITE)
            }
        }

        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Начальная установка состояния закладки из БД
        viewLifecycleOwner.lifecycleScope.launch {
            isSelected = selectedCourseRepository.isCourseSelected(courseId)
            applyBookmarkState(isSelected)
        }

        // Переключение закладки и синхронизация с БД
        cardBookmark.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                if (isSelected) {
                    selectedCourseRepository.deleteSelectedCourse(courseId)
                    isSelected = false
                } else {
                    selectedCourseRepository.insertSelectedCourse(courseId)
                    isSelected = true
                }
                applyBookmarkState(isSelected)
            }
        }
    }
}
