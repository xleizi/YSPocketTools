package com.lei123.YSPocketTools.utils

object getWeaponName {
    fun GetWeaponName(): Map<String, String> {
        val avatarname: MutableMap<String, String> = HashMap()
        avatarname["Albedo"] = "阿贝多"
        return avatarname
    }

    fun GetWeaponName(name:String):String{
        val simpleName = when(name){
            "笼钓瓶一心" -> "妖刀"
            "波乱月白经津" -> "波波剑"
            "证誓之明瞳" -> "明瞳"
            "神乐之真意" -> "神乐"
            "赤角石溃杵" -> "赤角"
            "辰砂之纺锤" -> "纺锤"
            "松籁响起之时" -> "松籁"
            "苍古自由之誓" -> "苍古"
            "终末嗟叹之诗" -> "终末"
            "薙草之稻光" -> "薙刀"
            "飞雷之弦振" -> "飞雷"
            "雾切之回光" -> "雾切"
            "桂木斩长正" -> "桂木"
            "喜多院十文字" -> "十文字"
            "天目影打刀" -> "天目"
            "阿莫斯之弓" -> "阿莫斯"
            "宗室秘法录" -> "宗室"
            "万国诸海图谱" -> "诸海"
            "雪葬的星银" -> "雪山剑"
            "暗巷的酒与诗" -> "酒与诗"
            "幽夜华尔兹" -> "幽夜"
            "嘟嘟可故事集" -> "嘟嘟可"
            "沐浴龙血的剑" -> "龙血剑"
            "讨龙英杰谭" -> "讨龙"
            "神射手之誓" -> "神射手"
            "飞天大御剑" -> "大御剑"
            "异世界行记" -> "异世界"
            "口袋魔导书" -> "魔导书"
            "历练的猎弓" -> "历练弓"
            else -> name
        }
        return simpleName
    }
}