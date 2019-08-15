package thepublictransport.schildbach.pte.service.framework.tools

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTools {
    fun parseDate(dateString : String) : Date {
        var date: Date
        val format = SimpleDateFormat("dd.MM.yyyy'T'HH:mm:ss")

        try {
            date = format.parse(dateString)
        } catch (e: ParseException) {
            date = Date()
        }

        return date
    }
}