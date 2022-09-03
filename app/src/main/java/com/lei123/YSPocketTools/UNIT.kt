package com.lei123.YSPocketTools

import android.content.Intent
import android.os.Message
import com.lei123.YSPocketTools.http.HTTPs.saveAccount.saveAcountinfo
import com.lei123.YSPocketTools.utils.*
import okhttp3.Call
import okhttp3.FormBody
import okhttp3.Request

object UNIT {
    fun saveUID(uid: String, cookies: String) {
        var a = 0
        for (i in 0..9){
            if (loadString("uid".plus(i.toString())) == "123456789" ||
                uid == loadString("uid".plus(i.toString())) ||
                loadString("cookie".plus(i.toString())) == "123456789") {
                saveString("uid".plus(i.toString()), uid)
                saveString("cookie".plus(i.toString()), cookies)
                saveAcountinfo(uid)
                break
            }
            a += 1
        }
        if (a == 10){
            getString(R.string.saveFailed).toast()
        }
    }



    fun returnHome() {
        //返回桌面
        val i = Intent(Intent.ACTION_MAIN)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        i.addCategory(Intent.CATEGORY_HOME)
        application.startActivity(i)
    }

    fun getLineLightProgressBar(current: String, max: String): Int {
        val percentage = ((current.toFloat() / max.toFloat()) * 100).toInt()
        //Log.i("percentage", percentage.toString())
        val image = when (percentage) {
            0 -> R.drawable.line_light_pb0
            1 -> R.drawable.line_light_pb1
            2 -> R.drawable.line_light_pb2
            3 -> R.drawable.line_light_pb3
            4 -> R.drawable.line_light_pb4
            5 -> R.drawable.line_light_pb5
            6 -> R.drawable.line_light_pb6
            7 -> R.drawable.line_light_pb7
            8 -> R.drawable.line_light_pb8
            9 -> R.drawable.line_light_pb9
            10 -> R.drawable.line_light_pb10
            11 -> R.drawable.line_light_pb11
            12 -> R.drawable.line_light_pb12
            13 -> R.drawable.line_light_pb13
            14 -> R.drawable.line_light_pb14
            15 -> R.drawable.line_light_pb15
            16 -> R.drawable.line_light_pb16
            17 -> R.drawable.line_light_pb17
            18 -> R.drawable.line_light_pb18
            19 -> R.drawable.line_light_pb19
            20 -> R.drawable.line_light_pb20
            21 -> R.drawable.line_light_pb21
            22 -> R.drawable.line_light_pb22
            23 -> R.drawable.line_light_pb23
            24 -> R.drawable.line_light_pb24
            25 -> R.drawable.line_light_pb25
            26 -> R.drawable.line_light_pb26
            27 -> R.drawable.line_light_pb27
            28 -> R.drawable.line_light_pb28
            29 -> R.drawable.line_light_pb29
            30 -> R.drawable.line_light_pb30
            31 -> R.drawable.line_light_pb31
            32 -> R.drawable.line_light_pb32
            33 -> R.drawable.line_light_pb33
            34 -> R.drawable.line_light_pb34
            35 -> R.drawable.line_light_pb35
            36 -> R.drawable.line_light_pb36
            37 -> R.drawable.line_light_pb37
            38 -> R.drawable.line_light_pb38
            39 -> R.drawable.line_light_pb39
            40 -> R.drawable.line_light_pb40
            41 -> R.drawable.line_light_pb41
            42 -> R.drawable.line_light_pb42
            43 -> R.drawable.line_light_pb43
            44 -> R.drawable.line_light_pb44
            45 -> R.drawable.line_light_pb45
            46 -> R.drawable.line_light_pb46
            47 -> R.drawable.line_light_pb47
            48 -> R.drawable.line_light_pb48
            49 -> R.drawable.line_light_pb49
            50 -> R.drawable.line_light_pb50
            51 -> R.drawable.line_light_pb51
            52 -> R.drawable.line_light_pb52
            53 -> R.drawable.line_light_pb53
            54 -> R.drawable.line_light_pb54
            55 -> R.drawable.line_light_pb55
            56 -> R.drawable.line_light_pb56
            57 -> R.drawable.line_light_pb57
            58 -> R.drawable.line_light_pb58
            59 -> R.drawable.line_light_pb59
            60 -> R.drawable.line_light_pb60
            61 -> R.drawable.line_light_pb61
            62 -> R.drawable.line_light_pb62
            63 -> R.drawable.line_light_pb63
            64 -> R.drawable.line_light_pb64
            65 -> R.drawable.line_light_pb65
            66 -> R.drawable.line_light_pb66
            67 -> R.drawable.line_light_pb67
            68 -> R.drawable.line_light_pb68
            69 -> R.drawable.line_light_pb69
            70 -> R.drawable.line_light_pb70
            71 -> R.drawable.line_light_pb71
            72 -> R.drawable.line_light_pb72
            73 -> R.drawable.line_light_pb73
            74 -> R.drawable.line_light_pb74
            75 -> R.drawable.line_light_pb75
            76 -> R.drawable.line_light_pb76
            77 -> R.drawable.line_light_pb77
            78 -> R.drawable.line_light_pb78
            79 -> R.drawable.line_light_pb79
            80 -> R.drawable.line_light_pb80
            81 -> R.drawable.line_light_pb81
            82 -> R.drawable.line_light_pb82
            83 -> R.drawable.line_light_pb83
            84 -> R.drawable.line_light_pb84
            85 -> R.drawable.line_light_pb85
            86 -> R.drawable.line_light_pb86
            87 -> R.drawable.line_light_pb87
            88 -> R.drawable.line_light_pb88
            89 -> R.drawable.line_light_pb89
            90 -> R.drawable.line_light_pb90
            91 -> R.drawable.line_light_pb91
            92 -> R.drawable.line_light_pb92
            93 -> R.drawable.line_light_pb93
            94 -> R.drawable.line_light_pb94
            95 -> R.drawable.line_light_pb95
            96 -> R.drawable.line_light_pb96
            97 -> R.drawable.line_light_pb97
            98 -> R.drawable.line_light_pb98
            99 -> R.drawable.line_light_pb99
            100 -> R.drawable.line_light_pb100
            else -> R.drawable.line_light_pb0
        }
        return image
    }
    fun getLineDarkProgressBar(current: String, max: String): Int {
        val percentage = ((current.toFloat() / max.toFloat()) * 100).toInt()
        //Log.i("percentage", percentage.toString())
        val image = when (percentage) {
            0 -> R.drawable.line_dark_pb0
            1 -> R.drawable.line_dark_pb1
            2 -> R.drawable.line_dark_pb2
            3 -> R.drawable.line_dark_pb3
            4 -> R.drawable.line_dark_pb4
            5 -> R.drawable.line_dark_pb5
            6 -> R.drawable.line_dark_pb6
            7 -> R.drawable.line_dark_pb7
            8 -> R.drawable.line_dark_pb8
            9 -> R.drawable.line_dark_pb9
            10 -> R.drawable.line_dark_pb10
            11 -> R.drawable.line_dark_pb11
            12 -> R.drawable.line_dark_pb12
            13 -> R.drawable.line_dark_pb13
            14 -> R.drawable.line_dark_pb14
            15 -> R.drawable.line_dark_pb15
            16 -> R.drawable.line_dark_pb16
            17 -> R.drawable.line_dark_pb17
            18 -> R.drawable.line_dark_pb18
            19 -> R.drawable.line_dark_pb19
            20 -> R.drawable.line_dark_pb20
            21 -> R.drawable.line_dark_pb21
            22 -> R.drawable.line_dark_pb22
            23 -> R.drawable.line_dark_pb23
            24 -> R.drawable.line_dark_pb24
            25 -> R.drawable.line_dark_pb25
            26 -> R.drawable.line_dark_pb26
            27 -> R.drawable.line_dark_pb27
            28 -> R.drawable.line_dark_pb28
            29 -> R.drawable.line_dark_pb29
            30 -> R.drawable.line_dark_pb30
            31 -> R.drawable.line_dark_pb31
            32 -> R.drawable.line_dark_pb32
            33 -> R.drawable.line_dark_pb33
            34 -> R.drawable.line_dark_pb34
            35 -> R.drawable.line_dark_pb35
            36 -> R.drawable.line_dark_pb36
            37 -> R.drawable.line_dark_pb37
            38 -> R.drawable.line_dark_pb38
            39 -> R.drawable.line_dark_pb39
            40 -> R.drawable.line_dark_pb40
            41 -> R.drawable.line_dark_pb41
            42 -> R.drawable.line_dark_pb42
            43 -> R.drawable.line_dark_pb43
            44 -> R.drawable.line_dark_pb44
            45 -> R.drawable.line_dark_pb45
            46 -> R.drawable.line_dark_pb46
            47 -> R.drawable.line_dark_pb47
            48 -> R.drawable.line_dark_pb48
            49 -> R.drawable.line_dark_pb49
            50 -> R.drawable.line_dark_pb49
            60 -> R.drawable.line_dark_pb60
            61 -> R.drawable.line_dark_pb61
            62 -> R.drawable.line_dark_pb62
            63 -> R.drawable.line_dark_pb63
            64 -> R.drawable.line_dark_pb64
            65 -> R.drawable.line_dark_pb65
            66 -> R.drawable.line_dark_pb66
            67 -> R.drawable.line_dark_pb67
            68 -> R.drawable.line_dark_pb68
            69 -> R.drawable.line_dark_pb69
            70 -> R.drawable.line_dark_pb70
            71 -> R.drawable.line_dark_pb71
            72 -> R.drawable.line_dark_pb72
            73 -> R.drawable.line_dark_pb73
            74 -> R.drawable.line_dark_pb74
            75 -> R.drawable.line_dark_pb75
            76 -> R.drawable.line_dark_pb76
            77 -> R.drawable.line_dark_pb77
            78 -> R.drawable.line_dark_pb78
            79 -> R.drawable.line_dark_pb79
            80 -> R.drawable.line_dark_pb80
            81 -> R.drawable.line_dark_pb81
            82 -> R.drawable.line_dark_pb82
            83 -> R.drawable.line_dark_pb83
            84 -> R.drawable.line_dark_pb84
            85 -> R.drawable.line_dark_pb85
            86 -> R.drawable.line_dark_pb86
            87 -> R.drawable.line_dark_pb87
            88 -> R.drawable.line_dark_pb88
            89 -> R.drawable.line_dark_pb89
            90 -> R.drawable.line_dark_pb90
            91 -> R.drawable.line_dark_pb91
            92 -> R.drawable.line_dark_pb92
            93 -> R.drawable.line_dark_pb93
            94 -> R.drawable.line_dark_pb94
            95 -> R.drawable.line_dark_pb95
            96 -> R.drawable.line_dark_pb96
            97 -> R.drawable.line_dark_pb97
            98 -> R.drawable.line_dark_pb98
            99 -> R.drawable.line_dark_pb99
            100 -> R.drawable.line_dark_pb100
            else -> R.drawable.line_dark_pb0
        }
        return image
    }


    //获取颜色为 如： #fffff 类型
    fun toHexEncoding(color: androidx.compose.ui.graphics.Color): String {
        var R: String
        var G: String
        var B: String
        val sb = StringBuffer()
        R = Integer.toHexString(color.blue.toInt())
        G = Integer.toHexString(color.green.toInt())
        B = Integer.toHexString(color.red.toInt())
        //判断获取到的R,G,B值的长度 如果长度等于1 给R,G,B值的前边添0
        R = if (R.length == 1) "0$R" else R
        G = if (G.length == 1) "0$G" else G
        B = if (B.length == 1) "0$B" else B
        sb.append("#") //0x也可以
        sb.append(R)
        sb.append(G)
        sb.append(B)
        return sb.toString()
    }


}