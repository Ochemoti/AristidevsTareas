package com.example.aristidevstareas

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask:TextView=view.findViewById(R.id.tvTask)
    private val cvTask:CheckBox=view.findViewById(R.id.cbTask)


    fun render (task:Task){

        if(task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        cvTask.isChecked = task.isSelected
        tvTask.text=task.name

       val color= when(task.category){
            TaskCategory.Bussines -> R.color.todo_ussines_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }
        cvTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cvTask.context,color)
        )




    }
}