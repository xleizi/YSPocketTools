package com.lei123.YSPocketTools.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL

internal object FileUtils {
    /**
     *
     * 判断指定目录的文件夹是否存在，如果不存在则需要创建新的文件夹
     *
     * @param fileName 指定目录
     *
     * @return 返回创建结果 TRUE or FALSE
     */
    fun fileIsExist(fileName: String?): Boolean {
        //传入指定的路径，然后判断路径是否存在
        val file = File(fileName)
        return if (file.exists()) true else {

            //file.mkdirs() 创建文件夹的意思
            file.mkdirs()
        }
    }
}

fun UrlToName (url:String): String? {
    try {
        val split = URL(url).path.split("/")
        if (split.isNotEmpty()) {
            //val file =application.getExternalFilesDir(null).toString()+split[split.size - 1]
            val name = split[split.size - 1]
            return name
        }
    }catch (e:Exception){
        return null
    }
    return null
}

fun getSavePath(text: String): String{
    val TargetPath: String = application.getExternalFilesDir(null).toString() + File.separator + text + File.separator
    if (!File(TargetPath).exists()){
        File(TargetPath).mkdirs()
    }
    return TargetPath
}

fun urlToPath(url: String): File {
    var name = UrlToName(url)
    val TargetPath = getSavePath("images")
    val file =  File(TargetPath.plus(name))
    return file
}

fun imageUrlToBitmap(url: String): Bitmap? {
    val file = urlToPath(url)
    val uri = FileProvider.getUriForFile(application, "com.lei123.YSPocketTools.fileprovider", file)
    //Log.i("imageUrlToBitmap", uri.toString())
    try {
        val bitmapImage = uri.toImage()
        return bitmapImage
    }catch (e:Exception){
        return null
    }
}

fun imageUrlToFile(url: String): File? {
    val split = URL(url).path.split("/")
    val dir = File("${application.cacheDir.absolutePath}${File.separator}files${File.separator}img${File.separator}")
    Log.i("imageUrlToFile", dir.toString())
    if (!dir.exists()) {
        Log.i("imageUrlToFile", "!dir.exists()")
        dir.mkdirs()
    }
    if (split.isNotEmpty()) {
        val file =
            File("${application.cacheDir.absolutePath}${File.separator}files${File.separator}img${File.separator}${split[split.size - 1]}")
        return file
    }
    return null
}

fun uriToFile(url: String): File {
    val dir =
        File("${application.cacheDir.absolutePath}${File.separator}files${File.separator}img${File.separator}")
    if (!dir.exists()) {
        dir.mkdirs()
    }
    val file =
        File(
            "${application.cacheDir.absolutePath}${File.separator}files${File.separator}img${File.separator}${
                MD5(url)
            }.png"
        )
    return file
}

fun fileToBitmap(file: File?): Bitmap? {
    file?: return null
    val uri = FileProvider.getUriForFile(application, "com.lei123.YSPocketTools.fileprovider", file)
        ?: return null
    return uri.toImage()
}

fun fileToBitmap(file: String?): Bitmap? {
    if (TextUtils.isEmpty(file)) {
        return null
    }
    try {
        return fileToBitmap(File(file))
    } catch (e: Exception) {
        return null
    }
}



fun Uri.toImage(): Bitmap? {
    var input: InputStream? = null
    var inputStream: InputStream? = null
    try {
        //根据uri获取图片的流
        inputStream = application.contentResolver.openInputStream(this)
        val options = BitmapFactory.Options()
        //options的in系列的设置了，injustdecodebouond只解析图片的大小，而不加载到内存中去
        options.inJustDecodeBounds = true
        //1.如果通过options.outHeight获取图片的宽高，就必须通过decodestream解析同options赋值
        //否则options.outheight获取不到宽高
        BitmapFactory.decodeStream(inputStream, null, options)
        //2.通过 btm.getHeight()获取图片的宽高就不需要1的解析，我这里采取第一张方式
//            Bitmap btm = BitmapFactory.decodeStream(inputStream);
        //以屏幕的宽高进行压缩
        val displayMetrics: DisplayMetrics = application.getResources().getDisplayMetrics()
        val heightPixels = displayMetrics.heightPixels
        val widthPixels = displayMetrics.widthPixels
        //获取图片的宽高
        val outHeight = options.outHeight
        val outWidth = options.outWidth
        //heightPixels就是要压缩后的图片高度，宽度也一样
        val a = Math.ceil((outHeight / heightPixels.toFloat()).toDouble()).toInt()
        val b = Math.ceil((outWidth / widthPixels.toFloat()).toDouble()).toInt()
        //比例计算,一般是图片比较大的情况下进行压缩
        val max = Math.max(a, b)
        if (max > 1) {
            options.inSampleSize = max
        }
        //解析到内存中去
        options.inJustDecodeBounds = false
        //            根据uri重新获取流，inputstream在解析中发生改变了
        input = application.contentResolver.openInputStream(this)
        return BitmapFactory.decodeStream(input, null, options)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            inputStream?.close()
            input?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return null
}

