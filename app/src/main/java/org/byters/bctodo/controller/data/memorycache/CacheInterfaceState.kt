package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.FontEnum
import org.byters.bctodo.model.StyleEnum
import org.byters.bctodo.model.ThemeEnum
import java.util.*

class CacheInterfaceState(app: ApplicationToDo) : ICacheInterfaceState {

    private var style: Int = 0
    private var theme: ThemeEnum = ThemeEnum.LIGHT
    private var font: FontEnum = FontEnum.SANS

    private var listeners: WeakHashMap<String, ICacheInterfaceStateListener>? = null

    override fun setStyleNext() {
        this.style += 1
        style = style % 3
        listeners?.values?.forEach { it.onStyleUpdate() }
    }

    override fun addListener(listener: ICacheInterfaceStateListener) {
        if (listeners == null) listeners = WeakHashMap()
        listeners!!.put(listener::class.java.name, listener)
    }

    override fun getStyle(): StyleEnum =
        if (style == 0) StyleEnum.SMALL
        else if (style == 1) StyleEnum.MEDIUM
        else StyleEnum.FULL

    override fun setThemeNext() {
        theme = if (theme == ThemeEnum.LIGHT) ThemeEnum.DARK else ThemeEnum.LIGHT
        listeners?.values?.forEach { it.onThemeUpdate() }
    }

    override fun getTheme(): ThemeEnum = theme

    override fun setFontNext() {
        font = if (font == FontEnum.SANS) FontEnum.SERIF else FontEnum.SANS
        listeners?.values?.forEach { it.onFontUpdate() }
    }

    override fun getFont(): FontEnum = font

}
