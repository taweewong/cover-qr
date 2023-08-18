package com.taweewong.coverqr.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.github.pheerathach.ThaiQRPromptPay
import com.google.zxing.WriterException
import java.math.BigDecimal

class CoverQRWidget(
    private val viewModel: CoverQRViewModel
) : GlanceAppWidget() {

    private var lastGlanceId: GlanceId? = null

    companion object {
        val TEST_ACTION_KEY = ActionParameters.Key<String>("test_action_key")
    }
    
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var mobileNumber by remember { mutableStateOf("0822231404") }
            var amount by remember { mutableStateOf("") }

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
                    if (mobileNumber.isEmpty()) {
                        Text(text = viewModel.test)
                    } else {
                        val content = getQRContent(mobileNumber, amount)
                        if (content != null) {
                            val image = generateQRImage(content)
                            if (image != null) {
                                Image(
                                    modifier = GlanceModifier.size(200.dp),
                                    provider = ImageProvider(image),
                                    contentDescription = "Image"
                                )
                            } else {
                                Text(text = "Error: Something went wrong!")
                            }
                        } else {
                            Text(text = "Error: Something went wrong!")
                        }
                    }
                }
                Row(
                    modifier = GlanceModifier
                        .padding(
                            start = 16.dp,
                            bottom = 32.dp,
                            end = 16.dp,
                            top = 0.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column {
                        Button(text = "test", onClick = {})
                    }
                    Column(modifier = GlanceModifier.padding(start = 16.dp)) {
                        Button(text = "test2", onClick = {})
                    }
                }
            }
        }
    }

    class TestCallBack : ActionCallback {
        override suspend fun onAction(
            context: Context,
            glanceId: GlanceId,
            parameters: ActionParameters
        ) {

        }
    }

    @Composable
    fun ShowError() {
        Text(text = "test")
    }

    private fun getQRContent(mobileNumber: String?, amount: String?): String? {
        val qr = when {
            !mobileNumber.isNullOrEmpty() && !amount.isNullOrEmpty() -> {
                ThaiQRPromptPay
                    .Builder()
                    .staticQR()
                    .creditTransfer()
                    .mobileNumber(mobileNumber)
                    .amount(BigDecimal("10.00"))
                    .build()
            }

            !mobileNumber.isNullOrEmpty() && amount.isNullOrEmpty() -> {
                ThaiQRPromptPay
                    .Builder()
                    .staticQR()
                    .creditTransfer()
                    .mobileNumber(mobileNumber)
                    .build()
            }

            else -> {
                return null
            }
        }
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