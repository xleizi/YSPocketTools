package com.lei123.selectcoser

import android.graphics.Bitmap
import android.util.Log
import com.lei123.YSPocketTools.utils.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 *
 * Bitmap 帮助类之一
 *
 */
internal object BitmapUtils {
    /**
     *
     * Save Bitmap
     *
     *
     *
     * @param name file name
     *
     * @param bm  picture to save
     */
    fun saveBitmap(name: String?, bm: Bitmap, TargetPath: String) {
        Log.d("Save Bitmap", "Ready to save picture")

        //指定我们想要存储文件的地址
        //val TargetPath = mContext.getExternalFilesDir(null).toString() + "/images/"
        Log.d("Save Bitmap", "Save Path=$TargetPath")

        //判断指定文件夹的路径是否存在
        if (!FileUtils.fileIsExist(TargetPath)) {
            Log.d("Save Bitmap", "TargetPath isn't exist")
        } else {
            //如果指定文件夹创建成功，那么我们则需要进行图片存储操作
            val saveFile = File(TargetPath, name)
            if (!saveFile.exists()) {
                try {
                    val saveImgOut = FileOutputStream(saveFile)

                    // compress - 压缩的意思
                    bm.compress(Bitmap.CompressFormat.PNG, 100, saveImgOut)

                    //存储完成后需要清除相关的进程
                    saveImgOut.flush()
                    saveImgOut.close()
                    Log.d("Save Bitmap", "The picture is save to your phone!")
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        }
    }
}