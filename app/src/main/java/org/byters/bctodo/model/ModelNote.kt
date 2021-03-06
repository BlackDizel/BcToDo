package org.byters.bctodo.model

import java.io.Serializable

data class ModelNote(
    val id: String,
    var title: String?,
    var body: String?,
    var date: Long?,
    var tags: ArrayList<String>? = null,
    var folderId: String? = null,
    var colorLabel: Int? = null
) : Serializable
