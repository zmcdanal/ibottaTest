package com.ethereal.ibottaofferstest.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(offersViewModel: OffersViewModel) {
    val navController = LocalNavController.current
    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Details")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            )
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
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