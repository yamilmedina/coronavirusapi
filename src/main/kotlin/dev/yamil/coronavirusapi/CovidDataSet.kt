package dev.yamil.coronavirusapi

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity
import org.springframework.data.annotation.Id
import org.springframework.util.DigestUtils

@Entity(name = "covid_data")
data class CovidDataSet(
        val date: String,
        val region: String,
        val newCases: Long,
        val totalCases: Long,
        val deaths: Long) {

    @get:Id
    var id: String = DigestUtils.md5DigestAsHex((date + region).toByteArray())

}