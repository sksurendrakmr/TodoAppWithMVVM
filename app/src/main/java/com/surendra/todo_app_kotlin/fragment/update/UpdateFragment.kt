package com.surendra.todo_app_kotlin.fragment.update

import android.os.Bundle
import android.os.SharedMemory
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.surendra.todo_app_kotlin.MainActivity
import com.surendra.todo_app_kotlin.R
import com.surendra.todo_app_kotlin.data.models.Priority
import com.surendra.todo_app_kotlin.data.models.ToDoData
import com.surendra.todo_app_kotlin.data.viewmodel.SharedViewModel
import com.surendra.todo_app_kotlin.data.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_update.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: TodoViewModel
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel=(activity as MainActivity).viewModel

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_current_Title.setText(args.currentItem.title)
        et_current_Description.setText(args.currentItem.description)
        current_priorities_spinner.setSelection(sharedViewModel.parsePriorityToInt(args.currentItem.priority))
        current_priorities_spinner.onItemSelectedListener= sharedViewModel.listener

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.menu_save -> updateToDb()
           R.id.menu_delete -> confirmItemRemoval()
       }
        return super.onOptionsItemSelected(item)
    }

  private fun confirmItemRemoval(){
      val alert = AlertDialog.Builder(requireContext())
          .setPositiveButton("Yes"){_,_->
              viewModel.deleteATask(args.currentItem)
              Toast.makeText(requireContext(),"Successfully deleted '${args.currentItem.title}'",Toast.LENGTH_SHORT).show()
              findNavController().navigate(R.id.action_updateFragment_to_listFragment)
          }
          .setNegativeButton("No"){_,_ ->}
          .setMessage("Are You sure you want to remove '${args.currentItem.title}'")
          .setTitle("Delete ${args.currentItem.title}?")
          .create()
      alert.show()
  }

    private fun updateToDb(){
        val mTitle = et_current_Title.text.toString()
        val mdescription = et_current_Description.text.toString()
        val mPriority = current_priorities_spinner.selectedItem.toString()


        val validation = sharedViewModel.verifyDataFromUser(mTitle,mdescription)
        if (validation ){
            val updatedTask = ToDoData(mTitle,sharedViewModel.parsePriority(mPriority),mdescription)
            updatedTask.id = args.currentItem.id


            viewModel.update(updatedTask)

            Toast.makeText(requireContext(),"Successfully Updated",Toast.LENGTH_SHORT).show()

            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()
            findNavController().navigate(action)

        }
        else{
            Toast.makeText(requireContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show()
        }

    }



}