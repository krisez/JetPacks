package cn.krisez.common

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : CommonBaseActivity() {
    override fun init() {
        intent.getStringExtra("title")?.let {
            setTitle(it)
        }
        intent.getStringExtra("url")?.let {
            webview.loadUrl(it)
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if(newProgress == 100){
                    progress.visibility = View.GONE
                }
                progress.progress = newProgress
            }
        }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_web, null)

}