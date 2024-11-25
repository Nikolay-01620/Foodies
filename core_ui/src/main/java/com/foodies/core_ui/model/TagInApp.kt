package com.foodies.core_ui.model

import androidx.annotation.DrawableRes
import com.foodies.core_ui.R

data class TagInApp(val id: Int, val name: String, @DrawableRes val imageRes: Int) {
    companion object {

        private val tagsList = mutableListOf<TagInApp>()

        fun setList(tags: List<Tag>) {
            val list = listOf(
                TagInApp(0, "Со скидкой", R.drawable.sale_tag),
                TagInApp(1, tags[0].name, R.drawable.new_tag),
                TagInApp(2, tags[1].name, R.drawable.vegan_tag),
                TagInApp(3, tags[2].name, R.drawable.hit_tag),
                TagInApp(4, tags[3].name, R.drawable.hot_tag),
                TagInApp(5, tags[4].name, R.drawable.express_tag)
            )
            tagsList.clear()
            tagsList.addAll(list)
        }

        fun getList(): List<TagInApp> {
            return tagsList
        }

        fun getIconId(tagId: Int): Int {
            println(tagId)
            return tagsList.first { it.id == tagId }.imageRes
        }
    }
}
