package dev.yamil.coronavirusapi

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository
import org.springframework.stereotype.Repository

@Repository
interface CovidRepository : DatastoreRepository<CovidDataSet, String> {

    fun findAllByOrderByDateDesc(): List<CovidDataSet>

    fun findByDateEqualsOrderByDateDesc(date: String): List<CovidDataSet>

    fun existsByDateEquals(date: String): Boolean

}