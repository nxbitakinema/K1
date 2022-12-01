package com.nx.keikakuv1.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nx.keikakuv1.R.font.*

private val Roboto = FontFamily(
    Font(roboto_light, FontWeight.Light),
    Font(roboto_regular, FontWeight.Normal),
    Font(roboto_medium, FontWeight.Medium),
    Font(roboto_bold, FontWeight.SemiBold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light,
        fontSize = 25.sp,
        letterSpacing = 0.25.sp
    ),

    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp
    ),

    h3 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 0.25.sp
    ),

    h4 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        letterSpacing = 0.25.sp
    )



)