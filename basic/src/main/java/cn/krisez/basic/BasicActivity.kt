package cn.krisez.basic

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_basic_main.*

class BasicActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("基础")
        ktx.setOnClickListener { startActivity(Intent(this, KtxActivity::class.java)) }
        compat.setOnClickListener { startActivity(Intent(this, CompatActivity::class.java)) }
        auto.setOnClickListener { startActivity(Intent(this, AutoActivity::class.java)) }
        check.setOnClickListener { startActivity(Intent(this, CheckActivity::class.java)) }
        dex.setOnClickListener { startActivity(Intent(this, DexActivity::class.java)) }
        security.setOnClickListener { startActivity(Intent(this, SecurityActivity::class.java)) }
        test.setOnClickListener { startActivity(Intent(this, TestActivity::class.java)) }
        tv.setOnClickListener { startActivity(Intent(this, TvActivity::class.java)) }
        wear.setOnClickListener { startActivity(Intent(this, WearActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_basic_main, null)
}