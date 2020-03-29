package dev.yamil.coronavirusapi

import org.jsoup.nodes.Element
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtensionsKtTest {

    @Test
    fun `It transform number with dots to plain Integer like`() {
        val textNumeric = Element("p").text("1.999").textAsNumber()
        assertEquals(1999, textNumeric)
    }

    @Test
    fun `It transform number with comma to plain Integer like`() {
        val textNumeric = Element("p").text("1,999").textAsNumber()
        assertEquals(1999, textNumeric)
    }

    @Test
    fun `It transform number with dots or comma to plain Integer like`() {
        val textNumeric = Element("p").text("1,999.000").textAsNumber()
        assertEquals(1999000, textNumeric)
    }

}