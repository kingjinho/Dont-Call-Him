package com.kingjinho.dontcallhim.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedString = AnnotatedString.Builder().apply {

            val number = text.text

            if (number.length < 3) {
                append(number)
            }

            if (number.length >= 3) {
                append(number.substring(0, 3))
                append("-")
                if (number.length < 7) {
                    append(number.substring(3))
                }
            }

            if (number.length >= 7) {
                append(number.substring(3, 7))
                append("-")
                if (number.length < 11) {
                    append(number.substring(7))
                }
            }

            if (number.length >= 11) {
                append(number.substring(7))
            }


        }.toAnnotatedString()

        return TransformedText(
            annotatedString,
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return annotatedString.text.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return annotatedString.text.replace("-", "").length
                }
            }
        )
    }
}