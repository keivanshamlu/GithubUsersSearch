package com.shamlou.bases_android.mapper

interface Mapper<First, Second> {

    fun map(first: First): Second
}