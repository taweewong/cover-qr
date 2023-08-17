package com.taweewong.coverqr.widget

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.taweewong.coverqr.R

class CoverQRWidget : GlanceAppWidget() {
    private var lastGlanceId: GlanceId? = null

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
//            CoverQRTheme {
//                CoverQRScreen()
//            }
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = GlanceModifier.defaultWeight()) {
                    Image(
                        modifier = GlanceModifier.size(100.dp),
                        provider = ImageProvider(R.drawable.ic_launcher_foreground),
                        contentDescription = "Image"
                    )
                }
                Row(modifier = GlanceModifier.defaultWeight()) {
                    Text(
                        modifier = GlanceModifier,
                        text = "Hello"
                    )
                }
                Row(modifier = GlanceModifier.defaultWeight()) {
                    Button(modifier = GlanceModifier,
                        text = "click me",
                        onClick = {}
                    )
                }
            }


        }
    }

    override val sizeMode: SizeMode = SizeMode.Exact
}