package cn.krisez.page

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("界面")
        animation.setOnClickListener { startActivity(Intent(this, PropertyActivity::class.java)) }
        emoji.setOnClickListener { startActivity(Intent(this, PropertyActivity::class.java)) }
        layout.setOnClickListener { startActivity(Intent(this, PropertyActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_page, null)

}