package dev.yamil.coronavirusapi

import org.jsoup.nodes.Element

fun Element.textAsNumber(): Long {
    val text = text()
    val canConvert = text.replace("[,.]".toRegex(), "")
    return canConvert.toLong()
}