package com.taweewong.coverqr.widget

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.github.pheerathach.ThaiQRPromptPay
import com.google.zxing.WriterException
import com.taweewong.coverqr.R
import java.math.BigDecimal

@Composable
fun CoverQRScreenMain(
    mobileNumber: String,
    amount: String,
    onMobileNumberButtonClicked: () -> Unit,
    onAmountButtonClicked: () -> Unit,
    onResetButtonClicked: () -> Unit,
) {
//    var mobileNumberState by remember { mutableStateOf("") }

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
                Text(text = "ใส่เบอร์หน่อยจ้า")
            } else {
                val content = getQRContent(mobileNumber, amount)
                if (content != null) {
                    val image = generateQRImage(content)
                    if (image != null) {
                        androidx.glance.Image(
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
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                val text = mobileNumber.ifEmpty { "Mobile Number" }
                Button(
                    text = text,
                    onClick = {
                        onMobileNumberButtonClicked()
                    })
            }
            Column(modifier = GlanceModifier.padding(start = 8.dp)) {
                val text = amount.ifEmpty { "Amount" }
                Button(
                    text = text,
                    onClick = {
                        onAmountButtonClicked()
                    })
            }
            Column(modifier = GlanceModifier.padding(start = 8.dp)) {
                Button(
                    text = "x",
                    colors = ButtonDefaults.buttonColors(backgroundColor = ColorProvider(R.color.red)),
                    onClick = {
                        onResetButtonClicked()
                    })
            }
        }
    }
}

@Composable
fun CoverQRScreenTypeNumber(
    value: String,
    onConfirmNumber: (number: String) -> Unit
) {
    var valueState by remember { mutableStateOf(value) }
    val buttonWidth = 65.dp
    val buttonHeight = 55.dp

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = valueState,
                maxLines = 1,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = GlanceModifier
                    .width(200.dp)
                    .background(R.color.white)
                    .padding(vertical = 8.dp)
                    .cornerRadius(4.dp)
            )
        }
        Row(GlanceModifier.padding(top = 8.dp)) {
            Box {
                Button(text = "1", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 1 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "2", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 2 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "3", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 3 })
            }
        }
        Row(GlanceModifier.padding(top = 4.dp)) {
            Box {
                Button(text = "4", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 4 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "5", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 5 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "6", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 6 })
            }
        }
        Row(GlanceModifier.padding(top = 4.dp)) {
            Box {
                Button(text = "7", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 7 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "8", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 8 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "9", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 9 })
            }
        }
        Row(GlanceModifier.padding(top = 4.dp)) {
            Box {
                Button(text = "del", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight).padding(0), onClick = { valueState = valueState.dropLast(1) })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "0", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { valueState += 0 })
            }
            Box(GlanceModifier.padding(start = 4.dp)) {
                Button(text = "ok", modifier = GlanceModifier.width(buttonWidth).height(buttonHeight), onClick = { onConfirmNumber(valueState) })
            }
        }
    }
}

private fun getQRContent(mobileNumber: String?, amount: String?): String? {
    val qr = when {
        !mobileNumber.isNullOrEmpty() && !amount.isNullOrEmpty() -> {
            ThaiQRPromptPay
                .Builder()
                .staticQR()
                .creditTransfer()
                .mobileNumber(mobileNumber)
                .amount(BigDecimal(amount))
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

