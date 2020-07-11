package com.rbazua.wishipets.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class PetTimestamps (
    @SerializedName(value = "created_at")
    var createdAt: Date,

    @SerializedName(value = "updated_at")
    var updatedAt: Date?
)