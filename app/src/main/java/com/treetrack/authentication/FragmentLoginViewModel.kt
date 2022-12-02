package com.treetrack.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentLoginViewModel : ViewModel() {

    val email = MutableLiveData("kovacs.katalin@student.ms.sapientia.ro")
    val password = MutableLiveData("3tracker_student16")

    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()

    fun login() {
        val em = email.value?.trim() ?: ""
        val pw = password.value?.trim() ?: ""

        if (em != "" && pw != "") {
            viewModelScope.launch {
                val response = try {
                    ApiRepository.login(LoginRequest(email = em, passwordKey = pw))
                } catch (e: IOException) {
                    Log.e("FragmentFirstViewModel", "IOException, no internet")
                    return@launch
                } catch (e: HttpException) {
                    Log.e("FragmentFirstViewModel", "HttpException, unexpected response")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    loginResult.value = response.body()
                } else {
                    Log.e("FragmentFirstViewModel", "Response not successful")
                }
            }
        }
    }
}