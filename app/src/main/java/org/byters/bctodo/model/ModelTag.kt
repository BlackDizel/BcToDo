package org.byters.bctodo.model

import java.util.*

data class ModelTag(val id: String = UUID.randomUUID().toString(), var title: String, var isSelected: Boolean = true) {

}
