package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class RecyclerViewAdapter(private val todos: ArrayList<ToDoItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val todo = todos[position]
        holder.binding.apply {
            tvTodo.text = todo.todo
            cbItem.isChecked = todo.checked
            cbItem.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked== true){
                    tvTodo.setTextColor(Color.parseColor("#808080"))
                } else {
                    tvTodo.setTextColor(Color.parseColor(" #000000"))
                }
                todo.checked = !todo.checked
            }
        }
    }

    override fun getItemCount() = todos.size
}

