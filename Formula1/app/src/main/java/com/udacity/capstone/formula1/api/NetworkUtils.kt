package com.udacity.capstone.formula1.api

import com.udacity.capstone.formula1.dto.Constructor
import com.udacity.capstone.formula1.dto.Driver
import org.json.JSONObject

fun parseConstructorsJsonResult(jsonResult: JSONObject): List<Constructor> {
    val constructorsJson = jsonResult.getJSONObject("MRData")

    val constructorsList = ArrayList<Constructor>()

    val constructorsJsonArray = constructorsJson.getJSONArray("ConstructorsTable")

    for (i in 0 until constructorsJsonArray.length()) {
        val constructorJson = constructorsJsonArray.getJSONObject(i)

        val name = constructorJson.getString("Name")
        val nationality = constructorJson.getString("Nationality")
        val url = constructorJson.getString("url")

        val constructor = Constructor(0, name, nationality, url)
        constructorsList.add(constructor)
    }

    return constructorsList.toList()
}

fun parseDriversJsonResult(jsonResult: JSONObject): List<Driver> {
    val driversJson = jsonResult.getJSONObject("MRData")

    val driversList = ArrayList<Driver>()

    val driversJsonArray = driversJson.getJSONArray("DriversTable")

    for (i in 0 until driversJsonArray.length()) {
        val driversJson = driversJsonArray.getJSONObject(i)

        val driverId = driversJson.getString("driverId")
        val code = driversJson.getString("code")
        val url = driversJson.getString("url")
        val permanentNumber = driversJson.getInt("PermanentNumber")
        val givenName = driversJson.getString("GivenName")
        val familyName = driversJson.getString("FamilyName")
        val dateOfBirth = driversJson.getString("DateOfBirth")
        val nationality = driversJson.getString("Nationality")

        val driver = Driver(
            0,
            driverId,
            code,
            permanentNumber,
            givenName,
            familyName,
            dateOfBirth,
            nationality,
            url
        )
        driversList.add(driver)
    }

    return driversList.toList()
}