package com.kingjinho.dontcallhim.utils

import java.util.regex.Pattern

private const val phoneNumberRegex = "^01([0-9])-(\\d{3}|\\d{4})-\\d{4}$"
private val pattern = Pattern.compile(phoneNumberRegex)

fun String.isValidPhoneNumber(): Boolean {
    return pattern.matcher(this).matches()
}