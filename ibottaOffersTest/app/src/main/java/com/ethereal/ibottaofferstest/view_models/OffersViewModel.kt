package com.ethereal.ibottaofferstest.view_models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.ethereal.ibottaofferstest.objects.Offer
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.ByteArrayOutputStream

class OffersViewModel(context: Context) : ViewModel() {

    private val offers = arrayListOf<Offer>().toMutableStateList()
    private var currentSelection: MutableState<Offer?> = mutableStateOf(null)
    private var sorted = mutableStateOf(false)

    init {
        try {
            val jsonString = context.assets.open("Offers.json")
            val baos = ByteArrayOutputStream()
            jsonString.use { it.copyTo(baos) }
            val inputAsString = baos.toString()
            val dataType = Types.newParameterizedType(MutableList::class.java, Offer::class.java)
            val adapter: JsonAdapter<List<Offer>> =
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(dataType)
            offers.addAll(adapter.fromJson(inputAsString)!!)
        } catch (ex: Exception) {
            Log.e("OffersGridScreen", "readOffersFile: ", ex)
        }
    }

    fun setSelected(offer: Offer) {
        currentSelection.value = offer
    }

    fun getSelected(): Offer {
        return currentSelection.value!!
    }

    fun toggleFilter() {
        sorted.value = !sorted.value
    }

    fun getOffers(): List<Offer> {
        return if (!sorted.value) {
            offers.sortedBy { !it.favorited }
        } else {
            offers.filter { it.favorited }
        }
    }

    fun toggleFavorite(offer: Offer) {
        if (offers.contains(offer)) {
            offers.find { it.id == offer.id }?.favorited =
                !offers.find { it.id == offer.id }?.favorited!!
        } else {
            Log.e("OffersViewModel", "toggleFavorite: Error finding offer in Offer List")
        }
    }
}