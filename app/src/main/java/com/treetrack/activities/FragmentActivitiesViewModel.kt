package com.treetrack.activities

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treetrack.api.ApiRepository
import com.treetrack.api.data.activities.ActivitiesResponse
import com.treetrack.api.data.activities.ActivitiesResponseItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentActivitiesViewModel : ViewModel() {
    val activities = ArrayList<ActivitiesResponseItem>()
    val exampleAdapter = ExampleAdapter(activities)

    val getActivitiesResponse = MutableLiveData<ActivitiesResponse?>()

    private fun loadActivities(token: String) {
        viewModelScope.launch {
            val response = try {
                ApiRepository.getActivities(token)
            } catch (e: IOException) {
                Log.e("FragmentActivitiesView", "IOException, no internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("FragmentActivitiesView", "HttpException, unexpected response")
                return@launch
            }
            val body = response.body()
            if (response.isSuccessful && body != null) {
                getActivitiesResponse.value = body
            } else {
                Log.e("FragmentActivitiesView", "Response not successful")
            }
        }
    }
}