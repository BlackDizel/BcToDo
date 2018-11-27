package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.model.ThemeEnum

interface ICacheInterfaceState {
    fun setStyleNext()

    fun addListener(listener: ICacheInterfaceStateListener)
    fun getStyle(): StyleEnum
    fun setThemeNext()
    fun getTheme(): ThemeEnum
    fun setFontNext()
    fun getFont(): FontEnum
}
