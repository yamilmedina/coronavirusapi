package dev.yamil.coronavirusapi

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MinsalDataScraperTest {

    private var scraper: MinsalDataScraper = MinsalDataScraper()

    @Test
    fun `It scrapes correctly data for all regions`() {
        val list = scraper.fetchNewData().map { it.region }
        val diff = REGIONS.subtract(list)

        assertTrue(diff.isEmpty())
    }

    @Test
    fun `It scrapes correctly data type for rows`() {
        val list = scraper.fetchNewData()
        list.forEach { (_, _, newCases, totalCases, deaths) ->
            assertDoesNotThrow { newCases / 1 }
            assertDoesNotThrow { totalCases / 1 }
            assertDoesNotThrow { deaths / 1 }
        }
    }

    companion object {
        val REGIONS = listOf("Arica y Parinacota",
                "Tarapacá",
                "Antofagasta",
                "Atacama",
                "Coquimbo",
                "Valparaíso",
                "Metropolitana",
                "O’Higgins",
                "Maule",
                "Ñuble",
                "Biobío",
                "Araucanía",
                "Los Ríos",
                "Los Lagos",
                "Aysén",
                "Magallanes")
    }
}