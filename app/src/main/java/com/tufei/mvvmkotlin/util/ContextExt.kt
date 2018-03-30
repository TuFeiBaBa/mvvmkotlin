package com.tufei.mvvmkotlin.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.support.annotation.RawRes
import android.widget.Toast
import com.tufei.architecturedemo.util.ActivityCollector
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

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

/**
 * 检查当前网络是否可用
 */
@Suppress("DEPRECATION")
@SuppressLint("NewApi")
fun Context.isNetworkAvailable(): Boolean {
    // 获取手机所有连接管理对象(包括对wifi,net等连接的管理)
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
            as? ConnectivityManager
    connectivityManager ?: return false
    when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        true -> {
            var networkInfo: NetworkInfo
            connectivityManager.allNetworks.forEach {
                networkInfo = connectivityManager.getNetworkInfo(it)
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        false -> {
            connectivityManager.allNetworkInfo.forEach {
                if (it.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
    }
    return false
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

/**
 * 读取assets目录下的文件
 */
fun Context.readAssetsFile(file: String): String {
    val len: Int
    val buf: ByteArray
    var result = ""
    try {
        val `in` = assets.open(file)
        len = `in`.available()
        buf = ByteArray(len)
        `in`.read(buf, 0, len)

        result = String(buf, Charset.forName("utf-8"))
        `in`.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}

/**
 * 读取raw目录下的文件
 */
fun Context.readRawFile(@RawRes rawId: Int): ByteArray {
    var inputStream: InputStream? = null
    val byteArrayOutputStream = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var count = 0
    try {
        inputStream = applicationContext.resources.openRawResource(rawId)
        while (count != -1) {
            byteArrayOutputStream.write(buffer, 0, count)
            count = inputStream.read(buffer)
        }
        byteArrayOutputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return byteArrayOutputStream.toByteArray()
}

fun Context.getScreenWidth() = applicationContext.resources.displayMetrics.widthPixels
fun Context.getScreenHeight() = applicationContext.resources.displayMetrics.heightPixels
fun Context.getScreenDensity() = applicationContext.resources.displayMetrics.density