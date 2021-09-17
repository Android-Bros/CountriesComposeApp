package com.androidbros.countries.view.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.androidbros.countries.view.components.CountryCard
import com.androidbros.countries.view.components.TopBar
import com.androidbros.countries.viewmodel.CountryListViewModel

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun CountryListScreen(navController: NavController,viewModel:CountryListViewModel= hiltViewModel()) {

    val listState=viewModel.countriesList


    Scaffold(topBar = { TopBar()}) {


        if (!listState.value.isNullOrEmpty()){

            LazyColumn{
                items(listState.value){ item ->

                    CountryCard(navController,item)

                }


            }

        }





    }


}