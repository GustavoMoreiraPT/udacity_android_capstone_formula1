package com.udacity.capstone.formula1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.capstone.formula1.dto.Constructor as ConstructorDTO

@Entity(tableName = "constructor_info_table")
data class Constructor(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "nationality")
    val nationality: String,

    @ColumnInfo(name = "url")
    val url: String
)

fun Constructor.toDTO(): ConstructorDTO {
    return ConstructorDTO(
        id = this.id,
        name = this.name,
        nationality = this.nationality,
        url = this.url
    )
}

fun List<Constructor>.toDTO(): List<ConstructorDTO> {
    return map {
        it.toDTO()
    }
}

fun ConstructorDTO.toEntity(): Constructor {
    return Constructor(
        id = this.id,
        name = this.name,
        nationality = this.nationality,
        url = this.url
    )
}

fun List<ConstructorDTO>.toEntity(): List<Constructor> {
    return map {
        it.toEntity()
    }
}