package ro.simona.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateGenerator {

    /**
    if the date is today -> the function returns the hour and minute
    if the date is this year -> the function returns the day and month
    otherwise -> the function returns the day, month and year
     */
    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = inputFormat.parse(inputDate)

        val currentDate = Calendar.getInstance()

        val calendar = Calendar.getInstance()
        calendar.time = date
        if (isSameDay(calendar, currentDate)) {
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return timeFormat.format(date)
        }

        val currentYear = currentDate.get(Calendar.YEAR)
        val yearOfDate = calendar.get(Calendar.YEAR)
        if (currentYear == yearOfDate) {
            val dayMonthFormat = SimpleDateFormat("dd-MM", Locale.getDefault())
            return dayMonthFormat.format(date)
        }

        val dayMonthYearFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dayMonthYearFormat.format(date)
    }

    private fun isSameDay(date1: Calendar, date2: Calendar): Boolean {
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
                date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR)
    }
}