package com.androidbros.countries.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.androidbros.countries.R
import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.view.components.TopBar
import com.androidbros.countries.viewmodel.CountryDetailViewModel


@Composable
fun CountryDetailScreen(navController: NavController, name: String?) {


    val viewModel:CountryDetailViewModel= hiltViewModel()
    viewModel.loadCountry(name!!)
    val detailState=viewModel.country


    Scaffold(topBar = { TopBar()}) {

        if (!detailState.value.isNullOrEmpty()){

            val detail= detailState.value[0]
            DetailScreenComponents(detail)

        }






    }


}

@ExperimentalCoilApi
@Composable
fun DetailScreenComponents(detail:CountryItem) {


    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {


        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 5.dp)) {

            BoxWithConstraints {
                Image(
                    painter = painterResource(id = R.drawable.earth),
                    contentDescription = "map",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 120.dp),
                    horizontalArrangement = Arrangement.Center
                ) {


                    val svgLink = detail.flag
                    val imageLoader = ImageLoader.Builder(LocalContext.current)
                        .componentRegistry {
                            add(SvgDecoder(LocalContext.current))
                        }
                        .build()


                    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                        val painter = rememberImagePainter(svgLink)

                        Image(
                            painter = painter,
                            contentDescription = "flag",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

            }


            
            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = "${detail.name} · ${detail.alpha2Code}",
                modifier = Modifier.padding(start = 15.dp, end = 10.dp),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Capital: ${detail.capital}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Region: ${detail.region}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Currency: ${detail.currencies[0].code}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )


            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Population: ${detail.population}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Area: ${detail.area}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Native Name: ${detail.nativeName}",
                modifier = Modifier.padding(start = 17.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )


        }








    }



    





}





