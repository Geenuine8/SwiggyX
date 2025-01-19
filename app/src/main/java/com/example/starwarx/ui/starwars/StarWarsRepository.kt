package com.example.starwarx.ui.starwars

import android.util.Log
import com.example.starwarx.models.MatchData
import com.example.starwarx.models.PlayerData
import com.example.starwarx.network.StarWarApi
import com.example.starwarx.ui.mathesscreen.MatchItemData
import com.example.starwarx.ui.mathesscreen.MatchScreenData
import com.example.starwarx.ui.pointstable.PointItemData
import com.example.starwarx.ui.pointstable.PointsTableData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip

/**
 * @author Yogesh Jangir.
 */

class StarWarsRepository(private val starWarApi: StarWarApi) {

    companion object {
        private const val WIN_SCORE = 3
        private const val DRAW_SCORE = 1
    }

    private val pointMap = hashMapOf<Int, PointItemData>()
    private var matches: MutableList<MatchData> = mutableListOf()
    private var playersMap = mutableMapOf<Int, PlayerData>()

    private fun getPlayers(): Flow<List<PlayerData>> = flow {
        emit(starWarApi.getPlayers())
    }.flowOn(Dispatchers.IO)

    private fun getMatches(): Flow<List<MatchData>> = flow {
        emit(starWarApi.getMatches())
    }.flowOn(Dispatchers.IO)

    private fun calculateData(players: List<PlayerData>, matches: List<MatchData>) {
        this.matches = matches.toMutableList()

        players.map {
            playersMap[it.id] = it
        }
        initPointMap(players)
        updateScoreAndPoint(matches)
    }

    private fun updateScoreAndPoint(matches: List<MatchData>) {
        matches.forEach { match ->
            val player1 = pointMap[match.player1.id]?.apply {
                score?.plus(match.player1.score)
            }
            val player2 = pointMap[match.player2.id]?.apply {
                score?.plus(match.player2.score)
            }
            val score1 = match.player1.score
            val score2 = match.player2.score
            if (score1 > score2) {
                player1?.points = player1?.points?.plus(WIN_SCORE)
            } else if (score2 > score1) {
                player2?.points = player2?.points?.plus(WIN_SCORE)
            } else {
                player1?.points = player1?.points?.plus(DRAW_SCORE)
                player2?.points = player2?.points?.plus(DRAW_SCORE)
            }
        }
    }

    private fun initPointMap(players: List<PlayerData>) {
        pointMap.clear()
        players.forEach { player ->
            pointMap[player.id] = PointItemData(
                id = player.id,
                icon = player.icon,
                name = player.name,
                points = 0,
                score = 0
            )
        }
    }

    fun fetchData() = getPlayers().zip(getMatches()) { playerData, matchData ->
        calculateData(playerData, matchData)
    }.catch {
        Log.d("G8","$it")
    }

    fun getPointTableData() = PointsTableData(
        heading = "Points Table",
        matchList = pointMap.map { it.value }.sortedByDescending { it.points }.sortedByDescending { it.score }
    )

    fun getPlayerMatches(id: Int?): MatchScreenData {
        return MatchScreenData(
            heading = "Matches",
            matchList = matches.filter { it.player1.id == id || it.player2.id == id }.map {
                val player1 = playersMap[it.player1.id]
                val player2 = playersMap[it.player2.id]
                MatchItemData(
                    player1 = player1?.name,
                    player2 = player2?.name,
                    score1 = it.player1.score,
                    score2 = it.player2.score,
                    result = if (it.player1.score == it.player2.score) {
                        null
                    } else if (player1?.id == id) {
                        it.player1.score > it.player2.score
                    } else {
                        it.player1.score < it.player2.score
                    }
                )
            }.reversed()
        )
    }

}