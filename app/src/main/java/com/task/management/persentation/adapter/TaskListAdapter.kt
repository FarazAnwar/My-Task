package com.task.management.persentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.management.R
import com.task.management.databinding.ListItemUserBinding
import com.task.management.domain.entities.TaskModel

class TaskListAdapter(private val userList : List<TaskModel>,
                      private val listener : OnPerformAction
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listener)
    }

    override fun getItemCount(): Int = userList.size

   inner class ViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener : OnPerformAction) {
            binding.tvUserName.text = userList[adapterPosition].taskName
            binding.tvUserNumber.text = userList[adapterPosition].description
            binding.tvUserEmail.text = userList[adapterPosition].taskProity

            binding.ivEdit.setOnClickListener { listener.onEdit(userList[adapterPosition]) }
            binding.ivDelete.setOnClickListener { listener.onDelete(userList[adapterPosition]) }
        }
    }

    interface OnPerformAction {
        fun onEdit(userDataModel: TaskModel)
        fun onDelete(userDataModel: TaskModel)
    }
}

