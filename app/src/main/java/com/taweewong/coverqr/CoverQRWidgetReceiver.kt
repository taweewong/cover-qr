package com.taweewong.coverqr

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class CoverQRWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = CoverQRWidget()

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Toast.makeText(context, "Receive", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        Toast.makeText(context, "Bye Bye", Toast.LENGTH_SHORT).show()
    }
}