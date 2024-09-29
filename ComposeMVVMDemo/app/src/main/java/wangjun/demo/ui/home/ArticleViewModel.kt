package wangjun.demo.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wangjun.demo.base.BaseViewModel
import wangjun.demo.model.bean.Article
import wangjun.demo.model.bean.ArticleList
import wangjun.demo.model.bean.Result
import wangjun.demo.model.repository.*
import javax.inject.Inject

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/27
 *   desc : 文章视图模型
 * </pre>
 */
@HiltViewModel
open class ArticleViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var homeRepository: HomeRepository

    sealed class ArticleType {
        object Home : ArticleType()                 // 首页
    }

    private val _uiState = MutableLiveData<ArticleUiModel>()
    val uiState: LiveData<ArticleUiModel>
        get() = _uiState

    private var currentPage = 0

    val lazyListState = LazyListState()
    private val allArticleList = arrayListOf<Article>()

    val refreshHome: () -> Unit = { getHomeArticleList(true) }

    fun getHomeArticleList(isRefresh: Boolean = false) = getArticleList(ArticleType.Home, isRefresh)


    private fun getArticleList(
        articleType: ArticleType,
        isRefresh: Boolean = false,
        cid: Int = 0,
        key: String = ""
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            emitArticleUiState(isRefresh, showSuccess = allArticleList, showEnd = true)
            if (isRefresh) {
                allArticleList.clear()
                currentPage =  0
            }

            val result: Result<ArticleList> = when (articleType) {
                ArticleType.Home -> homeRepository.getArticleList(currentPage)
            }

            if (result is Result.Success) {
                val articleList = result.data
                if (articleList.offset >= articleList.total || articleList.datas.size == articleList.total) {
                    emitArticleUiState(
                        showLoading = false,
                        showEnd = true,
                        showSuccess = allArticleList.apply { addAll(articleList.datas) })
                    return@launch
                }
                currentPage++
                emitArticleUiState(
                    showLoading = false,
                    showSuccess = allArticleList.apply { addAll(articleList.datas) },
                    isRefresh = isRefresh
                )
            } else if (result is Result.Error) {
                emitArticleUiState(showLoading = false, showError = result.exception.message)
            }
        }
    }

    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: List<Article>? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null
    ) {
        val uiModel =
            ArticleUiModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
        _uiState.value = uiModel
    }


    data class ArticleUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: List<Article>?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null,
        val listState: LazyListState = LazyListState()
    )


}