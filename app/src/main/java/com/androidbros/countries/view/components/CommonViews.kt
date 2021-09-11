package com.androidbros.countries.view.components

import android.graphics.Insets.add
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.androidbros.countries.R
import com.androidbros.countries.model.CountryItem
import com.androidbros.countries.ui.theme.TopBarBackgroundColor


@Composable
fun TopBar() {

    TopAppBar(backgroundColor = TopBarBackgroundColor) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.earth),
                contentDescription = "earth",
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }


    }


}


@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun CountryCard(navController: NavController, country:CountryItem) {
    
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {


    Card(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp)),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        
        Column() {

            var expanded by remember { mutableStateOf(false) }
            val svgLink = country.flag
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }
                .build()

            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                val painter = rememberImagePainter(svgLink)
            Image(
                painter =  painter,
                contentDescription = "flag",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)
                    )
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { navController.navigate("country_detail_screen" + "/${country.name}") },
                contentScale = ContentScale.Crop

            )}



            
            Spacer(modifier = Modifier.size(5.dp))
            
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${country.name} · ${country.alpha2Code}",
                    style = if (expanded) MaterialTheme.typography.h4 else MaterialTheme.typography.h5
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow",
                    modifier = Modifier.clickable { expanded = !expanded }
                )

            }


            AnimatedVisibility(visible = expanded) {

                Column(modifier = Modifier.padding(start = 10.dp,end = 10.dp,bottom = 10.dp)) {

                    Text(
                        text = "Capital : ${country.capital}",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Region : ${country.region}",
                        style = MaterialTheme.typography.h5
                    )

                }


            }






            
            
        }
        

        
    }

    }
    
}

