package com.tufei.mvvmkotlin.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.support.annotation.RawRes
import android.support.annotation.StringRes
import android.widget.Toast
import com.tufei.architecturedemo.util.ActivityCollector
import com.tufei.mvvmkotlin.SingleLiveEvent
import java.io.*
import java.nio.charset.Charset

/**
 * @author tufei
 * @date 2018/2/19.
 */
private var toast: Toast? = null

enum class ToastTime {
    LONG, SHORT
}

@SuppressLint("ShowToast")
fun Context.showToast(lifecycleOwner: LifecycleOwner, toastTipEvent: SingleLiveEvent<Int>, toastTime: ToastTime = ToastTime.SHORT) {
    toastTipEvent.observe(lifecycleOwner, Observer {
        it?.let {
            showToast(it, toastTime)
        }
    })
}

@SuppressLint("ShowToast")
fun Context.showToast(@StringRes tipRes: Int, toastTime: ToastTime = ToastTime.SHORT) {
    val time = when (toastTime) {
        ToastTime.LONG -> Toast.LENGTH_LONG
        ToastTime.SHORT -> Toast.LENGTH_SHORT
    }
    toast?.apply {
        setText(getString(tipRes))
    } ?: apply {
        toast = Toast.makeText(applicationContext, getString(tipRes), time)
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
 * @return 文件内容
 */
@Throws(IOException::class, FileNotFoundException::class)
fun Context.readAssetsFile(file: String): String {
    var len: Int
    var buf: ByteArray
    var result = ""
    with(assets.open(file)) {
        len = available()
        buf = ByteArray(len)
        read(buf, 0, len)
        result = String(buf, Charset.forName("utf-8"))
    }
    return result
}

/**
 * 读取raw目录下的文件
 */
@Throws(IOException::class, Resources.NotFoundException::class)
fun Context.readRawFile(@RawRes rawId: Int): ByteArray {
    val inputStream: InputStream = applicationContext.resources.openRawResource(rawId)
    val byteArrayOutputStream = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var count = 0
    try {
        while (count != -1) {
            byteArrayOutputStream.write(buffer, 0, count)
            count = inputStream.read(buffer)
        }
    } finally {
        byteArrayOutputStream.close()
        inputStream.close()
    }
    return byteArrayOutputStream.toByteArray()
}

fun Context.getScreenWidth() = applicationContext.resources.displayMetrics.widthPixels
fun Context.getScreenHeight() = applicationContext.resources.displayMetrics.heightPixels
fun Context.getScreenDensity() = applicationContext.resources.displayMetrics.density