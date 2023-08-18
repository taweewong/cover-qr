package com.taweewong.coverqr.widget

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.provideContent

class CoverQRWidget(
    private val viewModel: CoverQRViewModel
) : GlanceAppWidget() {

    private var lastGlanceId: GlanceId? = null

    companion object {
        val TEST_ACTION_KEY = ActionParameters.Key<String>("test_action_key")
    }

    enum class CoverQRViewState {
        MAIN, TYPING_NUMBER, TYPING_AMOUNT
    }
    
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var viewState by remember { mutableStateOf(CoverQRViewState.MAIN) }

            when (viewState) {
                CoverQRViewState.MAIN -> CoverQRScreenMain(
                    mobileNumber = viewModel.mobileNumber,
                    amount = viewModel.amount,
                    onMobileNumberButtonClicked = { viewState = CoverQRViewState.TYPING_NUMBER },
                    onAmountButtonClicked = { viewState = CoverQRViewState.TYPING_AMOUNT }
                )
                CoverQRViewState.TYPING_NUMBER -> CoverQRScreenTypeNumber(
                    value = viewModel.mobileNumber,
                    onConfirmNumber = { number ->
                        viewModel.mobileNumber = number
                        viewModel.saveMobileNumber(number)
                        viewState = CoverQRViewState.MAIN
                    }
                )
                CoverQRViewState.TYPING_AMOUNT -> CoverQRScreenTypeNumber(
                    value = viewModel.amount,
                    onConfirmNumber = { amount ->
                        viewModel.amount = amount
                        viewState = CoverQRViewState.MAIN
                    }
                )
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

    override val sizeMode: SizeMode = SizeMode.Exact
}