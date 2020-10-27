package com.surendra.todo_app_kotlin.fragment.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.surendra.todo_app_kotlin.MainActivity
import com.surendra.todo_app_kotlin.R
import com.surendra.todo_app_kotlin.data.models.ToDoData
import com.surendra.todo_app_kotlin.data.viewmodel.SharedViewModel
import com.surendra.todo_app_kotlin.data.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {


    private lateinit var viewModel: TodoViewModel
    private lateinit var listAdapter: ListAdapter
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set the menu
        setHasOptionsMenu(true)
        viewModel = (activity as MainActivity).viewModel
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setupRecyclerView()
        viewModel.getAllTasks.observe(viewLifecycleOwner, Observer {
            sharedViewModel.checkIfEmpty(it)
            listAdapter.differ.submitList(it)
        })

        sharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabase(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all ->deleteAllTaskFromDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTaskFromDb(){
        AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes"){_,_ ->
                viewModel.deleteAllTask()
                Toast.makeText(requireContext(),"Deleted all task",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_, _ ->}
            .setTitle("Delete All Tasks")
            .setMessage("Are You sure You want to delete all tasks")
            .create()
            .show()
    }

    private fun setupRecyclerView() {
        listAdapter= ListAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showEmptyDatabase(emptyDatabase:Boolean){
       if (emptyDatabase){
           iv_no_data.visibility=View.VISIBLE
           tv_no_data.visibility=View.VISIBLE
       }
        else{
           iv_no_data.visibility=View.INVISIBLE
           tv_no_data.visibility=View.INVISIBLE
       }
    }


}