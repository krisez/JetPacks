package cn.krisez.behavior.camera

import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_camera_pre.*

class CameraPreActivity : CommonBaseActivity() {
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    override fun init() {
        setTitle("Camera 预览")
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))
    }

    fun bindPreview(cameraProvider: ProcessCameraProvider) {
        var preview: Preview = Preview.Builder().build()
        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview)
        preview.setSurfaceProvider(view_finder.surfaceProvider)
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_camera_pre, null)

}
