/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ethan.framework.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import com.ethan.framework.util.LoadingUtil
import org.slf4j.LoggerFactory

/**
 * @author ethan
 */
@Keep
abstract class ActivityBase : AppCompatActivity() {

    /**
     * 是否禁止旋转屏幕
     */
    private var isAllowScreenRotate = false
    protected lateinit var mContext: Context
        private set
    protected var mLogger = LoggerFactory.getLogger(this.javaClass)
    private lateinit var mLoadingUtil: LoadingUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mLoadingUtil = LoadingUtil(context = mContext)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        initWindowAttributes()
        if (layoutResId() != -1) {
            bindLayoutResId(layoutResId())
        } else {
            bindLayoutView()
        }
        initView()

        requestedOrientation = (if (isAllowScreenRotate) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        if (savedInstanceState != null) {
            initParam(savedInstanceState)
        } else {
            if (intent != null && intent.extras != null) {
                initParam(intent.extras)
            }
        }
        initListener()
    }

    /**
     * 初始化数据
     *
     * @param bundle 界面跳转携带的参数
     */
    open fun initParam(bundle: Bundle?) {}

    /**
     * 初始化窗口属性
     */
    abstract fun initWindowAttributes()

    /**
     * 获取资源文件Id
     *
     * @return
     */
    open fun layoutResId(): Int {
        return -1;
    }


    /**
     * 绑定布局文件
     *
     * @param view 布局文件资源ID
     */
    open fun bindLayoutResId(layoutResId: Int) {}

    open fun bindLayoutView() {}

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化监听事件
     */
    abstract fun initListener()


    open fun showLoading(message: String = "") {
        mLoadingUtil.showLoading(text = message)
    }

    open fun hideLoading() {
        mLoadingUtil.hideLoading()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}