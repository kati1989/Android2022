package com.treetrack.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.profile.UserResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GlobalUserViewModel : ViewModel() {
    val user = MutableLiveData<User>()

    private fun setGlobalUser(userResponse: UserResponse) {
        user.value = UserResponse.getUserFromUserResponse(userResponse)
    }

    fun getProfile(token: String) {
        viewModelScope.launch {
            val response = try {
                ApiRepository.user(token)
            } catch (e: IOException) {
                Log.e("GlobalUserViewModel", "IOException, no internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("GlobalUserViewModel", "HttpException, unexpected response")
                return@launch
            }
            val body = response.body()
            if (response.isSuccessful && body != null) {
                setGlobalUser(body)
            } else {
                Log.e("GlobalUserViewModel", "Response not successful")
            }
        }
    }
}