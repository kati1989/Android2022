package com.treetrack.tasks

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.findNavController
import com.treetrack.R
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.tasks.TasksResponseItem
import com.treetrack.databinding.FragmentMyTasksBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentMyTasks : Fragment() {

    private lateinit var binding: FragmentMyTasksBinding
    private val viewModel: FragmentMyTasksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_tasks, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFloatingActionButton()
        setupRecyclerView()
        getTokenFromSharedPreferences()?.let { getAllTasks(it) }
    }

    private fun setupFloatingActionButton() {
        binding.fabCreateTask.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMyTasks_to_fragmentAddTask)
        }
    }

    private fun setupRecyclerView() {
        binding.rvTasks.adapter = viewModel.tasksAdapter
    }

    private fun getAllTasks(token: String) {
        this.lifecycleScope.launch {
            val response = try {
                ApiRepository.getTasks(token = token)
            } catch (e: IOException) {
                Log.e("FragmentMyTasks", "IOException, no internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("FragmentMyTasks", "HttpException, unexpected response")
                return@launch
            }
            val body = response.body()
            if (response.isSuccessful && body != null) {
                addTasksInRecView(body)
            } else {
                Log.e("FragmentMyTasks", "Response not successful")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTasksInRecView(body: ArrayList<TasksResponseItem>) {
        viewModel.tasks.clear()
        viewModel.tasks.addAll(body)
        viewModel.tasksAdapter.notifyDataSetChanged()
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getString("token", "")
    }

}