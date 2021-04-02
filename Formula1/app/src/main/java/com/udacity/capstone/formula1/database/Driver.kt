package com.udacity.capstone.formula1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.capstone.formula1.dto.Driver as DriverDTO

@Entity(tableName = "driver_info_table")
data class Driver(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "driverId")
    val driverId: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "permanentNumber")
    val permanentNumber: Int,

    @ColumnInfo(name = "givenName")
    val givenName: String,

    @ColumnInfo(name = "familyName")
    val familyName: String,

    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String,

    @ColumnInfo(name = "nationality")
    val nationality: String,

    @ColumnInfo(name = "url")
    val url: String
)

fun Driver.toDTO(): DriverDTO {
    return DriverDTO(
        id = this.id,
        driverId = this.driverId,
        code = this.code,
        permanentNumber = this.permanentNumber,
        givenName = this.givenName,
        familyName = this.familyName,
        dateOfBirth = this.dateOfBirth,
        nationality = this.nationality,
        url = this.url
    )
}

fun List<Driver>.toDTO(): List<DriverDTO> {
    return map {
        it.toDTO()
    }
}

fun DriverDTO.toEntity(): Driver {
    return Driver(
        id = this.id,
        driverId = this.driverId,
        code = this.code,
        permanentNumber = this.permanentNumber,
        givenName = this.givenName,
        familyName = this.familyName,
        dateOfBirth = this.dateOfBirth,
        nationality = this.nationality,
        url = this.url
    )
}

fun List<DriverDTO>.toEntity(): List<Driver> {
    return map {
        it.toEntity()
    }
}