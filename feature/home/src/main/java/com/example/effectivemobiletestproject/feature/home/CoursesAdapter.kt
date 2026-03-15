package com.example.effectivemobiletestproject.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestproject.core.network.dto.CourseDto

class CoursesAdapter(
    private var items: List<CourseDto> = emptyList(),
    private val onCourseClick: (title: String, description: String, price: String, rate: String, startDate: String) -> Unit = { _, _, _, _, _ -> }
) : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    fun submitList(newItems: List<CourseDto>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivCourseImage: ImageView = itemView.findViewById(R.id.ivCourseImage)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvRate: TextView = itemView.findViewById(R.id.tvRate)
        private val tvRateOverlay: TextView = itemView.findViewById(R.id.tvRateOverlay)
        private val tvDateOverlay: TextView = itemView.findViewById(R.id.tvDateOverlay)

        fun bind(item: CourseDto) {
            tvTitle.text = item.title
            tvDescription.text = item.text
            tvPrice.text = item.price
            tvRate.text = item.rate
            tvRateOverlay.text = item.rate
            tvDateOverlay.text = item.startDate

            itemView.setOnClickListener {
                onCourseClick(item.title, item.text, item.price, item.rate, item.startDate)
            }
        }
    }
}


