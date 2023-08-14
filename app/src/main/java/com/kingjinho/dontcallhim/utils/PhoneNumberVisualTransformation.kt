package com.kingjinho.dontcallhim.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedString = AnnotatedString.Builder().apply {
            append(text.text.convertToPhoneNumber())
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