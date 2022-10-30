package com.ethereal.ibottaofferstest.objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
// Offer class for storing in viewModel
class Offer(
    val id: String,
    val url: String?,
    var name: String,
    val description: String,
    val terms: String,
    val current_value: String,
    var favorited: Boolean = false
) {

    // For splitting the current_value up so I can use it in different ways
    // Normally wouldn't do this for a large amount of data but i know everything in the
    // JSON file is the same
    fun valueToString(): List<String> {
        return current_value.split(" ")
    }
}

