package com.ethereal.ibottaofferstest.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethereal.ibottaofferstest.R
import com.ethereal.ibottaofferstest.ui.theme.IbottaOffersTestTheme

// Floating Action Button used on multiple screens
@Composable
fun RoundedFAB(text: String, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .height(50.dp)
            .then(modifier)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.roboto_bold))
        )
    }
}

@Preview
@Composable
fun RoundedFABPreview() {
    IbottaOffersTestTheme {
        RoundedFAB(text = "Purchase", onClick = { }, modifier = Modifier)
    }
}