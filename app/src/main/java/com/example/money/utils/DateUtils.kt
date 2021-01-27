package  com.example.money.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

object DateUtils {

    fun convertUTCZToMills(dateUTC: String): Long {
        var millis: Long = 0

        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        df.timeZone = TimeZone.getTimeZone("UTC")

        var date: Date? = null
        try {
            date = df.parse(dateUTC)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        df.timeZone = TimeZone.getDefault()


        millis = date?.time!!

        return millis
    }

    fun convertUTCZToDate(dateUTC: String): Date? {
        var millis: Long = 0

        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        df.timeZone = TimeZone.getTimeZone("UTC")

        var date: Date? = null
        try {
            date = df.parse(dateUTC)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    fun convertMilliSecondsToDateFormat(milliSeconds: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(milliSeconds))
    }
}