package com.ethereal.ibottaofferstest.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.components.OfferListItemCard
import com.ethereal.ibottaofferstest.routes.Routes
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OffersGridScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    var list by remember { mutableStateOf(offersViewModel.getOffers()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Offers",
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        color = MaterialTheme.colors.secondary
                    )
                },
                actions = {
                    IconButton(onClick = {
                        offersViewModel.toggleFilter()
                        list = offersViewModel.getOffers()
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_list_24),
                            tint = MaterialTheme.colors.secondary,
                            contentDescription = "Filter"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colors.background
                )
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(MaterialTheme.colors.secondary)
        )

        if (list.isNotEmpty()) {

            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 24.dp,
                    end = 8.dp,
                    bottom = 24.dp
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                items(list) { item ->
                    OfferListItemCard(offer = item) {
                        offersViewModel.toggleFavorite(item)
                        offersViewModel.setSelected(item)
                        navController.navigate(Routes.DetailsScreen.route)
                    }
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "No Items Available to Display",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    modifier = Modifier.fillMaxWidth(.9f)
                )
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