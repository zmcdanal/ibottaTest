package com.ethereal.ibottaofferstest

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme
import com.ethereal.ibottaofferstest.view_models.setupContext
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun myTest() {
        setupContext(instrumentationContext)

        // Start the app
        composeTestRule.setContent {
            IbottaOffersTestTheme {

            }
        }
    }
}