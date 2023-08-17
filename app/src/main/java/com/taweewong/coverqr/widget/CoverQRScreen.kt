package com.taweewong.coverqr.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taweewong.coverqr.R
import com.taweewong.coverqr.ui.theme.CoverQRTheme

@Preview(showBackground = true)
@Composable
fun Preview() {
    CoverQRTheme {
        CoverQRScreen()
    }
}

@Composable
fun CoverQRScreen() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "",
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    )
}

