package com.androidbros.countries.di

import com.androidbros.countries.network.CountriesAPI
import com.androidbros.countries.repository.CountryRepository
import com.androidbros.countries.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CountriesModule {


    @Singleton
    @Provides
    fun providesCountryRepository(api: CountriesAPI): CountryRepository {
        return CountryRepository(api)
    }

    @Singleton
    @Provides
    fun providesCountriesAPI(): CountriesAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CountriesAPI::class.java)
    }

}