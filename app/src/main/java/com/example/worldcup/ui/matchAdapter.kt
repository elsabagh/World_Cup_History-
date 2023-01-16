package com.example.worldcup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.R
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ItemHeaderMatchesBinding
import com.example.worldcup.databinding.ItemsMatchBinding


class matchAdapter(private var list: List<Match>, private val listener: MatchInteractionListener) :
    RecyclerView.Adapter<matchAdapter.BaseViewHolder>() {

    //لتحويل الitems من الxml الي view عن طريق layoutinflater ثم نحولها الي viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        //لكي نعمل ارجاع لاكثر من item مختلفة وليس لها نفسViewHolder
        when (viewType) {
            VIEW_TYPE_ITEMS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.items_match, parent, false)
                return MatchViewHolder(view)

            }
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_matches, parent, false)
                return HeaderViewHolder(view)
            }
        }
        return super.createViewHolder(parent, viewType)
    }

    //التحويل يصير في هذه ال fun
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentMatch = list[position]
        //لكي الداتا تعرف هي رايح لاي ViewHolder
        when (holder) {
            is MatchViewHolder -> {
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
                    iconDeleteItem.setOnClickListener {
                        listener.onClickDeleteItem(position)
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
                    root.setOnClickListener { listener.onClickItem(currentMatch) }
                }
            }
            is HeaderViewHolder -> {

            }
        }
    }

    fun setData(newList: List<Match>) {
        list = newList
        notifyDataSetChanged()
    }

    //لجعل ايتم header تظهر فوق كل ايتم نتيجة المباراة (2% = معناها بين كل ايتم واخري )
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEMS
        }
    }

    // calculate size items
    override fun getItemCount() = list.size

    // لعمل ViewHolder اب يرث منهم كل الابناء لكي نعمل اكثر من ايتم
    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class MatchViewHolder(viewItem: View) : BaseViewHolder(viewItem) {

        val binding = ItemsMatchBinding.bind(viewItem)
    }

    class HeaderViewHolder(viewItem: View) : BaseViewHolder(viewItem) {

        val binding = ItemHeaderMatchesBinding.bind(viewItem)
    }

    companion object {
        const val VIEW_TYPE_ITEMS = 11
        const val VIEW_TYPE_HEADER = 12

    }

}