package org.byters.bctodo.controller.data.device

import org.byters.bctodo.model.FileEnum
import kotlin.reflect.KClass

interface ICacheStorage {
    fun <T : Any> readFile(fileEnum: FileEnum, className: KClass<T>): T?
    fun writeData(data: Any, fileEnum: FileEnum)


}
