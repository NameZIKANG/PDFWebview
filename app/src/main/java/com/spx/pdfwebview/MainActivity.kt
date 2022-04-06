package com.spx.pdfwebview

import android.os.Bundle
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.spx.pdflibrary.initWeb

class MainActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView  // 使用lateinit修饰后，访问一个还没有初始化的变量或属性将会导致UninitializedPropertyAccessException异常。

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()  //隐藏ActionBar
        initWebView("http://47.99.136.221:9000/gse-minio/b5a591c5c0504ff0a0b1af1b2a7b2516.pdf")
    }

    // 初始化对象
    fun initWebView(url: String) {
        val webViewContainer = findViewById<FrameLayout>(R.id.frame_layout)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        mWebView = WebView(applicationContext)
        webViewContainer.addView(mWebView, params)

        initWeb(
            mWebView,
            url
        )
    }

    override fun onResume() {
        super.onResume()
        mWebView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView?.clearCache(true)
        (mWebView?.parent as FrameLayout).removeView(mWebView)
        mWebView?.stopLoading()
//        mWebView?.setWebViewClient(null)
        mWebView?.setWebChromeClient(null)
        mWebView?.removeAllViews()
        mWebView?.destroy()
//        mWebView = null
    }
}