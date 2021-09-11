package com.androidbros.countries.repository

import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.network.CountriesAPI
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CountryRepository @Inject constructor(private val api: CountriesAPI) {


    suspend fun getAllCountries(): List<CountryItem> {
        return api.getAllCountries()
    }

    suspend fun getCountry(name: String): CountryItem {
        return api.getCountry(name)
    }

}