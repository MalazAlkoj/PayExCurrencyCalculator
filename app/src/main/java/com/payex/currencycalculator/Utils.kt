package com.payex.currencycalculator

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    companion object {
        fun calculate(
            value: BigDecimal,
            exchangeRate: Double,
            fromCurrency: String = "NOK",
            scale: Int = 4,
            roundingMode: RoundingMode = RoundingMode.HALF_DOWN
        ): BigDecimal {
            if (exchangeRate.equals(0)) return BigDecimal.ZERO
            if (fromCurrency == "USD") return value.multiply(BigDecimal(exchangeRate))
                .setScale(scale, roundingMode)
            return value.divide(BigDecimal(exchangeRate), scale, roundingMode)
        }

        fun getFormattedCurrentDate(): String {
            val current = Date()
            return SimpleDateFormat("yyyy-MM-dd").format(current)
        }
    }
}