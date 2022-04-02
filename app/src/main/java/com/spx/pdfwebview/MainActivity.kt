package com.spx.pdfwebview

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()  //隐藏ActionBar
        initWebView( "http://47.99.136.221:9000/gse-minio/b5a591c5c0504ff0a0b1af1b2a7b2516.pdf")

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
            mWebView!!,
            url
        )
//
//        var ws = mWebView!!.settings
//
//        mWebView!!.setHorizontalScrollBarEnabled(false) //水平不显示滚动条
//        mWebView!!.setVerticalScrollBarEnabled(true) //垂直不显示滚动条
//        ws.allowUniversalAccessFromFileURLs = true
//        ws.pluginState = WebSettings.PluginState.ON
//        ws.loadWithOverviewMode = true
//        // 保存表单数据
//        ws.saveFormData = true
//        // 是否应该支持使用其屏幕缩放控件和手势缩放
//        ws.setSupportZoom(true)
//        ws.builtInZoomControls = true
//        ws.displayZoomControls = false
//        // 启动应用缓存
//        ws.setAppCacheEnabled(true)
//        // 设置缓存模式
//        ws.cacheMode = WebSettings.LOAD_DEFAULT
//        // setDefaultZoom  api19被弃用
//        // 设置此属性，可任意比例缩放。
//        ws.useWideViewPort = true
//        // 告诉WebView启用JavaScript执行。默认的是false。
//        ws.javaScriptEnabled = true
//        ws.javaScriptCanOpenWindowsAutomatically = true
//        //  页面加载好以后，再放开图片
//        ws.blockNetworkImage = false
//        // 使用localStorage则必须打开
//        ws.domStorageEnabled = true
//        ws.allowContentAccess = true
//        // ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ws.mixedContentMode = WebSettings.LOAD_NORMAL
//            ws.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
//            ws.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//        }
//        mWebView!!.setOnKeyListener(OnKeyEvent)
////        mWebView!!.setWebViewClient(webClient)
//        mWebView!!.loadUrl(url)

    }

//    private val webClient = object : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            if (url != null) {
//                Log.d("TAG", url)
//            }
//            return false
//        }
//    }

    private val OnKeyEvent = View.OnKeyListener { v, keyCode, event ->
        val action = event.action
        val webView = v as WebView
        if (KeyEvent.ACTION_DOWN == action && KeyEvent.KEYCODE_BACK == keyCode) {
            if (webView?.canGoBack()) {
                webView.goBack()
                return@OnKeyListener true
            }
        }
        false
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
        mWebView = null
    }
}