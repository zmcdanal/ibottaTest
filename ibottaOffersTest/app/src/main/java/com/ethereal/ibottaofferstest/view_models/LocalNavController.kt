package com.ethereal.ibottaofferstest.view_models

import android.content.Context
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

// Makes it possible to call to NavController from anywhere within the app
lateinit var LocalNavController: ProvidableCompositionLocal<NavHostController>

fun setupContext(contextIn: Context) {
    LocalNavController = staticCompositionLocalOf { NavHostController(contextIn) }
}