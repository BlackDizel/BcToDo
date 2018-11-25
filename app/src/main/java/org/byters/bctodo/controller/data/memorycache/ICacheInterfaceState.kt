package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.controller.data.memorycache.callback.ICacheInterfaceStateListener
import org.byters.bctodo.model.StyleEnum

interface ICacheInterfaceState {
    fun setStyleNext()

    fun setListener(listener: ICacheInterfaceStateListener)
    fun getStyle(): StyleEnum
}
