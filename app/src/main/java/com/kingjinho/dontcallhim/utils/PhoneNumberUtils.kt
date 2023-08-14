package com.kingjinho.dontcallhim.utils

import java.util.regex.Pattern

private const val phoneNumberRegex = "^01([0-9])(\\d{3}|\\d{4})\\d{4}$"
private val pattern = Pattern.compile(phoneNumberRegex)

fun String.isValidPhoneNumber(): Boolean {
    return pattern.matcher(this).matches()
}

fun String.convertToPhoneNumber(): String {
    return StringBuilder().also {
        if (length < 3) {
            it.append(this)
        }

        if (length >= 3) {
            it.append(substring(0, 3))
            it.append("-")
            if (length < 7) {
                it.append(substring(3))
            }
        }

        if (length >= 7) {
            it.append(substring(3, 7))
            it.append("-")
            if (length < 11) {
                it.append(substring(7))
            }
        }

        if (length >= 11) {
            it.append(substring(7))
        }

    }.toString()
}