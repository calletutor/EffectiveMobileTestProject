package com.example.effectivemobiletestproject

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class CourseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val titleView = findViewById<TextView>(R.id.tvCourseTitle)
        val descView = findViewById<TextView>(R.id.tvCourseDescription)
        val priceView = findViewById<TextView>(R.id.tvCoursePrice)
        val rateView = findViewById<TextView>(R.id.tvCourseRate)
        val dateView = findViewById<TextView>(R.id.tvCourseStartDate)

        titleView.text = intent.getStringExtra(EXTRA_TITLE).orEmpty()
        descView.text = intent.getStringExtra(EXTRA_DESCRIPTION).orEmpty()
        priceView.text = intent.getStringExtra(EXTRA_PRICE).orEmpty()
        rateView.text = intent.getStringExtra(EXTRA_RATE).orEmpty()
        dateView.text = intent.getStringExtra(EXTRA_START_DATE).orEmpty()
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_RATE = "extra_rate"
        const val EXTRA_START_DATE = "extra_start_date"
    }
}




