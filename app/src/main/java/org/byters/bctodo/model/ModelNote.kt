package org.byters.bctodo.model

import java.io.Serializable

data class ModelNote(val id:String, var title: String?, var body: String?) : Serializable {
}
