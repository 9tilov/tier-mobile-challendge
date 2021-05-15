package com.d9tilov.android.tiertestapp.base.ui.wrapper

data class ResultData<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ResultData<T> {
            return ResultData(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): ResultData<T> {
            return ResultData(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): ResultData<T> {
            return ResultData(Status.LOADING, data, null)
        }
    }
}
