@file:JvmName("Utils")

package com.norm.hexadecimalclock

import java.util.*

object Utils {

    /**
     * Convert hexadecimal code of seconds, minutes and hours of current datetime.
     * @return Hexadecimal color code
     */
    fun getHexTime(): String {
        val date = Date()

        // TODO: not to use deprecated date.seconds
        val seconds = (date.seconds * 255 / 59)

        // TODO: not to use deprecated date.minute
        val minutes = (date.minutes * 255 / 59)

        // TODO: not to use deprecated date.hours
        val hours = (date.hours * 255 / 23)

        return "#${hours.toColorHexString()}${minutes.toColorHexString()}${seconds.toColorHexString()}"
    }
}