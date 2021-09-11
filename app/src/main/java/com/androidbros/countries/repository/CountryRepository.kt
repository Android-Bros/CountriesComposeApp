package com.androidbros.countries.repository

import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.network.CountriesAPI

class CountryRepository(private val api: CountriesAPI) {


    suspend fun getAllCountries(): List<CountryItem> {
        return api.getAllCountries()
    }

    suspend fun getCountry(name: String): CountryItem {
        return api.getCountry(name)
    }

}