package com.ethereal.ibottaofferstest.components

import android.content.res.Resources
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.objects.Offer
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme

@Composable
fun OfferListItemCard(offer: Offer, onClick: () -> Unit) {
    
    Box(modifier = Modifier.clickable { onClick() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                backgroundColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(5.dp),
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = offer.current_value,
                color = colorResource(id = R.color.amount_color),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = offer.name,
                color = colorResource(id = R.color.amount_color),
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            if (offer.favorited) {
                Image(painter = painterResource(id = R.drawable.favorite_filled_24), contentDescription = "favorite")
            }
        }
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
        ) {

        }
    }
}