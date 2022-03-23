package com.shamlou.domain.model.search


data class ResponseItemsDomain(

    var name: String,
    var displayName: String,
    var shortDescription: String,
    var description: String,
    var createdBy: String,
    var released: String,
    var createdAt: String,
    var updatedAt: String,
    var featured: Boolean,
    var curated: Boolean,
    var score: Int
)