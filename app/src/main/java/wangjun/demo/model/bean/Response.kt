package wangjun.demo.model.bean

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/26
 *   desc : 文章列表数据类
 * </pre>
 */
data class Response<out T>(val errorCode: Int, val errorMsg: String, val data: T)

suspend fun <T : Any> Response<T>.executeResponse(successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                                  errorBlock: (suspend CoroutineScope.() -> Unit)? = null): Result<T> {
    return coroutineScope {
        if (errorCode == -1) {
            errorBlock?.let { it() }
            Result.Error(IOException(errorMsg))
        } else {
            successBlock?.let { it() }
            Result.Success(data)
        }
    }
}

suspend fun <T : Any> Response<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): Response<T> {
    return coroutineScope {
        if (errorCode != -1) successBlock?.invoke(this, this@doSuccess.data)
        this@doSuccess
    }

}

suspend fun <T : Any> Response<T>.doError(errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null): Response<T> {
    return coroutineScope {
        if (errorCode == -1) errorBlock?.invoke(this, this@doError.errorMsg)
        this@doError
    }
}

