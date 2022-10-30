package com.ethereal.ibottaofferstest.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.activities.MainActivity
import com.ethereal.ibottaofferstest.components.CartItemCard
import com.ethereal.ibottaofferstest.components.RoundedFAB
import com.ethereal.ibottaofferstest.components.popups.NotificationPopup
import com.ethereal.ibottaofferstest.routes.Routes
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    val mAct = LocalContext.current as MainActivity
    // For toggling the delete all popup
    var deleteAllState by remember { mutableStateOf(false) }

    // Top Nav Bar and overall Scaffold
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
                actions = {
                    // Toggles the deleteAll Popup to remove all items from cart
                    if (offersViewModel.getCart().isNotEmpty()) {
                        IconButton(
                            onClick = { deleteAllState = !deleteAllState },
                            modifier = Modifier.semantics { contentDescription = "Empty Cart" }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Empty Cart Icon",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                },
                navigationIcon = {
                    // Pops the back stack
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
            // Wasn't sure what I wanted to make this button do so I decided to scrambled all names of the offers
            // Original idea was to route you guys to youtube to a RickRoll lol
            if (offersViewModel.getCart().isNotEmpty()) {
                RoundedFAB(
                    text = "Purchase",
                    onClick = {
                        // Nothing is supposed to happen
                        offersViewModel.getOffers().forEach {
                            offersViewModel.scramble(it)
                        }
                        mAct.runOnUiThread {
                            Toast.makeText(mAct, "Scrambled!", Toast.LENGTH_LONG).show()
                        }
                        navController.navigate(Routes.OffersGridScreen.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .semantics { contentDescription = "Purchase" }
                )
            }
        }
    ) {
        // If cart is not empty, show the list of items
        if (offersViewModel.getCart().isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.6f)
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
                        .weight(.3f)
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
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .weight(.5f)
                                .padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(.1f))

                        // Total
                        Text(
                            text = offersViewModel.getCartTotal(),
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
        } else {
            // If cart is empty, show placeholder
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Nothing in cart - Message
                Text(
                    text = "Your Shopping Cart is Empty",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    if (deleteAllState) {
        NotificationPopup(
            onDismiss = {
                deleteAllState = !deleteAllState
            },
            onDismissText = "Cancel",
            onPositive = {
                deleteAllState = !deleteAllState
                offersViewModel.emptyCart()
            },
            title = "Empty Cart",
            message = "Are you sure you wish to remove all items from your cart?"
        )
    }
}
