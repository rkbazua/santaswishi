package com.rbazua.wishipets.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Story(
    @ColumnInfo(name = "pet_uid")
    @SerializedName(value = "petuid")
    var petuid: String,

    @SerializedName(value = "title")
    var title: String,

    @SerializedName(value = "description")
    var description: String?


) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long  = 0

    @SerializedName(value = "timestamps")
    @Ignore
    var timestamps: PetTimestamps = PetTimestamps(Date(), Date())
}