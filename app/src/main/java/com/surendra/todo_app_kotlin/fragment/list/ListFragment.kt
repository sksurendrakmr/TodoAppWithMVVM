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
import com.surendra.todo_app_kotlin.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {


    private lateinit var viewModel: TodoViewModel
    private lateinit var listAdapter: ListAdapter
    private val sharedViewModel: SharedViewModel by viewModels()

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set the menu
        setHasOptionsMenu(true)
        viewModel = (activity as MainActivity).viewModel
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater,container,false)
//        return inflater.inflate(R.layout.fragment_list, container, false)
        binding.sharedViewModel = sharedViewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getAllTasks.observe(viewLifecycleOwner, Observer {
            sharedViewModel.checkIfEmpty(it)
            listAdapter.differ.submitList(it)
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
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null //Whenever our fragment is destroyed, we need to set our binding to null to avoid memory leak
    }

}