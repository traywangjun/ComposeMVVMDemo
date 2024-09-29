package wangjun.demo.model.repository

import wangjun.demo.model.api.BaseRepository
import wangjun.demo.model.api.RetrofitClient
import wangjun.demo.model.bean.ArticleList
import wangjun.demo.model.bean.Result
import javax.inject.Inject

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/3/1
 *   desc : 首页数据仓库
 * </pre>
 */
class HomeRepository @Inject constructor() : BaseRepository() {

    suspend fun getArticleList(page: Int): Result<ArticleList> {
        return safeApiCall(call = { requestArticleList(page) }, errorMessage = "")
    }

    private suspend fun requestArticleList(page: Int): Result<ArticleList> =
        executeResponse(RetrofitClient.service.getHomeArticles(page))

}