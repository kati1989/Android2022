package com.example.ui.fragmentFirst

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.Question
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentFirstBinding


class FragmentFirst : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FragmentFirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            saveNameInSharedPrefs()
        }
    }

    private fun saveNameInSharedPrefs() {
        val sharedPreference = activity?.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreference?.edit()
        editor?.apply {
            putString("name", viewModel.name.value)
            apply() // async
            navigateToActivitySecond()
        }
    }

    private fun navigateToActivitySecond() {
        activity?.finish()
        findNavController().navigate(R.id.action_fragmentFirst_to_activitySecond)
    }
}