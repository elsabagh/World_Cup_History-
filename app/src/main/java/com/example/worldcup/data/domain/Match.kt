package com.example.worldcup.data.domain

import android.os.Parcel
import android.os.Parcelable


data class Match (
    val year: Int,
    val stadium: String,
    val city: String,
    val homeTeamName: String,
    val awayTeamName: String,
    val homeTeamGoals: Int,
    val awayTeamGoals: Int) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(year)
        parcel.writeString(stadium)
        parcel.writeString(city)
        parcel.writeString(homeTeamName)
        parcel.writeString(awayTeamName)
        parcel.writeInt(homeTeamGoals)
        parcel.writeInt(awayTeamGoals)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}