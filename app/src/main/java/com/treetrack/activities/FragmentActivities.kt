package com.treetrack.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.treetrack.R
import com.treetrack.databinding.FragmentActivitiesBinding
import com.treetrack.profile.GlobalUserViewModel

class FragmentActivities : Fragment() {

    private lateinit var binding: FragmentActivitiesBinding
    private val globalUserViewModel: GlobalUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activities, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTokenFromSharedPreferences()?.let { globalUserViewModel.getProfile(it) }
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getString("token", "")
    }

}