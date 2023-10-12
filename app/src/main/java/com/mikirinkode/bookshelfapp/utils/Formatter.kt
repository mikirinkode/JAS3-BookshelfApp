package com.mikirinkode.bookshelfapp.utils

import java.text.NumberFormat
import java.util.Locale

object Formatter {

    fun formatAsMoney(amount: Int): String {
        val localeID = Locale("id", "ID") // Use the Indonesian locale
        val formatter = NumberFormat.getCurrencyInstance(localeID)
        return formatter.format(amount)
    }
}