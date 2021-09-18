package com.androidbros.countries.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class CountryDetailViewModel @Inject constructor(private val repository: CountryRepository): ViewModel() {

    private val _country: MutableState<List<CountryItem>> = mutableStateOf(emptyList())
    val country: State<List<CountryItem>>
        get() = _country

    fun loadCountry(name: String) {
        viewModelScope.launch {
            try {
                _country.value = getCountry(name)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private suspend fun getCountry(name: String): List<CountryItem>{
        return repository.getCountry(name)
    }

}