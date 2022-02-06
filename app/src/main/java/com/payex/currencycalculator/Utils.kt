package com.payex.currencycalculator

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    companion object {
        fun calculate(
            value: BigDecimal,
            exchangeRate: BigDecimal,
            scale: Int = 4,
            roundingMode: RoundingMode = RoundingMode.HALF_DOWN
        ): BigDecimal {
            return value.multiply(exchangeRate).setScale(scale, roundingMode)
        }

        fun getFormattedCurrentDate(): String {
            val current = Date()
            return SimpleDateFormat("yyyy-MM-dd").format(current)
        }
    }
}