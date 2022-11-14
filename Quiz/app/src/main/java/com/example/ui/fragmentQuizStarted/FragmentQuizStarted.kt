package com.example.ui.fragmentQuizStarted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.data.Question
import com.example.data.QuestionsRepository
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizStartedBinding

class FragmentQuizStarted : Fragment() {
    private lateinit var binding: FragmentQuizStartedBinding
    private val viewModel: FragmentQuizStartedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_started, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        viewModel.firstQuestionInList.value?.let { createRadioGroupAndPopulateIt(it) }//first question
        binding.bNext.setOnClickListener {
            setGameRules()
        }
    }

    private fun createRadioGroupAndPopulateIt(question: Question) {
        val radioGroup = RadioGroup(this.context)
        viewModel.questionTitle.value = question.text

        val layout = binding.llStartQuiz
        radioGroup.setOnCheckedChangeListener { _, i ->
            viewModel.idRbChecked.value = i
        }

        for (a in question.answers) {
            val rb = RadioButton(this.context)
            rb.text = a
            rb.id = question.answers.indexOf(a)
            radioGroup.addView(rb)

            if (question.answers.indexOf(a) == 0) {
                rb.isChecked = true
            }
        }
        layout.addView(radioGroup)
    }

    private fun clearRadioGroup() {
        viewModel.idRbChecked.value = 0
        binding.llStartQuiz.removeViews(1, binding.llStartQuiz.childCount - 1)
    }

    private fun goToEndQuizFragment() {
        binding.root.findNavController()
            .navigate(R.id.action_fragmentQuizStarted_to_fragmentEndQuiz)
    }

    private fun setGameRules() {
        if (viewModel.firstQuestionInList.value?.correct == viewModel.idRbChecked.value) {
            viewModel.totalScore.value = viewModel.totalScore.value?.plus(1)
        }
        if (viewModel.fiveRandomQuestions.value?.isEmpty() == true) {
            goToEndQuizFragment()
        }
        clearRadioGroup()
        viewModel.removeFirstQuestionFromListAndChangeFirstQuestion()
        viewModel.firstQuestionInList.value?.let { it1 -> createRadioGroupAndPopulateIt(it1) }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}