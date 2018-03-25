package com.mirego.csmapapplication.model

import java.math.BigDecimal

/**
 * Created by Felix on 2018-03-24.
 */
class PartItem (
    val name : String,
    val component : String,
    val notes : String,
    val type : String,
    val lat : BigDecimal,
    val lon : BigDecimal,
    val address : String
)
{ }