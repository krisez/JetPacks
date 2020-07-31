package cn.krisez.basic.ktx

import android.content.Intent
import android.util.Log
import android.view.View
import cn.krisez.basic.R
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.WebActivity
import kotlinx.android.synthetic.main.activity_xc.*
import kotlinx.coroutines.*
import kotlin.math.log

class XcActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("协程")
        kotlin_official.setOnClickListener {
            startActivity(
                Intent(this, WebActivity::class.java).putExtra("title", "Github")
                    .putExtra("url", "https://github.com/Kotlin/kotlinx.coroutines")
            )
        }
        addRightHelp("https://developer.android.google.cn/kotlin/coroutines", "Android协程")
        CoroutineScope(Dispatchers.Main).launch {
            enh()
            toast("main")
        }
        runBlocking {//该操作会阻塞主线程
            Log.d("asdasd", "init:1 ")
            delay(4000)
            Log.d("asdasd", "init:2 ")
        }
    }

    suspend fun enh() {
        CoroutineScope(Dispatchers.Default).launch {
            Log.d("asdasd", "enh:1")
            delay(4000)
            Log.d("asdasd", "enh:2")
        }
        withContext(Dispatchers.IO) {
//            j.join() //等待协程执行完成
            launch {
                Log.d("asdasd", "asdasdd")
                delay(1000L)
                Log.d("asdasd", "asdadasdadasdadsadad")
            }
        }
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_xc, null)
    }

}