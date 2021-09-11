package com.androidbros.countries.model

import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("alpha2Code")
    val alpha2Code: String,
    @SerializedName("altSpellings")
    val altSpellings: List<String>,
    @SerializedName("area")
    val area: Double,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("currencies")
    val currencies: List<Currency>,
    @SerializedName("demonym")
    val demonym: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("latlng")
    val latlng: List<Double>,
    @SerializedName("name")
    val name: String,
    @SerializedName("nativeName")
    val nativeName: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subregion: String,
    @SerializedName("timezones")
    val timezones: List<String>
)
