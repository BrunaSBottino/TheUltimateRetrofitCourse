package com.example.theultimateretrofitcourse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.theultimateretrofitcourse.model.Todo
import com.example.theultimateretrofitcourse.databinding.ItemTodosBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodosBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>(){
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<Todo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }
    }

    override fun getItemCount() = todos.size

}