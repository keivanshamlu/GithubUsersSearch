package com.shamlou.data.model.search

import com.google.gson.annotations.SerializedName


data class ResponseItemsRemote (

  @SerializedName("name"              ) var name             : String?  = null,
  @SerializedName("display_name"      ) var displayName      : String?  = null,
  @SerializedName("short_description" ) var shortDescription : String?  = null,
  @SerializedName("description"       ) var description      : String?  = null,
  @SerializedName("created_by"        ) var createdBy        : String?  = null,
  @SerializedName("released"          ) var released         : String?  = null,
  @SerializedName("created_at"        ) var createdAt        : String?  = null,
  @SerializedName("updated_at"        ) var updatedAt        : String?  = null,
  @SerializedName("featured"          ) var featured         : Boolean? = null,
  @SerializedName("curated"           ) var curated          : Boolean? = null,
  @SerializedName("score"             ) var score            : Int?     = null

)