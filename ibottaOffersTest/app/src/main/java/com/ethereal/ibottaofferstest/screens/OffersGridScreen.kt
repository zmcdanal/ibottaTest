package com.ethereal.ibottaofferstest.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethereal.ibottaofferstest.components.OfferListItemCard
import com.ethereal.ibottaofferstest.routes.Routes
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OffersGridScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colors.onBackground),
                title = {
                    Text(text = "Offers", color = Color.Black)
                }
            )
        }
    ) {

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(offersViewModel.getOffers()) { item ->
                OfferListItemCard(offer = item) {
                    offersViewModel.toggleFavorite(item)
                    navController.navigate(Routes.DetailsScreen.route)
                }
            }
        }
    }
}

@Preview
@Composable
fun OffersGridScreenPreview() {
    IbottaOffersTestTheme {
        //OffersGridScreen()
    }
}