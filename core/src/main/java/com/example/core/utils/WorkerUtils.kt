package com.example.core.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.core.R
import com.example.core.utils.CoreConstant.CHANNEL_ID
import com.example.core.utils.CoreConstant.NOTIFICATION_ID
import com.example.core.utils.CoreConstant.NOTIFICATION_TITLE
import com.example.core.utils.CoreConstant.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.core.utils.CoreConstant.VERBOSE_NOTIFICATION_CHANNEL_NAME
import java.io.File
import kotlin.random.Random

object WorkerUtils {
    fun makeStatusNotification(message: String, context: Context) {

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
            val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            // Add the channel
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)
        }

        // Create the notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))

        // Show the notification
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        }
    }

    @WorkerThread
    fun blurBitmap(bitmap: Bitmap, blurLevel: Int): Bitmap {
        val input = Bitmap.createScaledBitmap(
            bitmap,
            bitmap.width/(blurLevel*5),
            bitmap.height/(blurLevel*5),
            true
        )
        return Bitmap.createScaledBitmap(input, bitmap.width, bitmap.height, true)
    }

    @RequiresApi(Build.VERSION_CODES.FROYO)
    fun Bitmap.saveImage( context: Context): String {
        val rand = Random.nextInt()
        val destinationPath =
            File(context.getExternalFilesDir("image"), "photoProfile$rand.jpg")
        val fileOutputStream = destinationPath.outputStream() //destination
        this.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.close()

        return destinationPath.path
    }


}