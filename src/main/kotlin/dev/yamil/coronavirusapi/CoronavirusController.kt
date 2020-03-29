package dev.yamil.coronavirusapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/coronavirus")
class CoronavirusController @Autowired constructor(private val covidRepository: CovidRepository,
                                                   private val minsalDataScraper: MinsalDataScraper) {

    @GetMapping
    fun fetchAll(): ResponseEntity<List<CovidDataSet>> {
        val list = covidRepository.findAllByOrderByDateDesc()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @GetMapping("/today")
    fun fetchAllData(): ResponseEntity<List<CovidDataSet>> {
        var lastRecords = LocalDate.now()
        val hasRecords = covidRepository.existsByDateEquals(lastRecords.toString())

        if (!hasRecords) {
            lastRecords = lastRecords.minusDays(1)
        }

        val list = covidRepository.findByDateEqualsOrderByDateDesc(lastRecords.toString())
        return ResponseEntity(list, HttpStatus.OK)
    }

}