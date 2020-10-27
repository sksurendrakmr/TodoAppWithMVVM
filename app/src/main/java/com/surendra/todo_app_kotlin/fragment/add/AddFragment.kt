package com.surendra.todo_app_kotlin.fragment.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.surendra.todo_app_kotlin.MainActivity
import com.surendra.todo_app_kotlin.R
import com.surendra.todo_app_kotlin.data.models.Priority
import com.surendra.todo_app_kotlin.data.models.ToDoData
import com.surendra.todo_app_kotlin.data.viewmodel.SharedViewModel
import com.surendra.todo_app_kotlin.data.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {


    private lateinit var  todoViewModel:TodoViewModel
    private val sharedViewModel:SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        todoViewModel = (activity as MainActivity).viewModel
        val view=  inflater.inflate(R.layout.fragment_add, container, false)
            view.priorities_spinner.onItemSelectedListener = sharedViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_list_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.add_menu){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = etTitle.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = etDescription.text.toString()

        val validation=sharedViewModel.verifyDataFromUser(mTitle,mDescription)
        if (validation){
            val newTask=ToDoData(mTitle,sharedViewModel.parsePriority(mPriority),mDescription)
            todoViewModel.insert(newTask)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(),"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }




}