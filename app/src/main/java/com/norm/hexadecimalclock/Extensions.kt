@file:JvmName("ExtensionUtils")

package com.norm.hexadecimalclock

import android.annotation.SuppressLint
import android.util.Log

/**
 * Create by Kyaw Zayar Tun on 24/01/2020.
 */
@SuppressLint("DefaultLocale")
/** generate two digit hexadecimal code from datetime = seconds | minutes | hours **/
fun Int.toColorHexString() : String {
    val hex = ("0" + this.toString(16)).toUpperCase()
    Log.i("Extensions", "Int to hex = $hex")

    if (hex.length > 2) {
        return hex.substring(1)
    } else {
        return hex
    }
}

/**
 * Check the color hex string for brightness
 * If brightness is greater than 155, you should use text color #000000 = black
 * else you should use #FFFFFF = white
 */
fun String.hexIsLight() : Boolean {
    val hex = this.substring(1)
    val red = hex.substring(0, 2).toInt(16)
    val green = hex.substring(2, 4).toInt(16)
    val blue = hex.substring(4).toInt(16)

    // brightness = ((red * 299) + (green * 587) + (blue * 114)) / 1000;
    // it is a known formula, nothing magical here
    val brightness = ((red * 299) + (green * 587) + (blue * 114)) / 1000
    return brightness > 155
}
