package com.myapp.mvvmexample.core.util

import java.text.SimpleDateFormat
import java.util.*


class DateUtil {

    companion object {
        const val SERVER_DATE_FORMAT = "EEE, dd MMM yyyy hh:mm:ss z" //Thu, 28 Feb 2019 12:52:46 GMT
        const val DD_MM_YYYY_FORMAT = "dd/MM/yyyy"

        fun format(input: String?, pattern: String): String {
            val formatterIn = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
            val output = formatterIn.parse(input)
            val formatterOut = SimpleDateFormat(pattern, Locale.ENGLISH)
            return formatterOut.format(output)
        }
    }
}