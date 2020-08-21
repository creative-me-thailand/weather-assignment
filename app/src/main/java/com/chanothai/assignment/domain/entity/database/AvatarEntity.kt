package com.chanothai.assignment.domain.entity.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "avatar_table")
data class AvatarEntity(
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "avatar_id")
        var id: Int) {

    var name: String? = null
    var image: String? = null
    var status: String? = null
    var species: String? = null
    var gender: String? = null
    var origin: String? = null
    var location: String? = null
    var created: String? = null
}