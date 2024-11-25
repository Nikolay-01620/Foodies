package com.foodies.catalog_feature.model

import androidx.annotation.DrawableRes
import com.foodies.core_ui.R

data class TagInAppCatalog(val id: Int, val name: String, @DrawableRes val imageRes: Int) {
    companion object {

        private val tagsList = mutableListOf<TagInAppCatalog>()

        fun setList(tags: List<TagCatalog>) {
            val list = listOf(
                TagInAppCatalog(0, "Со скидкой", R.drawable.sale_tag),
                TagInAppCatalog(1, tags[0].name, R.drawable.new_tag),
                TagInAppCatalog(2, tags[1].name, R.drawable.vegan_tag),
                TagInAppCatalog(3, tags[2].name, R.drawable.hit_tag),
                TagInAppCatalog(4, tags[3].name, R.drawable.hot_tag),
                TagInAppCatalog(5, tags[4].name, R.drawable.express_tag)
            )
            tagsList.clear()
            tagsList.addAll(list)
        }

        fun getList(): List<TagInAppCatalog> {
            return tagsList
        }

        fun getIconId(tagId: Int): Int {
            println(tagId)
            return tagsList.first { it.id == tagId }.imageRes
        }
    }
}
