package com.ethereal.ibottaofferstest.components

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.objects.Offer
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme

@Composable
fun OfferListItemCard(offer: Offer) {
    var width = Resources.getSystem().displayMetrics.widthPixels
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            if (!offer.url.isNullOrEmpty()) {
                SubcomposeAsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data(offer.url)
                        .diskCacheKey(offer.id)
                        .build(),
                    contentDescription = "Offer Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(7f)
                ) {
                    val state = painter.state
                    when {
                        (state is AsyncImagePainter.State.Loading) -> CircularProgressIndicator()
                        (state is AsyncImagePainter.State.Error) -> {
                            // Reporting error message to determine exact cause for image load error
                            // Display a temp 'no image' placeholder if image cannot load
                            Log.e("OfferListItemCard", "Image Load Error: ", state.result.throwable)
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_image_24),
                                contentDescription = "No Image",
                                contentScale = ContentScale.Fit
                            )
                        }
                        else -> SubcomposeAsyncImageContent()
                    }
                }
            } else {
                Log.i("OfferListItemCard", "No image found for item: ${offer.id}")
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_image_24),
                    contentDescription = "No Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(7f)
                )
            }
        }
        Text(
            text = offer.current_value,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun OfferListItemCardPreview() {
    IbottaOffersTestTheme {
        OfferListItemCard(
            Offer(
                id = "23d32",
                url = "https://product-images.ibotta.com/offer/WqhWM4IPi8UORU4MRbiUbQ-normal.png",
                name = "Cherries",
                description = "Yada Yada Yada",
                terms = "Blah Blah Blah",
                current_value = "5.99"
            )
        )
    }
}