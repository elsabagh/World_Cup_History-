package com.example.worldcup.data

import com.example.worldcup.data.domain.Match

object DataManger {
    private val matchesList = mutableListOf<Match>()
    val matches: List<Match>
        get() = matchesList.reversed()

    private var matchIndex = 0

    fun addMatch(match: Match){
        matchesList.add(match)
    }

    fun addMatchIndex(match: Match, index: Int){
        matchesList.add(index, match)
    }

    fun getCurrentMatch(): Match = matchesList[matchIndex]

    fun getNextMatch(): Match{
        matchIndex++
        if (matchIndex == matchesList.size){
            matchIndex = 0
        }
        return matchesList[matchIndex]
    }

    fun getPreviousMatch(): Match{
        matchIndex--
        if (matchIndex == -1){
            matchIndex = matchesList.size - 1
        }
        return matchesList[matchIndex]
    }
}