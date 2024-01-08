package com.example.chat

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ForegroundInfoService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent?.action == "start"){
            startForeground(1, NotificationCompat.Builder(this, "not_important").setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Table Chat").setContentText("Table Chat is running").build())
        }
        else if(intent?.action == "stop")
            stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }
}