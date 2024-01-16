package com.ethan.common.util

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

fun View.clipViewCornerRadius(radius:Int){
    if (radius>0){
        this.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view?.width ?: 0, view?.height ?: 0, radius.toFloat())
            }
        }
        this.clipToOutline =true
    }else{
        this.clipToOutline =false
    }
}