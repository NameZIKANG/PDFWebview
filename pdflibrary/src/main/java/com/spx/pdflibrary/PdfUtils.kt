package com.spx.pdflibrary

import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView

class PdfUtils {

    fun initWeb( webView: WebView, mUrl: String) {

        val ws = webView.settings
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.isHorizontalScrollBarEnabled = false //水平不显示滚动条
        webView.isVerticalScrollBarEnabled = true //垂直不显示滚动条
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.pluginState = WebSettings.PluginState.ON
        ws.loadWithOverviewMode = true
        // 保存表单数据
        ws.saveFormData = true
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true)
        ws.builtInZoomControls = true
        ws.displayZoomControls = false
        // 启动应用缓存
        ws.setAppCacheEnabled(true)
        // 设置缓存模式
        ws.cacheMode = WebSettings.LOAD_DEFAULT
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.useWideViewPort = true
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.javaScriptEnabled = true
        ws.javaScriptCanOpenWindowsAutomatically = true
        //  页面加载好以后，再放开图片
        ws.blockNetworkImage = false
        // 使用localStorage则必须打开
        ws.domStorageEnabled = true
        ws.allowContentAccess = true
        //        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.mixedContentMode = WebSettings.LOAD_NORMAL
            ws.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
            ws.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webView!!.setOnKeyListener(OnKeyEvent)
        webView.loadUrl("file:///android_asset/pdf.html?$mUrl")
    }

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
}