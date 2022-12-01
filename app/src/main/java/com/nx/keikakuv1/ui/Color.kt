package com.nx.keikakuv1.ui

import android.annotation.SuppressLint
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Color10 = Color(0xFF292929)
val Color50 = Color(0xFFE0E0E0)
val Color100 = Color(0xFF292929)
val Color300 = Color(0xFFC6FF00)
val Color500 = Color(0xFF00E5FF)
val Color900 = Color(0xFFFF00DE)
val Color901 = Color(0xFF292929)
val Color902 = Color(0xFFE0E0E0)
val Color903 = Color(0xFFA7A7A7)
val Color904 = Color(0xD3333333)

val Color1000 = Color(0xFFC6FF00)
val Color1001 = Color(0xFF292929)
val Color1002 = Color(0xFF292929)

@SuppressLint("ConflictingOnColor")
val LightColors = lightColors(
    primary = Color300,
    primaryVariant = Color500,
    secondary = Color50,
    background = Color100,
    surface = Color10,
    error = Color902,
    onPrimary = Color1000,
    onSecondary = Color901,
    onBackground = Color902,
    onSurface = Color903,
    onError = Color904
)