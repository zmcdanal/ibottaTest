package com.ethereal.ibottaofferstest.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.components.RoundedFAB
import com.ethereal.ibottaofferstest.routes.Routes
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    var favoriteState by remember { mutableStateOf(offersViewModel.getSelected().favorited) }
    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        color = MaterialTheme.colors.secondary
                    )
                },
                actions = {
                    BadgedBox(
                        badge = {
                            if (offersViewModel.getCart().isNotEmpty())
                                Badge { Text(offersViewModel.getCart().size.toString()) }
                        },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                navController.navigate(Routes.CartScreen.route)
                            }
                            .semantics { contentDescription = "cart" }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.shopping_cart_24),
                            tint = MaterialTheme.colors.primary,
                            contentDescription = "CartIcon"
                        )
                    }
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
            var cartText by remember {
                mutableStateOf("Add to Cart")
            }
            cartText = if (offersViewModel.getCart().contains(offersViewModel.getSelected())) {
                "Remove from Cart"
            } else {
                "Add to Cart"
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(.9F)
            ) {
                RoundedFAB(
                    text = cartText,
                    onClick = { offersViewModel.toggleCart() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics { contentDescription = "Add to Cart" }
                )
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Box(
                modifier = Modifier.background(MaterialTheme.colors.onBackground)
            ) {
                // Async Image Loader
                SubcomposeAsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data(offersViewModel.getSelected().url)
                        .diskCacheKey(offersViewModel.getSelected().id)
                        .build(),
                    contentDescription = "Offer Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    val state = painter.state
                    when {
                        (state is AsyncImagePainter.State.Loading) -> CircularProgressIndicator()
                        (state is AsyncImagePainter.State.Error) -> {
                            // Reporting error message to determine exact cause for image load error
                            // Display a temp 'no image' placeholder if image cannot load
                            Log.e(
                                "OfferListItemCard",
                                "Image Load Error: ",
                                state.result.throwable
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_image_24),
                                contentDescription = "No Image",
                                contentScale = ContentScale.Fit
                            )
                        }
                        else -> SubcomposeAsyncImageContent()
                    }
                }

                IconButton(
                    onClick = {
                        offersViewModel.toggleFavorite {
                            favoriteState = !favoriteState
                        }
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    // Favorite/Unfavorite the item
                    if (favoriteState) {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite_filled_24),
                            contentDescription = "favorite",
                            tint = MaterialTheme.colors.primary
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite_border_24),
                            contentDescription = "favorite",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                // Name
                Text(
                    text = offersViewModel.getSelected().name,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(.5f)
                )
                Spacer(modifier = Modifier.weight(.05f))

                // Price
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(.45F)
                ) {
                    Text(
                        text = offersViewModel.getSelected().valueToString()[0],
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "${
                            offersViewModel.getSelected().valueToString()[1]
                        } ${offersViewModel.getSelected().valueToString()[2]}",
                        color = MaterialTheme.colors.secondary,
                        textAlign = TextAlign.End,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Description
            Text(
                text = offersViewModel.getSelected().description,
                color = MaterialTheme.colors.secondary,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Terms
            Text(
                text = offersViewModel.getSelected().terms,
                color = MaterialTheme.colors.secondary,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(75.dp))
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    IbottaOffersTestTheme {
        //DetailsScreen()
    }
}