package com.astrapay.jason_ajaib_test.helper

import kotlin.math.ln
import kotlin.math.pow

object NumberFormatter {
    fun getFormatedNumber(count: Long): String {
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
    }
}