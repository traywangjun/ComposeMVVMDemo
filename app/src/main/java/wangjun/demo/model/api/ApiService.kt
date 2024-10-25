package wangjun.demo.model.api

import wangjun.demo.model.bean.*
import retrofit2.http.*

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/25
 *   desc : AppApi
 * </pre>
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): Response<ArticleList>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): Response<User>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") userName: String, @Field("password") passWord: String, @Field("repassword") rePassWord: String): Response<User>

}