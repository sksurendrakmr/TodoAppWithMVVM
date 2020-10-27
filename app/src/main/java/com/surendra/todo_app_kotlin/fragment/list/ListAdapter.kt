package com.surendra.todo_app_kotlin.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.surendra.todo_app_kotlin.R
import com.surendra.todo_app_kotlin.data.models.Priority
import com.surendra.todo_app_kotlin.data.models.ToDoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ToDoData>() {
        override fun areItemsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoData = differ.currentList[position]
        holder.itemView.apply {
            title_text.text = todoData.title
            description_text.text = todoData.description
            row_background.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(todoData)
                findNavController().navigate(action)
            }

            when (todoData.priority) {
                Priority.HIGH -> priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this.context,
                        R.color.red
                    )
                )
                Priority.MEDIUM -> priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this.context,
                        R.color.yellow
                    )
                )
                Priority.LOW -> priority_indicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        this.context,
                        R.color.green
                    )
                )
            }


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun display(){

    }
}