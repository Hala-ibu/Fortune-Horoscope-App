package com.example.fortune_horoscope.data.remote.api
import com.example.fortune_horoscope.data.remote.dto.HoroscopeDto
import com.example.fortune_horoscope.data.remote.dto.HoroscopeRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HoroscopeApiService {
    @GET("posts")
    suspend fun getHoroscopes(): List<HoroscopeDto>

    @GET("posts/{id}")
    suspend fun getHoroscope(@Path("id") id: Long): HoroscopeDto

    @POST("posts")
    suspend fun createHoroscope(@Body request: HoroscopeRequestDto): HoroscopeDto

    @PUT("posts/{id}")
    suspend fun updateHoroscope(
        @Path("id") id: Long,
        @Body request: HoroscopeRequestDto
    ): HoroscopeDto

    @DELETE("posts/{id}")
    suspend fun deleteHoroscope(@Path("id") id: Long)
}
