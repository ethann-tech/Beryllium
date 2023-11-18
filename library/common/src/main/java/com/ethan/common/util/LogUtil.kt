package com.ethan.common.util

class LogUtil {


    companion object {
        private var isDebug: Boolean = false
        private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this::class.java)


        fun <T> info(prefix:String,vararg content:T){
           if (isDebug){
               mLogger.info(prefix,content)
           }
        }



    }
}