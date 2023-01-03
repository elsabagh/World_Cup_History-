package com.example.worldcup.ui

import android.view.LayoutInflater
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityDetailsBinding
import com.example.worldcup.util.Constants

class DetailsActivity :BaseActivity<ActivityDetailsBinding>() {
    override val LOG_TAG: String = "Details_Activity"
    override val bindingInflater: (LayoutInflater) -> ActivityDetailsBinding = ActivityDetailsBinding::inflate

    override fun setup() {
        val match:Match? = intent.getParcelableExtra(Constants.key.MATCH) as Match?
        match?.let { bindMatch(it) }
    }

    private fun bindMatch(match: Match) {
        binding.apply {
            textHomeName.text = match.homeTeamName
            textAwayName.text = match.awayTeamName
            textHomeGoals.text = match.homeTeamGoals.toString()
            textAwayGoals.text = match.awayTeamGoals.toString()
            textYear.text = match.year.toString()
        }
    }

    override fun addCallbacks() {

    }

}