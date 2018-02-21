package com.tufei.architecturedemo.util

import android.app.Activity

/**
 * @author tufei
 * @date 2018/2/19.
 */
object ActivityCollector {
    var activities: MutableList<Activity> = ArrayList()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    private fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 获取栈顶的activity
     */
    fun getCurrentActivity(): Activity {
        return activities[activities.size - 1]
    }

    fun exitApp() {
        ActivityCollector.finishAll()
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}
