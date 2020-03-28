package dev.yamil.coronavirusapi

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity
import org.springframework.data.annotation.Id

@Entity(name = "covid")
data class CovidData(@Id private val date: String,
                     private val covidDataSet: List<CovidDataSet>)