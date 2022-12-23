package com.treetrack.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.treetrack.R
import com.treetrack.databinding.FragmentSplashBinding

class FragmentSplash : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: FragmentSplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.goToLogin.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_fragmentSplash_to_fragmentLogin)
            }
        }
    }
}