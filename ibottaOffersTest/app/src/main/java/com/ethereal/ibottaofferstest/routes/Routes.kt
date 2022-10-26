package com.ethereal.ibottaofferstest.routes

sealed class Routes(val route: String) {
    object DetailsScreen : Routes("details")
    object OffersGridScreen : Routes("offers")
}