package com.example.ui.fragmentEndQuiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentEndQuizBinding
import com.example.ui.fragmentQuizStarted.FragmentQuizStartedViewModel


class FragmentEndQuiz : Fragment() {
    private lateinit var binding: FragmentEndQuizBinding
    private val viewModel: FragmentQuizStartedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_end_quiz, container, false)
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
        saveScoreInSharedPrefsIfMax()
        binding.bGoHome.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentEndQuiz_to_fragmentSecond)
            activity?.viewModelStore?.clear()
        }
    }

    private fun saveScoreInSharedPrefsIfMax() {
        val sharedPreference = activity?.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        val highScoreOfThePlayer = sharedPreference?.getInt("highScoreOfThePlayer", 0)
        val totalScoreEndGame = viewModel.totalScore.value ?: 0
        if (totalScoreEndGame > highScoreOfThePlayer!!) {
            val editor = sharedPreference.edit()
            editor?.apply {
                putInt("highScoreOfThePlayer", totalScoreEndGame)
                apply() // async
            }
        }
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