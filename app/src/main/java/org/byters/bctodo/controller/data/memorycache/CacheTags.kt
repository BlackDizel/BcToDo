package org.byters.bctodo.controller.data.memorycache

import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.BuildConfig
import org.byters.bctodo.controller.data.util.opt
import org.byters.bctodo.model.ModelTag
import org.byters.bctodo.model.ModelTagsCollection
import java.util.*

class CacheTags(app: ApplicationToDo) : ICacheTags {

    var data: ModelTagsCollection? = null

    init {

        if (BuildConfig.DEBUG)
        {
            data = ModelTagsCollection(ArrayList())
            data!!.tags.add(ModelTag(UUID.randomUUID().toString(), "todo"))
            data!!.tags.add(ModelTag(UUID.randomUUID().toString(), "work"))
            data!!.tags.add(ModelTag(UUID.randomUUID().toString(), "other"))
        }
    }

    override fun getItemsNum(): Int = data?.tags?.size ?: 0

    override fun getItemTitle(position: Int): String = data?.tags?.opt(position)?.title ?: ""

}
