package wangjun.demo.model.bean

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/26
 *   desc : 接口返回结果
 * </pre>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}