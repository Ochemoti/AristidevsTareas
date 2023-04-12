package com.example.aristidevstareas

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aristidevstareas.TaskCategory.*
import com.example.aristidevstareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        Bussines,
        Personal,
        Other
    )

    private val task = mutableListOf(
        Task("PruebaBussines", Bussines),
        Task("PruebaPersonal", Personal),
        Task("PruebaOther", Other)

    )

    lateinit var binding: ActivityMainBinding

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()


    }

    private fun initListeners() {
        binding.favAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategorias: RadioGroup = dialog.findViewById(R.id.rgCategorias)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategorias.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategorias.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.Bussines) -> Bussines
                    getString(R.string.Personal) -> Personal
                    else -> Other
                }
                task.add(Task(etTask.text.toString(), currentCategory))
                updateTask()
                dialog.hide()
            }

        }

        dialog.show()


    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { updateCategories(it) }
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoriesAdapter

        //onItemSelected(it) o position->onItemSelected(position)

        tasksAdapter = TasksAdapter(task) { onItemSelected(it) }
        //Por defecto si no pones orientacio  se pone vertical por defecto
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = tasksAdapter


    }

    private fun updateCategories(position: Int) {

        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()

    }

    private fun onItemSelected(position: Int) {
        task[position].isSelected = !task[position].isSelected
        updateTask()

    }

    private fun updateTask() {
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = task.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks

        tasksAdapter.notifyDataSetChanged()
    }


}