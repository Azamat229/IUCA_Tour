package com.GDSC_IUCA.iuca_tour.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
     val myResponse: MutableLiveData<Response<List<PlacesItem>>> = MutableLiveData()

    fun getPlace(){
        viewModelScope.launch {
            val response = repository.getPlaces()
            myResponse.value = response
        }
    }




}