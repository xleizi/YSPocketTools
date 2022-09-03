package com.lei123.YSPocketTools.AndroidTools

import android.annotation.SuppressLint
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.getString
import java.text.SimpleDateFormat
import java.util.*

object TimeCounter {

    /**
     * 计算达到秒数目标的时间
     * @param needTime  秒数
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun AimTime(needTime: Int?): String {
        val nowTime = Date()
        val time = (needTime?.times(1000))?.toLong() //recoveryTime*60秒
        val ts = System.currentTimeMillis() + time!!
        val res: String
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        val simpleDateFormat = SimpleDateFormat(getString(R.string.TimeForm))
        val date = Date(ts)
        res = simpleDateFormat.format(date)
        return res
    }
    /**
     * 计算达到秒数目标的时间
     * @param needTime  秒数
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun AimTime2(needTime: Int?): String {
        val nowTime = Date()
        val time = (needTime?.times(1000))?.toLong() //recoveryTime*60秒
        val ts = System.currentTimeMillis() + time!!
        val res: String
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        val simpleDateFormat = SimpleDateFormat(getString(R.string.TimeForm4))
        val date = Date(ts)
        res = simpleDateFormat.format(date)
        return res
    }

    /**
     * 秒数转化为时分
     * @param time
     * @return
     */
    fun NeededTime(time: Int): String {
        //需要时间计算
        val remainHours = time / 3600
        val remainminutes = time % 3600 / 60
        return remainHours.toString() + getString(R.string.Hours) +
                remainminutes + getString(R.string.Minutes)
    }

    /**
     * 时间转年月日
     * @param days
     * @return
     */
    fun TravelTime(days: Int): String {
        //需要时间计算
        val TravelYears = days / 365
        val TravelMonths = (days % 365 / 30.5).toInt()
        val TravelDays = (days % 365 % 30.5).toInt()
        return TravelYears.toString() + getString(R.string.years) +
                TravelMonths + getString(R.string.months) +
                TravelDays + getString(R.string.day)

    }
}