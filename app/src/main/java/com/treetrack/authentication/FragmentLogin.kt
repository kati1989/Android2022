package com.treetrack.authentication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
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

        verifyLogin()
    }

    private fun verifyLogin() {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val email = sharedPreferences?.getString("email", null)
        val password = sharedPreferences?.getString("password", null)
        if (email != null && password != null) {
            viewModel.verifyLogin()
            viewModel.loginResultVerifyLogin.observe(viewLifecycleOwner) {
                saveTokenInSharedPrefs(it.token)
                saveUsernameAndPassword(viewModel.email.value, viewModel.password.value)
                findNavController().navigate(R.id.action_fragmentLogin_to_activityHome)
            }
        } else {
            normalLogin()
        }
    }

    private fun normalLogin() {
        viewModel.loginResult.observe(viewLifecycleOwner) {
            saveTokenInSharedPrefs(it.token)
            saveUsernameAndPassword(viewModel.email.value, viewModel.password.value)
            findNavController().navigate(R.id.action_fragmentLogin_to_activityHome)

        }
    }

    private fun saveUsernameAndPassword(email: String?, password: String?) {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    private fun saveTokenInSharedPrefs(token: String) {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("token", token)
            apply()
        }
    }
}