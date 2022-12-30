package com.treetrack.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.treetrack.R
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.activities.ActivitiesResponseItem
import com.treetrack.databinding.FragmentActivitiesBinding
import com.treetrack.profile.GlobalUserViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentActivities : Fragment() {

    private lateinit var binding: FragmentActivitiesBinding
    private val globalUserViewModel: GlobalUserViewModel by activityViewModels()
    private val viewModel: FragmentActivitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activities, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTokenFromSharedPreferences()?.let { globalUserViewModel.getProfile(it) }

        setupRecyclerView()
        getTokenFromSharedPreferences()?.let { getAllActivities(it) }
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getString("token", "")
    }


    private fun setupRecyclerView() {
        binding.rvActivities.adapter = viewModel.activitiesAdapter
    }

    private fun getAllActivities(token: String) {
        this.lifecycleScope.launch {
            val response = try {
                ApiRepository.getActivities(token = token)
            } catch (e: IOException) {
                Log.e("FragmentActivities", "IOException, no internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("FragmentActivities", "HttpException, unexpected response")
                return@launch
            }
            val body = response.body()
            if (response.isSuccessful && body != null) {
                addActivitiesInRecView(body)
            } else {
                Log.e("FragmentActivities", "Response not successful")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addActivitiesInRecView(body: ArrayList<ActivitiesResponseItem>) {
        viewModel.activities.clear()
        viewModel.activities.addAll(body)
        viewModel.activitiesAdapter.notifyDataSetChanged()
    }

}