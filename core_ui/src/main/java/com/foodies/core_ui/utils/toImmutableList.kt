package com.foodies.core_ui.utils

import java.util.Collections

fun <T> List<T>.toImmutableList(): List<T> {
    return Collections.unmodifiableList(toMutableList())
}
