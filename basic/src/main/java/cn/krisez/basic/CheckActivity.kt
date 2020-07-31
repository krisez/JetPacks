package cn.krisez.basic

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.WebActivity
import kotlinx.android.synthetic.main.activity_check.*

class CheckActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Check")
        addRightHelp("https://developer.android.google.cn/studio/profile/benchmark", "Check")
        web_link.setOnClickListener {
            startActivity(
                Intent(this, WebActivity::class.java).putExtra(
                    "url",
                    "https://github.com/android/performance-samples"
                ).putExtra("title", "Github-Benchmark")
            )
        }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_check, null)
}