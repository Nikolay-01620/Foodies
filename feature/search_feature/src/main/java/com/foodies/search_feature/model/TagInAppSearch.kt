package com.foodies.search_feature.model

import androidx.annotation.DrawableRes
import com.foodies.core_ui.R

data class TagInAppSearch(val id: Int, val name: String, @DrawableRes val imageRes: Int) {
    companion object {

        private val tagsList = mutableListOf<TagInAppSearch>()

        fun setList(tags: List<TagSearch>) {
            val list = listOf(
                TagInAppSearch(0, "Со скидкой", R.drawable.sale_tag),
                TagInAppSearch(1, tags[0].name, R.drawable.new_tag),
                TagInAppSearch(2, tags[1].name, R.drawable.vegan_tag),
                TagInAppSearch(3, tags[2].name, R.drawable.hit_tag),
                TagInAppSearch(4, tags[3].name, R.drawable.hot_tag),
                TagInAppSearch(5, tags[4].name, R.drawable.express_tag)
            )
            tagsList.clear()
            tagsList.addAll(list)
        }

        fun getList(): List<TagInAppSearch> {
            return tagsList
        }

        fun getIconId(tagId: Int): Int {
            println(tagId)
            return tagsList.first { it.id == tagId }.imageRes
        }
    }
}
