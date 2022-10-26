package com.ethereal.ibottaofferstest.objects

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
class Offer(val id: String, val url: String?, val name: String, val description: String, val terms: String, val current_value: String, var favorited: Boolean = false)

