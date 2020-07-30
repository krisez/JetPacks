package cn.krisez.basic.ktx

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

val String.empty: Boolean
    get() = TextUtils.isEmpty(this)
