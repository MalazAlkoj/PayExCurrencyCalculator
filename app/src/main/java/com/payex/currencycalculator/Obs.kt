package com.payex.currencycalculator

data class Obs(
    var obsDimension: String? = null,
    var obsValue: Double = defaultExchangeRate
) {


    override fun toString(): String {
        return "ObsDimension = $obsDimension\n ObsValue = $obsValue "
    }
}