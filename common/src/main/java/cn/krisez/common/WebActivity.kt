package cn.krisez.common

import android.os.Build
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : CommonBaseActivity() {
    private var mTitle = ""
    override fun init() {
        intent.getStringExtra("title")?.let {
            mTitle = it
            setTitle(it)
        }
        intent.getStringExtra("url")?.let {
            webview.loadUrl(it)
        }

        webview.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                /* val url = request?.url.toString()
                 if(url.startsWith("http://") or url.startsWith("https://")){
                     return false
                 }*/
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    progress.visibility = View.GONE
                }
                progress.progress = newProgress
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (mTitle.isEmpty() && !title.isNullOrEmpty()) {
                    setTitle(title)
                }
            }


        }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_web, null)

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}