package com.GDSC_IUCA.iuca_tour.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GDSC_IUCA.iuca_tour.models.Preset
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MapViewModel(private val repository: Repository): ViewModel() {
     val myResponse: MutableLiveData<Response<PresetItem>> = MutableLiveData()

    fun getPreset(){
            viewModelScope.launch {
                val response = repository.getPresent()
                myResponse.value = response
            }
    }



}