package com.androidbros.countries.network

import com.androidbros.countries.model.CountryItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): List<CountryItem>

    @GET("name/{NAME}")
    suspend fun getCountry(@Path("NAME") name: String): List<CountryItem>

}