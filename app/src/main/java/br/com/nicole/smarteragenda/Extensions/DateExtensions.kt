package br.com.nicole.smarteragenda.Extensions

import br.com.nicole.smarteragenda.Util.DATE_FORMAT_DAY_MONTH_YEAR
import br.com.nicole.smarteragenda.Util.SHOW_DATE_FORMAT
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