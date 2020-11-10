package cn.krisez.page

import android.content.Intent
import android.view.View
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_page.*

class PageActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("界面")
        EmojiCompat.init(BundledEmojiCompatConfig(applicationContext))
        animation.setOnClickListener { startActivity(Intent(this, PropertyActivity::class.java)) }
        emoji.setOnClickListener { startActivity(Intent(this, EmojiActivity::class.java)) }
        layout.setOnClickListener { startActivity(Intent(this, LayoutActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_page, null)

}