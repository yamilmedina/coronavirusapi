package dev.yamil.coronavirusapi

import org.jsoup.Jsoup
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MinsalDataScraper @Autowired constructor() {

    private val logger: Logger by lazy { LoggerFactory.getLogger(this::class.java) }

    fun fetchNewData(): List<CovidDataSet> {
        val response = Jsoup.connect("https://www.minsal.cl/nuevo-coronavirus-2019-ncov/casos-confirmados-en-chile-covid-19/").get()

        val data: MutableList<CovidDataSet> = mutableListOf()
        for ((index, it) in response.select(CONTENT_TABLE).select(TABLE_WITH_DATA).first().select(ROW_DATA).withIndex()) {
            if (index > 2) {
                data.add(CovidDataSet(it.children()[0].text(), it.children()[1].text(), it.children()[2].text()))
            }
        }
        logger.debug("All data -> $data")
        return data
    }

    companion object {
        const val CONTENT_TABLE = "div[class$=contenido]"
        const val TABLE_WITH_DATA = "table"
        const val ROW_DATA = "tr"
    }

}