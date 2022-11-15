package com.example.ui.fragmentSecond

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentSecondBinding

class FragmentSecond : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bReadQuestions.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSecond_to_fragmentQuestionList)
        }

        binding.bTestYourSkills.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSecond_to_fragmentQuizStarted)
        }
    }

}