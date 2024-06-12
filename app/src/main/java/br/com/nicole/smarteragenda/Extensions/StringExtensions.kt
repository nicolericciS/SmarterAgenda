package br.com.nicole.smarteragenda.Extensions

import br.com.nicole.smarteragenda.Util.DATE_FORMAT_DAY_MONTH_YEAR
import br.com.nicole.smarteragenda.Util.SHOW_DATE_FORMAT
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



fun Date.convertToString(): String {
    return SimpleDateFormat(
        SHOW_DATE_FORMAT,
        Locale.getDefault()
    ).format(this)
}

fun String.formatAsPhoneNumber(): String {
    return if (length == 11) {
        "(${substring(0, 2)}) ${substring(2, 7)}-${substring(7, 11)}"
    } else {
        this
    }
}