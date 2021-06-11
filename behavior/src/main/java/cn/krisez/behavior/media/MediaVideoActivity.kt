package cn.krisez.behavior.media

import android.view.View
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_media_video.*

class MediaVideoActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Video")
        video_view.setVideoPath("http://krisez.cn/media/%E8%AE%B8%E5%B5%A9-%E9%9B%85%E4%BF%97%E5%85%B1%E8%B5%8F.mp4")
        video_view.setOnPreparedListener { it.start();it.isLooping = true }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_media_video, null)

}