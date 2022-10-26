package com.ethereal.ibottaofferstest.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.components.OfferListItemCard
import com.ethereal.ibottaofferstest.objects.Offer
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.ByteArrayOutputStream


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OffersGridScreen() {
    val context = LocalContext.current
    val offers = readOffersFile(context, stringResource(id = R.string.offers))

    offers?.forEach {
        println("hey bobby ${it.url}")
    }
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(offers!!) { item ->
            OfferListItemCard(offer = item)
        }
    }
}

private fun readOffersFile(context: Context, file: String): List<Offer>? {
    try {
        val jsonString = context.assets.open(file)
        val baos = ByteArrayOutputStream()
        jsonString.use { it.copyTo(baos) }
        val inputAsString = baos.toString()
        val dataType = Types.newParameterizedType(MutableList::class.java, Offer::class.java)
        val adapter: JsonAdapter<List<Offer>> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(dataType)
        return adapter.fromJson(inputAsString)!!
    } catch (ex: Exception) {
        Log.e("OffersGridScreen", "readOffersFile: ", ex)
    }
    return null
}

@Preview
@Composable
fun OffersGridScreenPreview() {
    IbottaOffersTestTheme {
        OffersGridScreen()
    }
}