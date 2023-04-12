package com.example.aristidevstareas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val categories: List<TaskCategory>,private val onItemSelected:(Int)->Unit) :
    RecyclerView.Adapter<CategoriesViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category,parent,false)
        return CategoriesViewholder(view)
    }

    override fun getItemCount() = categories.size


    override fun onBindViewHolder(holder: CategoriesViewholder, position: Int) {
        holder.render(categories[position],onItemSelected)
    }
}