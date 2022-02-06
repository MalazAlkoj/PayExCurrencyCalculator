package com.payex.currencycalculator

import android.icu.util.Currency
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.payex.currencycalculator.Utils.Companion.calculate
import com.payex.currencycalculator.Utils.Companion.getFormattedCurrentDate
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.util.*

class MainActivity : AppCompatActivity() {
    val defaultCurrencyFrom: Currency = Currency.getInstance("NOK")
    val defaultCurrencTo = Currency.getInstance("USD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadSpinner(R.id.spinnerFrom, defaultCurrencyFrom);
        loadSpinner(R.id.spinnerTo, defaultCurrencTo)
        listenToTextChanging()
    }

    private fun loadSpinner(spinnerId: Int, defaultCurrency: Currency) {
        //val spinnerFrom = R.id.spinnerFrom
        val spinner: Spinner = findViewById(spinnerId)
        ArrayAdapter.createFromResource(
            this,
            R.array.Currencies,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
            var defaultIndexSelection = 0
            try {
                defaultIndexSelection = arrayAdapter.getPosition(defaultCurrency.currencyCode)
            } catch (e: Exception) {
            }
            spinner.setSelection(defaultIndexSelection)
        }
    }

    private fun listenToTextChanging() {
        val result: TextView = findViewById(R.id.textViewResult)
        result.text = "im ready"
        editTextInputConvertingValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                calculate(
                    BigDecimal(editTextInputConvertingValue.text.toString()),
                    BigDecimal("0.1")
                ).toString().also {
                    result.text = editTextInputConvertingValue.text.toString() +
                            " equals " + it + spinnerTo.selectedItem.toString() + ", on the date " + getFormattedCurrentDate()
                }

            }
        })


    }


}

