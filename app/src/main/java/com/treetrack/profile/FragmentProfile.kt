package com.treetrack.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.treetrack.R
import com.treetrack.databinding.FragmentProfileBinding


class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val globalUserViewModel: GlobalUserViewModel by activityViewModels()
    private val viewModel: FragmentProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalUserViewModel.user.observe(viewLifecycleOwner) {
            viewModel.profileUser.value = it
            loadProfilePicture()
        }
        binding.fabUpdateProfile.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentProfile_to_fragmentUpdateProfile)
        }
    }

    private fun loadProfilePicture() {
        binding.ivProfile.load(globalUserViewModel.user.value?.image)
    }

    private fun deleteSharedPrefsValues() {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("email", "")
            putString("password", "")
            putString("token", "")
            apply()
        }
    }
}
