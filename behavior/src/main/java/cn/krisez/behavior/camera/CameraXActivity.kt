package cn.krisez.behavior.camera

import android.content.Intent
import android.view.View
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_camera_x.*

/**
 * 更多用例
 * <a href="https://github.com/android/camera-samples/blob/master/CameraXBasic/app/src/main/java/com/android/example/cameraxbasic/fragments/CameraFragment.kt">官方用例</a>
 */
class CameraXActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("CameraX")
        addRightHelp("https://developer.android.google.cn/training/camerax", "CameraX")
        camera_x_preview.setOnClickListener { startActivity(Intent(this, CameraPreActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_camera_x, null)

}