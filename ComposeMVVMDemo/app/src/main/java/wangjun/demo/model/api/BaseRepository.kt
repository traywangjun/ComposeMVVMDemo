package wangjun.demo.model.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import wangjun.demo.model.bean.Result
import wangjun.demo.model.bean.Response
import java.io.IOException

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/26
 *   desc : Repository基类
 * </pre>
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Response<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(
        response: Response<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }


}