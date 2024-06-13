package br.com.nicole.smarteragenda.util.extensions

import br.com.nicole.smarteragenda.util.DATE_FORMAT_DAY_MONTH_YEAR
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertToDate(): Date? {
    return try {
        SimpleDateFormat(
            DATE_FORMAT_DAY_MONTH_YEAR,
            Locale.getDefault()
        ).parse(this)
    } catch (e: ParseException) {
        null
    }
}