package com.kingjinho.dontcallhim.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "0.8f",
    group = "Font scale",
    fontScale = 0.8f
)

@Preview(
    name = "1.0f",
    group = "Font scale",
    fontScale = 1.0f
)

@Preview(
    name = "1.2f",
    group = "Font scale",
    fontScale = 1.2f
)

@Preview(
    name = "1.4f",
    group = "Font scale",
    fontScale = 1.4f
)

@Preview(
    name = "1.6f",
    group = "Font scale",
    fontScale = 1.6f
)

@Preview(
    name = "1.8f",
    group = "Font scale",
    fontScale = 1.8f
)

@Preview(
    name = "2.0f",
    group = "Font scale",
    fontScale = 2.0f
)
annotation class FontScalePreviews


@Preview(
    name = "light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    showSystemUi = true
)

@Preview(
    name = "dark mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    showSystemUi = true
)
annotation class DarkLightPreviews