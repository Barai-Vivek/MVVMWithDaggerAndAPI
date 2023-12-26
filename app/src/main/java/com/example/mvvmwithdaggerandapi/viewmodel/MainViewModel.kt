package com.example.mvvmwithdaggerandapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithdaggerandapi.model.People
import com.example.mvvmwithdaggerandapi.repository.PeopleRepository
import com.example.mvvmwithdaggerandapi.util.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val peopleRepository: PeopleRepository): ViewModel() {

    private val _peopleData: MutableLiveData<DataStatus<List<People>>> = MutableLiveData()
    val peopleData: LiveData<DataStatus<List<People>>> get() = _peopleData

    fun getPeopleData() =
        viewModelScope.launch{
            _peopleData.value = DataStatus.loading()
            val response = peopleRepository.getPeoples()
            _peopleData.value = handleResponse(response)
        }

    private fun handleResponse(response: Response<List<People>>): DataStatus<List<People>>? {
        if(response.isSuccessful){
            response.body()?.let {
                return DataStatus.success(it)
            }
        }

        return  DataStatus.error(response.message())
    }
}