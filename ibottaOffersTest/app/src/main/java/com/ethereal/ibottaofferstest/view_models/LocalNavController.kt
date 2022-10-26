package com.ethereal.ibottaofferstest.view_models

import android.content.Context
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

lateinit var LocalNavController: ProvidableCompositionLocal<NavHostController>

fun setupContext(contextIn: Context) {
    LocalNavController = staticCompositionLocalOf { NavHostController(contextIn) }
}