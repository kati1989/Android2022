package com.treetrack.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.treetrack.R
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.profile.UpdateUserRequest
import com.treetrack.databinding.FragmentUpdateProfileBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentUpdateProfile : Fragment() {

    private lateinit var binding: FragmentUpdateProfileBinding
    private val globalUserViewModel: GlobalUserViewModel by activityViewModels()
    private val viewModel: FragmentUpdateProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bUpdateProfile.setOnClickListener {
            getTokenFromSharedPreferences()?.let { updateProfile(it) }
        }
    }

    private fun updateProfile(token: String) {
        val lastName = viewModel.lastName.value?.trim() ?: ""
        val firstName = viewModel.firstName.value?.trim() ?: ""
        val location = viewModel.location.value?.trim() ?: ""
        val imageUrl = viewModel.imageUrl.value?.trim() ?: ""
        val phoneNumber = viewModel.phoneNumber.value?.trim() ?: ""

        if (lastName != "" && firstName != "" && location != "" && imageUrl != "") {
            val uur = UpdateUserRequest(
                firstName = firstName,
                lastName = lastName,
                location = location,
                imageUrl = imageUrl,
                phoneNumber = phoneNumber
            )
            this.lifecycleScope.launch {
                val response = try {
                    ApiRepository.updateUserData(
                        token,
                        uur
                    )
                } catch (e: IOException) {
                    Log.e("FragmentUpdateProfile", "IOException, no internet")
                    return@launch
                } catch (e: HttpException) {
                    Log.e("FragmentUpdateProfile", "HttpException, unexpected response")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(activity, "Profile updated successfully!", Toast.LENGTH_SHORT)
                        .show()
                    globalUserViewModel.updateGlobalUser(uur)
                } else {
                    Log.e("FragmentUpdateProfile", "Response not successful")
                }
            }
        }
    }

    private fun getTokenFromSharedPreferences(): String? {
        val sharedPreferences = activity?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getString("token", "")
    }
}

