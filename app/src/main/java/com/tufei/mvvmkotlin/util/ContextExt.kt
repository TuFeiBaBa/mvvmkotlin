package com.tufei.mvvmkotlin.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.tufei.architecturedemo.util.ActivityCollector
import java.io.File

/**
 * @author tufei
 * @date 2018/2/19.
 */
private var toast: Toast? = null

@SuppressLint("ShowToast")
fun Context.showToast(tip: String, time: Int = Toast.LENGTH_SHORT) {
    if (time !in Toast.LENGTH_SHORT..Toast.LENGTH_LONG) {
        throw IllegalArgumentException("Only Toast.LENGTH_SHORT or Toast.LENGTH_LONG！")
    }
    toast?.apply {
        setText(tip)
        duration = time
    } ?: apply {
        toast = Toast.makeText(applicationContext, tip, time)
    }
    toast?.show()
}

inline fun <reified T : Activity> Context.restartApp(deplay: Long = 200) {
    val intent = Intent(applicationContext, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val restartIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
    val alarm = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarm.set(AlarmManager.RTC, System.currentTimeMillis() + deplay, restartIntent)
    ActivityCollector.exitApp()
}

fun Context.installApp(apkPath: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.fromFile(File(apkPath)), "application/vnd.android.package-archive")
    startActivity(intent)
}

