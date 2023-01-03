package com.example.worldcup.ui

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.example.worldcup.data.DataManger
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityMainBinding
import com.example.worldcup.util.Constants
import com.example.worldcup.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : BaseActivity<ActivityMainBinding>() , MatchInteractionListener {

    override val LOG_TAG: String = "MAIN_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    lateinit var adabter: matchAdabter

    override fun setup() {
        parseFile()
        adabter = matchAdabter(DataManger.matches.reversed(), this)
        binding.recyclerViewMatches.adapter = adabter
    }


    override fun addCallbacks() {

    }


    private fun parseFile() {
        val inputStream = assets.open("worldcup.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            val currentMatch = parser.parse(it)
            DataManger.addMatch(currentMatch)
        }
    }

    override fun onClickItem(match: Match) {
        val myIntent = Intent(this, DetailsActivity::class.java)
        myIntent.putExtra(Constants.key.MATCH, match)
        startActivity(myIntent)
    }

    override fun onClickTeamName(name: String) {
        Toast.makeText(applicationContext, name,Toast.LENGTH_SHORT).show()
    }

}
