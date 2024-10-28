package com.example.frontend.utils

interface HasName {
    val name: String
}

fun <T : HasName> selectOption(options: List<T>, name: String?, selectAction: (T?) -> Unit) {
    val selected = options.firstOrNull { it.name == name }
    selectAction(selected)
}