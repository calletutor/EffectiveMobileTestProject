package com.example.effectivemobiletestproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestproject.data.remote.dto.CourseDto

class CoursesAdapter(
    private var items: List<CourseDto> = emptyList()
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

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvRate: TextView = itemView.findViewById(R.id.tvRate)

        fun bind(item: CourseDto) {
            tvTitle.text = item.title
            tvDescription.text = item.text
            tvPrice.text = item.price
            tvRate.text = item.rate

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, CourseActivity::class.java).apply {
                    putExtra(CourseActivity.EXTRA_TITLE, item.title)
                    putExtra(CourseActivity.EXTRA_DESCRIPTION, item.text)
                    putExtra(CourseActivity.EXTRA_PRICE, item.price)
                    putExtra(CourseActivity.EXTRA_RATE, item.rate)
                    putExtra(CourseActivity.EXTRA_START_DATE, item.startDate)
                }
                context.startActivity(intent)
            }
        }
    }
}


