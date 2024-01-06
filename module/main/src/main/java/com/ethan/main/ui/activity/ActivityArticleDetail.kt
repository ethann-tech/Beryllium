package com.ethan.main.ui.activity

import android.net.Uri
import androidx.appcompat.widget.LinearLayoutCompat
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityBusinessBase
import com.ethan.framework.widget.view.WebViewBase
import com.ethan.main.R
import com.ethan.main.databinding.ActivityArticleDetailBinding
import com.ethan.zincum.base.ActivityDataBindingBase
import com.google.gson.Gson
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import io.github.uhsk.kit.android.setNavigationNoDoubleClickListener
import io.github.uhsk.kit.asDrawable
import org.koin.android.ext.android.inject
import zlc.season.butterfly.annotation.Agile

@Agile(scheme = RouterPath.ACTIVITY_ARTICLE_DETAIL)
class ActivityArticleDetail : ActivityBusinessBase<ActivityArticleDetailBinding>() {
    private lateinit var mWebView: WebViewBase
    private val mTitle: String by lazy { intent?.getStringExtra("title") ?: "" }
    private val mUrl: String by lazy { intent?.getStringExtra("url") ?: "" }
    override fun layoutResId() = R.layout.activity_article_detail

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(webView: WebView?, title: String?) {
            super.onReceivedTitle(webView, title)
            if (webView != null && !webView.canGoBack() && mTitle.isNotEmpty()) {
                mBinding.includeToolbar.tvToolbarTitle.text = mTitle
            } else {
                if (!title.isNullOrEmpty() && !title.startsWith("http", true)) {
                    mBinding.includeToolbar.tvToolbarTitle.text = title
                }
            }
        }

        override fun onShowFileChooser(p0: WebView?, p1: ValueCallback<Array<Uri>>?, p2: FileChooserParams?): Boolean {
            return super.onShowFileChooser(p0, p1, p2)
        }

        override fun onProgressChanged(webView: WebView?, progress: Int) {
            super.onProgressChanged(webView, progress)
            if (progress == 100) {
                hideLoading()
            }
        }
    }
    private val mWebViewClient = object : WebViewClient() {
        override fun onPageFinished(webView: WebView?, url: String?) {
            super.onPageFinished(webView, url)
        }

        override fun shouldOverrideUrlLoading(webView: WebView?, s: String?): Boolean {
            return super.shouldOverrideUrlLoading(webView, s)
        }

        override fun onReceivedError(p0: WebView?, p1: Int, p2: String?, p3: String?) {
            super.onReceivedError(p0, p1, p2, p3)
        }

        override fun onReceivedSslError(p0: WebView?, handler: SslErrorHandler?, p2: SslError?) {
            super.onReceivedSslError(p0, handler, p2)
            handler?.proceed()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (mWebView.canGoBack()) { // 返回上一个浏览页
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun initWindowAttributes() {

    }

    override fun initView() {
        super.initView()
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        mBinding.includeToolbar.toolbar.navigationIcon = com.ethan.common.R.drawable.nav_back.asDrawable(context = mContext)
        mBinding.includeToolbar.toolbar.setNavigationNoDoubleClickListener { finish() }
        showLoading("加载中...")
        initWebView()
        mWebView.loadUrl(mUrl)
    }

    private fun initWebView() {
        mWebView = WebViewBase(context = mContext)
        val params = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        mBinding.layoutWebView.addView(mWebView, params)
        mWebView.webChromeClient = mWebChromeClient
        mWebView.webViewClient = mWebViewClient
    }

    override fun initListener() {}

}