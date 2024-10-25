package wangjun.demo.model.bean

import java.io.Serializable

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/26
 *   desc : 文章列表数据类
 * </pre>
 */
data class ArticleList( val offset: Int,
                        val size: Int,
                        val total: Int,
                        val pageCount: Int,
                        val curPage: Int,
                        val over: Boolean,
                        val datas: List<Article>):Serializable