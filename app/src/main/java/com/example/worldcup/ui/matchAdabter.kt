package com.example.worldcup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.R
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ItemsMatchBinding


class matchAdabter(private var list: List<Match>,private val listener: MatchInteractionListener) :
    RecyclerView.Adapter<matchAdabter.MatchViewHolder>() {

    //لتحويل الitems من الxml الي view عن طريق layoutinflater ثم نحولها الي viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_match, parent, false)
        return MatchViewHolder(view)
    }

    //التحويل يصير في هذه ال fun
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentMatch = list[position]
        holder.binding.apply {
            textHomeName.text = currentMatch.homeTeamName
            textAwayName.text = currentMatch.awayTeamName
            textHomeGoals.text = currentMatch.homeTeamGoals.toString()
            textAwayGoals.text = currentMatch.awayTeamGoals.toString()
            textYear.text = currentMatch.year.toString()
            textHomeName.setOnClickListener {
                listener.onClickTeamName(currentMatch.homeTeamName)
            }
            textAwayName.setOnClickListener {
                listener.onClickTeamName(currentMatch.awayTeamName)
            }
            if (currentMatch.homeTeamGoals > currentMatch.awayTeamGoals) {
                textHomeGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.green
                    )
                )
                textAwayGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.red
                    )
                )

            } else if (currentMatch.homeTeamGoals < currentMatch.awayTeamGoals) {
                textHomeGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.red
                    )
                )
                textAwayGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.green
                    )
                )

            } else {
                textHomeGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.purple_700
                    )
                )
                textAwayGoals.setTextColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.purple_700
                    )
                )
            }
            root.setOnClickListener{listener.onClickItem(currentMatch)}
        }
    }

    fun setData(newList: List<Match>){
        list = newList
        notifyDataSetChanged()
    }

    // calculate size items
    override fun getItemCount() = list.size

    class MatchViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        val binding = ItemsMatchBinding.bind(viewItem)
    }

}