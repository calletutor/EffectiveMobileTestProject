package com.example.effectivemobiletestproject.feature.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CoursesAdapter(
    private var items: MutableList<CourseItem> = mutableListOf(),
    private val onCourseClick: (courseId: Int, title: String, description: String, price: String, rate: String, startDate: String) -> Unit = { _, _, _, _, _, _ -> },
    private val onBookmarkToggle: (courseId: Int, isSelected: Boolean) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    fun submitList(newItems: List<CourseItem>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItemSelection(courseId: Int, isSelected: Boolean) {
        val index = items.indexOfFirst { it.id == courseId }
        if (index != -1) {
            val item = items[index]
            items[index] = item.copy(isSelected = isSelected)
            notifyItemChanged(index)
        }
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
        private val cardBookmark: CardView = itemView.findViewById(R.id.cardBookmark)
        private val ivBookmark: ImageView = itemView.findViewById(R.id.ivBookmark)

        fun bind(item: CourseItem) {
            tvTitle.text = item.title
            tvDescription.text = item.text
            tvPrice.text = item.price
            tvRate.text = item.rate
            tvRateOverlay.text = item.rate
            tvDateOverlay.text = item.startDate

            applyBookmarkState(item.isSelected)

            itemView.setOnClickListener {
                onCourseClick(item.id, item.title, item.text, item.price, item.rate, item.startDate)
            }

            val toggleListener = View.OnClickListener {
                val position = adapterPosition
                if (position == RecyclerView.NO_POSITION) return@OnClickListener

                val current = items[position]
                onBookmarkToggle(current.id, !current.isSelected)
            }

            cardBookmark.setOnClickListener(toggleListener)
            ivBookmark.setOnClickListener(toggleListener)
        }

        private fun applyBookmarkState(isSelected: Boolean) {
            if (isSelected) {
                cardBookmark.setCardBackgroundColor(Color.parseColor("#FFFFC107")) // жёлтый
                ivBookmark.setColorFilter(Color.BLACK)
            } else {
                cardBookmark.setCardBackgroundColor(Color.parseColor("#80000000")) // полупрозрачный чёрный
                ivBookmark.setColorFilter(Color.WHITE)
            }
        }
    }
}
