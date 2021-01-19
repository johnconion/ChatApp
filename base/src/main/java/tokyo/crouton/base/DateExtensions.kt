package tokyo.crouton.base

import android.content.Context
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Date
import java.util.Locale

fun String.toDateFromISOString(): Date {
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val accessor: TemporalAccessor = timeFormatter.parse(this)
    return Date.from(Instant.from(accessor))
}

fun Date.toTimeString(context: Context): String =
    SimpleDateFormat(context.getString(R.string.time_string_format), Locale.getDefault())
        .format(this)