package com.treetrack.tasks

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.treetrack.R
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.tasks.TaskRequest
import com.treetrack.databinding.FragmentAddTaskBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentAddTask : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: FragmentAddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAddTaskButton()
    }

    private fun setupAddTaskButton() {
        binding.bAddTask.setOnClickListener {
            val token = getTokenFromSharedPreferences()
            if (token != null) {
                addTask(token)
            }
        }
    }

    private fun addTask(token: String) {
        val ti = viewModel.title.value?.trim() ?: ""
        val de = viewModel.description.value?.trim() ?: ""
        val pr = viewModel.priority.value?.trim() ?: ""
        val dedl = viewModel.deadline.value?.trim() ?: ""
        val depid = viewModel.departmentId.value?.trim() ?: ""
        val status = viewModel.status.value?.trim() ?: ""

        if (ti != "" && de != "" && pr != "" && dedl != "" && depid != "" && status != "") {
            val taskRequest = TaskRequest(
                title = ti,
                deadline = dedl.toLong(),
                departmentId = depid.toInt(),
                description = de,
                priority = pr.toInt(),
                status = status.toInt()
            )

            this.lifecycleScope.launch {
                val response = try {
                    ApiRepository.createTask(token = token, taskRequest = taskRequest)
                } catch (e: IOException) {
                    Log.e("FragmentAddTask", "IOException, no internet")
                    return@launch
                } catch (e: HttpException) {
                    Log.e("FragmentAddTask", "HttpException, unexpected response")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    showSuccessPrompt()
                } else {
                    Log.e("FragmentAddTask", "Response not successful")
                }
            }
        }
    }

    private fun showSuccessPrompt() {
        val builder = AlertDialog.Builder(this.context)
        builder.setMessage("Task added successfully!")
        builder.setPositiveButton("OK") { dialog, which ->
            resetFields()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getString("token", "")
    }

    private fun resetFields() {
        viewModel.status.value = ""
        viewModel.departmentId.value = ""
        viewModel.deadline.value = ""
        viewModel.title.value = ""
        viewModel.priority.value = ""
    }
}