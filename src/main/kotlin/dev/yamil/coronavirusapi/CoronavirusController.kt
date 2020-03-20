package dev.yamil.coronavirusapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coronavirus")
class CoronavirusController @Autowired constructor(private val minsalDataScraper: MinsalDataScraper) {

    @GetMapping("/today")
    fun fetchAllData(): ResponseEntity<List<CovidDataSet>> {
        val todayList = minsalDataScraper.fetchNewData()
        return ResponseEntity(todayList, HttpStatus.OK)
    }

}