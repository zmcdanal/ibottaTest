package com.ethereal.ibottaofferstest.view_models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.ethereal.ibottaofferstest.objects.Offer
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.collections.ArrayList

class OffersViewModel(context: Context) : ViewModel() {

    private val offers = arrayListOf<Offer>().toMutableStateList()
    private val cart = arrayListOf<Offer>().toMutableStateList()
    private var currentSelection: MutableState<Offer?> = mutableStateOf(null)
    private var sorted = mutableStateOf(false)

    init {
        // If this was much more data, I would likely run this within an async function
        try {
            val jsonString =
                context.assets.open("Offers.json")
            val baos = ByteArrayOutputStream()
            jsonString.use { it.copyTo(baos) }
            val inputAsString = baos.toString()
            val dataType =
                Types.newParameterizedType(MutableList::class.java, Offer::class.java)
            val adapter: JsonAdapter<List<Offer>> =
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(dataType)
            offers.addAll(adapter.fromJson(inputAsString)!!)
        } catch (ex: Exception) {
            Log.e("OffersGridScreen", "readOffersFile: ", ex)
        }
    }

    // The offer that gets used in the details screen
    fun setSelected(offer: Offer) {
        currentSelection.value = offer
    }

    // The offer that gets used in the details screen
    fun getSelected(): Offer {
        return currentSelection.value!!
    }

    // Toggle between All Offers and Favorites
    fun toggleFilter() {
        sorted.value = !sorted.value
    }

    // Grabs all offers and sorts them based on toggle
    fun getOffers(): List<Offer> {
        return if (!sorted.value) {
            offers.sortedBy { !it.favorited }
        } else {
            offers.filter { it.favorited }
        }
    }

    // Gets the shopping cart for use on the CartScreen
    fun getCart(): List<Offer> {
        return cart
    }

    // Toggles offer between favorite and unfavorite
    fun toggleFavorite(onComplete: () -> Unit) {
        currentSelection.value!!.favorited = !currentSelection.value!!.favorited
        onComplete()
    }

    // Adds/Removes item from shopping cart
    fun toggleCart() {
        if (cart.contains(currentSelection.value)) {
            cart.remove(currentSelection.value)
        } else {
            cart.add(currentSelection.value!!)
        }
    }

    // Computes the total of all items prices in the shopping cart
    fun getCartTotal(): String {
        var total = BigDecimal(0.00)
        cart.forEach {
            try {
                val priceString = it.valueToString()
                val price = priceString[0].split("$")
                total += price[1].toBigDecimal()
            } catch (ex: Exception) {
                Log.e("OffersViewModel", "getCartTotal: ", ex)
            }
        }
        return "$${total.setScale(2, RoundingMode.UNNECESSARY)}"
    }

    // Removes all items from cart
    fun emptyCart() {
        cart.clear()
    }

    // FOR FUN - Scrambled all names of offers
    fun scramble(item: Offer) {
        val l: MutableList<Char> = ArrayList()
        for (c in item.name.toCharArray())
            l.add(c)
        l.shuffle()
        val sb = StringBuilder()
        for (c in l) sb.append(c)
        item.name = sb.toString()
    }
}