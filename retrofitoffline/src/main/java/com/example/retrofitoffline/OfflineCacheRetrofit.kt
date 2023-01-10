package com.example.retrofitoffline

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 * @author Umer Bilal
 * Created 1/6/2023 at 4:24 PM
 */
class OfflineCacheRetrofit<T> private constructor(
    private var gsonFileNamee: String,
    private var classref: Class<T>
) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        fun <T> getInstance(gsonFileNam: String, classre: Class<T>) =
            OfflineCacheRetrofit(gsonFileNam, classre)
    }

    private fun getJsonFromAssets(): String? {
        val jsonString: String = try {
            val inputStream: InputStream = context.getAssets().open(gsonFileNamee)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun <T> getClassFromAssetGson(classref: Class<T>): T? {

        return try {
            val respo = getJsonFromAssets()
            val newRespon = respo!!.replace("%20", " ")
            Gson().fromJson(newRespon, classref)
        } catch (e: Exception) {
            null
        }
    }

    private fun <T> getGsonToClass(gson: String, classref: Class<T>): T? {

        val newRespon = gson.replace("%20", " ")
        try {
            return Gson().fromJson(newRespon, classref)
        } catch (e: Exception) {
            return null
        }
    }

    fun writeToFile(gson: String) {
        val fos = context.openFileOutput(gsonFileNamee, Context.MODE_PRIVATE)
        fos.write(gson.encodeToByteArray())
        fos.close()
    }

    private fun readFromFile(): String? {
        val inputStream = try {
            context.openFileInput(gsonFileNamee)
        } catch (e: Exception) {
            return null
        }
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.forName("UTF-8"))

    }

    fun tryToGetDataClasses() = try {
        val fromfile =
            readFromFile()
        getGsonToClass(fromfile!!, classref)
    } catch (e: Exception) {
        e.printStackTrace()
        getClassFromAssetGson(
            classref
        )
    }

}