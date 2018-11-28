package org.byters.bctodo.controller.data.util

import android.text.TextUtils
import org.byters.bctodo.model.ModelNote

fun <T> ArrayList<T>.opt(position: Int): T? =
    if (position < 0 || this.size <= position) null else this.get(position)

fun <T> List<T>.opt(position: Int): T? =
    if (position < 0 || this.size <= position) null else this.get(position)

fun ModelNote.getTitleSingleLine(): String {
    var result = ""

    if (!TextUtils.isEmpty(this.title))
        result += this.title + " "
    if (!TextUtils.isEmpty(this.body))
        result += this.body
    return result
}