package com.example.starwarx.network
import com.example.starwarx.models.MatchData
import com.example.starwarx.models.PlayerData
import retrofit2.http.GET

/**
 * @author Yogesh Jangir.
 */

interface StarWarApi {

    @GET("b/IKQQ")
    suspend fun getPlayers(): List<PlayerData>

    @GET("b/JNYL")
    suspend fun getMatches(): List<MatchData>
}
