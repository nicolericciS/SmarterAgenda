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