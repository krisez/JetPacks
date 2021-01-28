package com.example.car

import android.view.View
import cn.krisez.common.CommonBaseActivity

class CarActivity : CommonBaseActivity() {
    override fun init() {
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, MineFragment()).commit()
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_app, null)
    }
}