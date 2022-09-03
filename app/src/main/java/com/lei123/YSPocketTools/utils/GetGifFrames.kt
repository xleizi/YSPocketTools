package com.lei123.YSPocketTools.utils

fun GetGifFrames(name: String):Int{
    val simpleName = when(name){
        "hutaoao" -> 196
        "nilue" -> 88
        else -> 10
    }
    return simpleName
}