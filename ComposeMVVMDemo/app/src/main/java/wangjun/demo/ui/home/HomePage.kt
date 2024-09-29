package wangjun.demo.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import wangjun.demo.R
import wangjun.demo.model.bean.Article
import wangjun.demo.ui.hot.HotPage

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/21
 *   desc : 首页视图
 * </pre>
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(navController: NavController, onClickArticle: (Article) -> Unit) {

    val pages = remember {
        listOf("热门")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

        val coroutineScope = rememberCoroutineScope()

        //val loopingCount = Int.MAX_VALUE
        val loopingCount = 1
        val startIndex = loopingCount / 2
        val pagerState = rememberPagerState(initialPage = startIndex)

        fun pageMapper(index: Int): Int {
            return (index - startIndex).floorMod(pages.count())
        }

        val currentIndex: State<Int> = remember {
            derivedStateOf { pageMapper(pagerState.currentPage) }
        }

        TabRow(selectedTabIndex = currentIndex.value,
            backgroundColor = colorResource(R.color.colorPrimary),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions,
                        ::pageMapper
                    )
                )
            }) {
            pages.forEachIndexed { index, title ->
                Tab(text = { Text(title) }, selected = currentIndex.value == index, onClick = {
                    coroutineScope.launch {
                        when {
                            currentIndex.value > index -> {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - (currentIndex.value - index)
                                )
                            }
                            currentIndex.value < index -> {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + (index - currentIndex.value)
                                )
                            }
                        }
                    }
                })
            }
        }

        HorizontalPager(
            pageCount = loopingCount, state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { index ->
            when (pageMapper(index)) {
                0 -> HotPage(onClickArticle = onClickArticle)
            }
        }
    }
}

fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}