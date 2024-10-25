package wangjun.demo.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import wangjun.demo.R
import wangjun.demo.model.bean.Article
import wangjun.demo.ui.home.HomePage
import wangjun.demo.ui.profile.ProfilePage
import wangjun.demo.ui.web.WebPage

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/21
 *   desc :  底部导航栏视图
 * </pre>
 */
@Composable
fun AndroidPage() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding)
    }
}

val items = listOf(
    BottomNavItem.Home,
    BottomNavItem.Profile
)

@Composable
fun BottomNavigation(navController: NavController) {
    androidx.compose.material.BottomNavigation(
        backgroundColor =colorResource(R.color.colorPrimary),
        contentColor = colorResource(R.color.white),
        modifier = Modifier.navigationBarsPadding()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(item.icon),
                        contentDescription = stringResource(item.title)
                    )
                },
                label = { Text(text = stringResource(item.title), fontSize = 9.sp) },
                selectedContentColor = colorResource(R.color.white),
                unselectedContentColor = colorResource(R.color.white).copy(0.4f),
                alwaysShowLabel = true,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, innerPadding: PaddingValues) {

    val onClickArticle = { article: Article ->
        val args = listOf(Pair("url", article.link))
        navController.navigateAndArgument(Route.Web, args)
    }

    NavHost(
        navController = navController, startDestination = BottomNavItem.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomNavItem.Home.route) {
            HomePage(navController, onClickArticle)
        }
        composable(BottomNavItem.Profile.route) {
            ProfilePage()
        }
        composable(route = Route.Web) {
            val url = it.arguments?.getString("url") ?: ""
            WebPage(url, navController)
        }
    }
}

sealed class BottomNavItem(val title: Int, val icon: Int, val route: String) {
    object Home : BottomNavItem(R.string.home, R.drawable.ic_home_black_24dp, "home")
    object Profile : BottomNavItem(R.string.me, R.drawable.ic_profile, "profile")
}

object Route {
    const val SystemDetail: String = "systemDetail"
    const val Web = "web"
}

fun NavController.navigateAndArgument(
    route: String,
    args: List<Pair<String, Any>>? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,

    ) {
    navigate(route = route, navOptions = navOptions, navigatorExtras = navigatorExtras)

    if (args.isNullOrEmpty()) {
        return
    }

    val bundle = backQueue.lastOrNull()?.arguments
    if (bundle != null) {
        bundle.putAll(bundleOf(*args.toTypedArray()))
    } else {
        println("The last argument of NavBackStackEntry is NULL")
    }
}

