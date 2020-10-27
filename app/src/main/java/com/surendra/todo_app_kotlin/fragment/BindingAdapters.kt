package com.surendra.todo_app_kotlin.fragment

import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.surendra.todo_app_kotlin.R

//This class will contain all the custom binding adapters for our project.
class BindingAdapters {
    companion object {

        @BindingAdapter("android.navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view:FloatingActionButton, navigate:Boolean){
            view.setOnClickListener{
                if (navigate){
                    view.findNavController().navigate(R.id.listFragment)
                }
            }
        }
    }
}