package cn.krisez.behavior.media

import android.view.View
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity

class MediaVideoActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Video")

    }

    override fun view(): View? = View.inflate(this, R.layout.activity_media_video, null)

}