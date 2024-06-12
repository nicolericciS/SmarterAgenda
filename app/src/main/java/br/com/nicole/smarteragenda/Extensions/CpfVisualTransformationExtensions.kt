package br.com.nicole.smarteragenda.Extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CpfVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskCpf(text)
    }
}

fun maskCpf(text: AnnotatedString): TransformedText {
    // Remove any character that's not a digit
    val trimmed = text.text.filter { it.isDigit() }

    val out = StringBuilder()
    for (i in trimmed.indices) {
        out.append(trimmed[i])
        if (i == 2 || i == 5) {
            out.append('.')
        }
        if (i == 8) {
            out.append('-')
        }
    }

    val cpfOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset + 1
            if (offset <= 8) return offset + 2
            if (offset <= 11) return offset + 3
            return 14
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset - 1
            if (offset <= 11) return offset - 2
            if (offset <= 15) return offset - 3
            return 11
        }
    }

    return TransformedText(AnnotatedString(out.toString()), cpfOffsetTranslator)
}