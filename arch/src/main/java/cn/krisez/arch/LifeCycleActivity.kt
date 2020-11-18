package cn.krisez.arch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import cn.krisez.common.CommonBaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LifeCycleActivity : CommonBaseActivity() {
    private lateinit var ob: MyObserver
    override fun init() {
        setTitle("LifeCycle")
        addRightHelp("https://developer.android.google.cn/topic/libraries/architecture/lifecycle", "LifeCycle")
        ob = MyObserver(lifecycle)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            ob.setEnable(true)
        }
        lifecycle.addObserver(ob)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_life_cycle, null)

    internal class MyObserver(private val lifecycle: Lifecycle) : LifecycleObserver {
        private var enable = false
        private var connected = false

        fun setEnable(enable: Boolean) {
            this.enable = enable
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Log.d("LifeCycleActivity.kt", "setEnable: ")
                connected = true
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun connect() {
            if (enable) {
                Log.d("LifeCycleActivity.kt", "connect: ")
                connected = true
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun disconnect() {
            if (connected) {
                Log.d("LifeCycleActivity.kt", "disconnect: ")
                connected = false
            }
        }
    }
}