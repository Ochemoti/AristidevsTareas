package com.example.aristidevstareas

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewholder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)


    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {
        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when (taskCategory) {
            TaskCategory.Bussines -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_ussines_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otross"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
            else -> {}
        }


    }
}