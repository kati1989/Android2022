package com.example.ui.fragmentThird

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentThirdBinding
import com.example.ui.fragmentQuizStarted.FragmentQuizStartedViewModel

class FragmentThird : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private val viewModel: FragmentQuizStartedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetStarted.setOnClickListener {
            goToFragmentStartQuiz()
        }
    }

    private fun goToFragmentStartQuiz() {
        findNavController().navigate(R.id.action_fragmentThird_to_fragmentQuizStarted)
    }

}