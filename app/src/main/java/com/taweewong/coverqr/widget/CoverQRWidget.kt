package com.taweewong.coverqr.widget

import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.ui.unit.dp
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
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.github.pheerathach.ThaiQRPromptPay
import com.google.zxing.WriterException
import java.math.BigDecimal


class CoverQRWidget : GlanceAppWidget() {
    private var lastGlanceId: GlanceId? = null

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val content = getQRContent()
            val image = generateQRImage(content)

            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = GlanceModifier.defaultWeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (image != null) {
                        Image(
                            modifier = GlanceModifier.size(200.dp),
                            provider = ImageProvider(image),
                            contentDescription = "Image"
                        )
                    } else {
                        Text(text = "Error: Something went wrong!")
                    }
                }
            }
        }
    }

    private fun getQRContent(): String {
        val qr = ThaiQRPromptPay
            .Builder()
            .staticQR()
            .creditTransfer()
            .mobileNumber("0822231404")
//            .amount(BigDecimal("10.00"))
            .build()
        return qr.generateContent()
    }

    private fun generateQRImage(data: String): Bitmap? {
        val qrgEncoder = QRGEncoder(data, null, QRGContents.Type.TEXT, 200)
        qrgEncoder.colorBlack = Color.BLACK
        qrgEncoder.colorWhite = Color.WHITE

        return try {
            qrgEncoder.getBitmap(0)
        } catch (e: WriterException) {
            Log.e("generateQRImage", e.toString())
            null
        }
    }

    override val sizeMode: SizeMode = SizeMode.Exact
}