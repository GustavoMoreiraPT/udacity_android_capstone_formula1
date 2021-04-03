package com.udacity.capstone.formula1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.capstone.formula1.dto.FavoriteLocation as FavoriteLocationDTO

@Entity(tableName = "locations_info_table")
data class FavoriteLocation(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double
)

fun FavoriteLocation.toDTO(): FavoriteLocationDTO {
    return FavoriteLocationDTO(
        id = this.id,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun List<FavoriteLocation>.toDTO(): List<FavoriteLocationDTO> {
    return map {
        it.toDTO()
    }
}

fun FavoriteLocationDTO.toEntity(): FavoriteLocation {
    return FavoriteLocation(
        id = this.id,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun List<FavoriteLocationDTO>.toEntity(): List<FavoriteLocation> {
    return map {
        it.toEntity()
    }
}