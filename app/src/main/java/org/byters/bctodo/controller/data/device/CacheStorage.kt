package org.byters.bctodo.controller.data.device

import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.BuildConfig
import org.byters.bctodo.R
import org.byters.bctodo.model.FileEnum
import org.byters.bctodo.view.utils.IHelperPopup
import java.io.BufferedReader
import java.io.File
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.reflect.KClass


class CacheStorage(app: ApplicationToDo) : ICacheStorage {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var helperPopup: IHelperPopup

    @Inject
    lateinit var refContext: WeakReference<Context>

    init {
        app.component.inject(this)
    }

    override fun <T : Any> readFile(fileEnum: FileEnum, className: KClass<T>): T? {
        val file = File(getAppFolder(), fileEnum.file)
        if (!file.exists()) return null

        val bufferedReader: BufferedReader = file.bufferedReader()

        val data: T
        try {
            data = gson.fromJson(JsonReader(bufferedReader), className.java)
        } catch (e: Exception) {
            createBackup(file)
            return null
        }

        return data
    }

    private fun createBackup(file: File) {
        val filenameOut = file.name + SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Date())
        val fileOut = File(getAppFolder(), filenameOut)
        file.renameTo(fileOut)
        showMessageFileError(file.absolutePath, filenameOut)
    }

    private fun showMessageFileError(file: String, filenameOut: String) {
        val format = refContext.get()?.resources?.getString(R.string.error_json_parse_format)
        if (format == null) return
        helperPopup.showToast(String.format(format, file, filenameOut))
    }

    override fun writeData(data: Any, fileEnum: FileEnum) {

        val folder = File(getAppFolder())

        if (!folder.exists() && !folder.mkdirs())
            return

        val file = File(folder, fileEnum.file)
        file.writeText(gson.toJson(data))
    }

    fun getAppFolder(): String {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + File.separator + BuildConfig.APPLICATION_ID
    }

}
