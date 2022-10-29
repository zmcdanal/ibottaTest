package com.ethereal.ibottaofferstest.components.popups

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ethereal.ibottaofferstest.R

@Composable
fun NotificationPopup(
    onDismiss: () -> Unit,
    onDismissText: String = "Dismiss",
    onPositive: (() -> Unit)? = null,
    onPositiveText: String = "Yes",
    title: String,
    message: String
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(.9f)
        ) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    )
                    Text(
                        text = message,
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp,
                        maxLines = 12,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 10.dp,
                            bottom = 20.dp
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                    ) {
                        // onDismiss button
                        TextButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(.5f)
                                .height(60.dp)
                                .semantics { contentDescription = "onDismiss" }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = onDismissText,
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        if (onPositive != null) {

                            // onPositive button
                            TextButton(
                                onClick = onPositive,
                                modifier = Modifier
                                    .weight(.5f)
                                    .height(60.dp)
                                    .semantics { contentDescription = "onPositive" }
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = onPositiveText,
                                        color = MaterialTheme.colors.primary,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}