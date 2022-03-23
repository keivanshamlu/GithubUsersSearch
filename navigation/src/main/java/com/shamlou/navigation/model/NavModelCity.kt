package com.shamlou.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NavModelMap(
    val lon: Double,
    val lat: Double,
    val title: String
) : Parcelable
