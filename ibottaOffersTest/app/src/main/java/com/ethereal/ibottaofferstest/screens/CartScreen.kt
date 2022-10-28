package com.ethereal.ibottaofferstest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.components.CartItemCard
import com.ethereal.ibottaofferstest.components.RoundedFAB
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Shopping Cart",
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        color = MaterialTheme.colors.secondary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colors.background
                )
            )
        },
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(.9F)
            ) {

                RoundedFAB(
                    text = "Purchase",
                    onClick = {
                        // Nothing is supposed to happen
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.7f)
            ) {
                items(offersViewModel.getCart()) { item ->
                    CartItemCard(offer = item)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.secondary)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.2f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.9F)
                        .padding(top = 24.dp)
                ) {
                    // Title
                    Text(
                        text = "Total",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .weight(.5f)
                            .padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(.1f))

                    Text(
                        text = "5.99",
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.End,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .weight(.4f)
                            .padding(end = 8.dp)
                    )
                }
            }
        }
    }
}
