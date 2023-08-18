package com.taweewong.coverqr.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CoverQRWidgetReceiver : GlanceAppWidgetReceiver(), KoinComponent {
    private val viewModel: CoverQRViewModel by inject()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override val glanceAppWidget: GlanceAppWidget = CoverQRWidget(viewModel)

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Toast.makeText(context, "Receive", Toast.LENGTH_SHORT).show()
        observeWidgetReload(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show()
        observeWidgetReload(context)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        Toast.makeText(context, "Bye Bye", Toast.LENGTH_SHORT).show()
    }

    private fun observeWidgetReload(context: Context) = coroutineScope.launch {
        GlanceAppWidgetManager(context).getGlanceIds(CoverQRWidget::class.java).firstOrNull()?.let { glanceId ->
            glanceAppWidget.update(context, glanceId)
        }
    }
}