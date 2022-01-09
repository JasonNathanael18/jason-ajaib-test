package com.astrapay.jason_ajaib_test.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun getDateDifference(updatedTime: String): String{
        var resultDate = ""
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )

        try {
            val oldDate: Date = dateFormat.parse(updatedTime)!!
            println("Difference $oldDate")
            val currentDate = Date()
            println("Difference $currentDate")
            val diff: Long = currentDate.time - oldDate.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            if (oldDate.before(currentDate)) {
                Logger.e("is previous date")
                Logger.e(
                    "Difference: seconds: " + seconds + " minutes: " + minutes
                            + " hours: " + hours + " days: " + days
                )

                resultDate = when {
                    days > 30L -> {
                        val dateFormatter = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault())
                        "Updated on ${dateFormatter.format(oldDate)}"
                    }
                    days in 1..30 -> {
                        "Updated $days days ago"
                    }
                    hours != 0L -> {
                        "Updated $hours hours ago"
                    }
                    minutes != 0L -> {
                        "Updated $minutes minutes ago"
                    }
                    else -> {
                        "Updated just now"
                    }
                }
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return resultDate
    }
}