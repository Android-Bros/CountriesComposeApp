package com.androidbros.countries.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {

    private val _countriesList: MutableState<List<CountryItem>>  = mutableStateOf(emptyList())
    val countriesList: State<List<CountryItem>>
        get() = _countriesList


    init {
        loadAllCountries()
    }

    private fun loadAllCountries() {
        viewModelScope.launch {
            _countriesList.value = getAllCountries()
        }
    }

    private suspend fun getAllCountries(): List<CountryItem> {
        return repository.getAllCountries()
    }

}