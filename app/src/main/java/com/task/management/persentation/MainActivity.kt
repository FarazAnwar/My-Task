package com.task.management.persentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.task.management.R
import com.task.management.databinding.ActivityMainBinding
import com.task.management.domain.entities.TaskModel
import com.task.management.domain.entities.TaskViewModel
import com.task.management.persentation.adapter.TaskListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TaskViewModel by viewModels()
    private var userList: ArrayList<TaskModel> = arrayListOf()
    private var adapter: TaskListAdapter? = null
    private var existingModel: TaskModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observers()
        setClickListeners()
    }

    private fun observers() {

        viewModel.userList.observe(this) {
            if (it != null && it.isNotEmpty()) {
                userList = it as ArrayList<TaskModel>
                setTaskList(userList)
            } else {
                setTaskList(arrayListOf())
            }
        }
    }

    private fun setTaskList(list: ArrayList<TaskModel>) {

        adapter = TaskListAdapter(list, listener = object : TaskListAdapter.OnPerformAction {

            override fun onEdit(taskDataModel: TaskModel) {
                existingModel = taskDataModel
                binding.etName.setText(taskDataModel.taskName)
                binding.etNumber.setText(taskDataModel.description)
                binding.btnAdd.text = getString(R.string.update)
            }

            override fun onDelete(taskDataModel: TaskModel) {
                viewModel.deleteTask(taskDataModel)
//                showAlertDialog(applicationContext,"Delete Task","Are you sure you want to delete?",taskDataModel)
            }
        })
        binding.rvUserList.adapter = adapter
    }

    private fun setClickListeners() {
        binding.btnAdd.setOnClickListener {

            if (isValid()) {

                if (existingModel != null) {
                    viewModel.updateTaskDetail(TaskModel(existingModel!!.id, binding.etName.text.toString(),
                        binding.etNumber.text.toString(),
                        binding.etEmail.text.toString()))
                    existingModel = null
                } else {
                    viewModel.addTask(TaskModel(Date().time, binding.etName.text.toString(),
                        binding.etNumber.text.toString(),binding.etEmail.text.toString()))
                }

                binding.etName.setText("")
                binding.etNumber.setText("")
                binding.etEmail.setText("")
                binding.btnAdd.text = getString(R.string.add)
            }
        }

    }

    private fun isValid(): Boolean {
        return if (binding.etName.text.toString().trim().isEmpty()) {
            showToast("Please enter name")
            false
        } else if (binding.etNumber.text.toString().trim().isEmpty()) {
            showToast("Please enter number")
            false
        } else {
            true
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showAlertDialog(context: Context, title: String, message: String,taskDataModel: TaskModel) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            viewModel.deleteTask(taskDataModel)
        }

        builder.setPositiveButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

}

