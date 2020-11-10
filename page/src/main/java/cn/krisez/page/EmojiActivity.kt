package cn.krisez.page

import android.view.View
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_emoji.*

class EmojiActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("emoji")
        addRightHelp("https://developer.android.google.cn/guide/topics/ui/look-and-feel/emoji-compat", "")
        emoji_tv.text = EmojiCompat.get().process("neutral face \uD83D\uDE10")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_emoji, null)

}
