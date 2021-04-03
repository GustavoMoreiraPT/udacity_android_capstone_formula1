package com.udacity.capstone.formula1.api

import com.udacity.capstone.formula1.dto.Constructor
import com.udacity.capstone.formula1.dto.Driver
import org.json.JSONObject

fun parseConstructorsJsonResult(jsonResult: JSONObject): List<Constructor> {
    val wrapperJson = jsonResult.getJSONObject("MRData")

    val constructorsList = ArrayList<Constructor>()

    val constructorsJson = wrapperJson.getJSONObject("ConstructorTable")

    val constructorsJsonArray = constructorsJson.getJSONArray("Constructors")

    for (i in 0 until constructorsJsonArray.length()) {
        val constructorJson = constructorsJsonArray.getJSONObject(i)

        val name = constructorJson.getString("name")
        val nationality = constructorJson.getString("nationality")
        val url = constructorJson.getString("url")

        val constructor = Constructor(0, name, nationality, url)
        constructorsList.add(constructor)
    }

    return constructorsList.toList()
}

fun parseDriversJsonResult(jsonResult: JSONObject): List<Driver> {
    val wrapperJson = jsonResult.getJSONObject("MRData")

    val driversList = ArrayList<Driver>()

    val driversJson = wrapperJson.getJSONObject("DriverTable")

    val driversJsonArray = driversJson.getJSONArray("Drivers")

    for (i in 0 until driversJsonArray.length()) {
        val driversJson = driversJsonArray.getJSONObject(i)

        val driverId = driversJson.getString("driverId")
        val code = driversJson.getString("code")
        val url = driversJson.getString("url")
        val permanentNumber = driversJson.getInt("permanentNumber")
        val givenName = driversJson.getString("givenName")
        val familyName = driversJson.getString("familyName")
        val dateOfBirth = driversJson.getString("dateOfBirth")
        val nationality = driversJson.getString("nationality")

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