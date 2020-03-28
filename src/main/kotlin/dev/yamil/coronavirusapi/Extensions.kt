package dev.yamil.coronavirusapi

import org.jsoup.nodes.Element

fun Element.textNumeric(): String {
    val text = text()
    return text.replace("[,.]".toRegex(), "")
}