package com.treetrack.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.treetrack.R
import com.treetrack.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: FragmentLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginResult.observe(viewLifecycleOwner) {
            saveTokenInSharedPrefs(it.token)
            findNavController().navigate(R.id.action_fragmentLogin_to_activityHome)
        }
    }

    private fun saveTokenInSharedPrefs(token: String) {
        val sharedaPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedaPreferences?.edit()
        editor?.apply {
            putString("token", token)
            apply()
        }
    }



}