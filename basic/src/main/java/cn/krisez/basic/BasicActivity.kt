package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

public class BasicActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("基础")
    }

    override fun view(): View = View.inflate(this, R.layout.activity_basic_main, null)
}