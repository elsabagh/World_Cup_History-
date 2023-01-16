package com.example.worldcup.ui

import com.example.worldcup.data.domain.Match

interface MatchInteractionListener {
    fun onClickItem(match: Match)
    fun onClickTeamName(name: String)
    fun onClickDeleteItem(index: Int)

}