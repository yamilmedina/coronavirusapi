package dev.yamil.coronavirusapi

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository
import org.springframework.stereotype.Repository

@Repository
interface CovidRepository : DatastoreRepository<CovidData, String>