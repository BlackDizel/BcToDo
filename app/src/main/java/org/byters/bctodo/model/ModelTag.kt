package org.byters.bctodo.model

import java.util.*

data class ModelTag(val id: String = UUID.randomUUID().toString(), val title: String, var isSelected: Boolean = true) {

}
