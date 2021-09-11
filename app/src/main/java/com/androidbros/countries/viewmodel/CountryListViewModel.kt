package com.androidbros.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {

    private val _countriesList: MutableLiveData<List<CountryItem>> = MutableLiveData()
    val countriesList: LiveData<List<CountryItem>>
        get() = _countriesList


    init {
        loadAllCountries()
    }

    private fun loadAllCountries() {
        viewModelScope.launch {
            try {
                _countriesList.value = getAllCountries()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private suspend fun getAllCountries(): List<CountryItem> {
        return repository.getAllCountries()
    }

}