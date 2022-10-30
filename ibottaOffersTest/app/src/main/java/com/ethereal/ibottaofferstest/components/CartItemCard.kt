package com.ethereal.ibottaofferstest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.objects.Offer

// List item used on the CartScreen
@Composable
fun CartItemCard(offer: Offer) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {

        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth(0.9f)) {
            // Title
            Text(
                text = offer.name,
                color = MaterialTheme.colors.secondary,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(.5f)
            )
            Spacer(modifier = Modifier.weight(.1f))

            Text(
                text = offer.valueToString()[0],
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(.4f)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(MaterialTheme.colors.secondary)
        )
    }
}