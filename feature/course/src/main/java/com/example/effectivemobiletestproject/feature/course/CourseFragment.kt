package com.example.effectivemobiletestproject.feature.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        view.findViewById<TextView>(R.id.tvCourseTitle).text = args.getString("title").orEmpty()
        view.findViewById<TextView>(R.id.tvCourseDescription).text = args.getString("description").orEmpty()
        view.findViewById<TextView>(R.id.tvCoursePrice).text = args.getString("price").orEmpty()
        view.findViewById<TextView>(R.id.tvCourseRate).text = args.getString("rate").orEmpty()
        view.findViewById<TextView>(R.id.tvCourseStartDate).text = args.getString("startDate").orEmpty()
    }
}
