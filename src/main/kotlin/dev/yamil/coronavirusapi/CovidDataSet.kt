package dev.yamil.coronavirusapi

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity

@Entity
data class CovidDataSet(val region: String,
                        val newCases: String,
                        val totalCases: String,
                        val deaths: String)