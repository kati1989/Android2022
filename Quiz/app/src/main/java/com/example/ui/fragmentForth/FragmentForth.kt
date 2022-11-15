package com.example.ui.fragmentForth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentForthBinding


class FragmentForth : Fragment() {

    private lateinit var binding: FragmentForthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forth, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showHighestScoreOfThePlayer()
    }

    private fun showHighestScoreOfThePlayer() {
        val sharedPreference = activity?.getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
        val playerName = sharedPreference?.getString("name", "")
        val highScoreOfThePlayer = sharedPreference?.getInt("highScoreOfThePlayer", 0)

        binding.tvPlayerScore.text =
            getString(R.string.player_score, highScoreOfThePlayer.toString())
        binding.tvPlayerName.text = playerName
    }
}