package com.devloper.lloydfinch.neteasedemo.provider

import android.content.Context
import android.provider.MediaStore
import java.io.File

/**
 * 使用ContentProvider检索本地公共库的视频列表
 */
class AVProvider {

    companion object {
        fun getVideos(context: Context): ArrayList<AVFile> {
            val videos: ArrayList<AVFile> = ArrayList()
            var columns = arrayOf(MediaStore.Video.VideoColumns._ID)
            val resover = context.applicationContext.contentResolver
            val cursor = resover.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null, null, null)
            cursor?.apply {
                while (moveToNext()) {
                    //query id
                    val id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns._ID))
                    val avFile = AVFile(id)

                    //query path
                    val path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA))
                    val file = File(path)
                    val lastModifiedTime = file.lastModified()
                    avFile.lastModifiedTime = lastModifiedTime

                    //query other

                    videos.add(avFile)
                }
            }

            cursor?.close()
            return videos
        }
    }
}