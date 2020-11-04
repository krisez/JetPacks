package cn.krisez.behavior.media

import android.content.Intent
import android.view.View
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_media.*

class MediaActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("媒体应用架构概览")
        addRightHelp("https://developer.android.google.cn/guide/topics/media-apps/media-apps-overview", "")
        media_video.setOnClickListener { startActivity(Intent(this, MediaVideoActivity::class.java)) }
        media_audio.setOnClickListener { startActivity(Intent(this, MediaAudioActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_media, null)


}
