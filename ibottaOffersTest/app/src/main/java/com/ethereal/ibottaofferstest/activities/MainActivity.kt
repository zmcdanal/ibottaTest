package com.ethereal.ibottaofferstest.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ethereal.ibottaofferstest.routes.Routes
import com.ethereal.ibottaofferstest.screens.CartScreen
import com.ethereal.ibottaofferstest.screens.DetailsScreen
import com.ethereal.ibottaofferstest.screens.OffersGridScreen
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.utilities.viewModelProviderFactoryOf
import com.ethereal.ibottaofferstest.view_models.LocalNavController
import com.ethereal.ibottaofferstest.view_models.OffersViewModel
import com.ethereal.ibottaofferstest.view_models.setupContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IbottaOffersTestTheme {
                val offersViewModel: OffersViewModel = viewModel(
                    key = "OffersViewModel",
                    factory = viewModelProviderFactoryOf {
                        OffersViewModel(
                            context = this
                        )
                    }
                )

                val navController = rememberNavController()
                setupContext(this)

                CompositionLocalProvider(LocalNavController provides navController) {

                    NavHost(
                        navController = navController,
                        startDestination = Routes.OffersGridScreen.route
                    ) {

                        composable(Routes.OffersGridScreen.route) {
                            OffersGridScreen(offersViewModel = offersViewModel)
                        }

                        composable(Routes.DetailsScreen.route) {
                            DetailsScreen(offersViewModel = offersViewModel)
                        }

                        composable(Routes.CartScreen.route) {
                            CartScreen(offersViewModel = offersViewModel)
                        }
                    }
                }
            }
        }
    }
}