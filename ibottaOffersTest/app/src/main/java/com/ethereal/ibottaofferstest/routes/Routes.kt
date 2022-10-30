package com.ethereal.ibottaofferstest.routes


// All Route locations for NavController
sealed class Routes(val route: String) {
    object CartScreen : Routes("cart")
    object DetailsScreen : Routes("details")
    object OffersGridScreen : Routes("offers")
}