package cn.krisez.basic

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.WebActivity
import kotlinx.android.synthetic.main.activity_compat.*

class CompatActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("AppCompat")
        addRightHelp(
            "https://developer.android.google.cn/jetpack/androidx/releases/appcompat#1.1.0",
            "AppCompat"
        )
        web_link.setOnClickListener {
            startActivity(
                Intent(this, WebActivity::class.java).putExtra(
                    "url",
                    "https://github.com/androidx/androidx/tree/androidx-master-dev/appcompat"
                ).putExtra("title", "Github-AppCompat")
            )
        }
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_compat, null)
    }
}