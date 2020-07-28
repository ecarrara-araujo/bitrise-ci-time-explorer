package br.com.ecarrara.data.datasource.rest

import br.com.ecarrara.core.config.BITRISE_APP_SLUG
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BitriseRestService {
    @GET("apps/{app_slug}/builds")
    suspend fun getBuilds(
        @Path("app_slug") bitriseAppSlug: String = BITRISE_APP_SLUG,
        @Query("after") afterDate: Long? = null,
        @Query("before") beforeDate: Long? = null,
        @Query("limit") maxItemsPerPage: Int = 50,
        @Query("next") nextCursor: String = "",
        @Query("sorted_by") sortedBy: String = "created_at"
    ): BitriseResponseDto
}