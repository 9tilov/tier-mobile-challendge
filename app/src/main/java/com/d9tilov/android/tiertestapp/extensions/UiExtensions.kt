package com.d9tilov.android.tiertestapp.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.d9tilov.android.tiertestapp.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun Context.bitmapDescriptorFromVector(
    @DrawableRes vectorDrawableResourceId: Int
): BitmapDescriptor? {
    val background = ContextCompat.getDrawable(this, R.mipmap.pin) ?: return null
    background.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
    val vectorDrawable = ContextCompat.getDrawable(this, vectorDrawableResourceId) ?: return null
    vectorDrawable.setBounds(
        40,
        20,
        vectorDrawable.intrinsicWidth + 40,
        vectorDrawable.intrinsicHeight + 20
    )
    val bitmap = Bitmap.createBitmap(
        background.intrinsicWidth,
        background.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    background.draw(canvas)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}