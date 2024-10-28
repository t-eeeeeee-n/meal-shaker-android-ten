package com.example.frontend.data.api

import com.example.frontend.data.models.Genre
import com.example.frontend.data.models.Area
import com.example.frontend.data.models.SearchResponse
import com.example.frontend.data.models.Shop
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("hotpepper/genre")
    suspend fun getGenres(): List<Genre>

    @GET("hotpepper/large_service_area")
    suspend fun getLargeServiceAreas(): List<Area>

    @GET("hotpepper/large_area")
    suspend fun getLargeAreas(@Query("region_code") regionCode: String): List<Area>

    @GET("hotpepper/middle_area")
    suspend fun getMiddleAreas(@Query("large_area_code") regionCode: String): List<Area>

    @GET("hotpepper/small_area")
    suspend fun getSmallAreas(@Query("middle_area_code") regionCode: String): List<Area>

    @GET("hotpepper/location/search/one")
    suspend fun searchByLocation(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("genre") genre: String?,
        @Query("range") range: String?
    ): SearchResponse

    @GET("hotpepper/search/one")
    suspend fun searchByArea(
        @Query("large_service_area") largeServiceArea: String,
        @Query("large_area") largeArea: String?,
        @Query("middle_area") middleArea: String?,
        @Query("small_area") smallArea: String?,
        @Query("genre") genre: String?
    ): SearchResponse
}