package fr.uge.plutus.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*

import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.launch

open class AntTabBarItem(
    val label: String,
    val content: @Composable (page: Int) -> Unit
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntPagerLayout(
    navController: NavHostController,
    pages: List<AntTabBarItem>,

) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            contentColor = Ant.colors.nav_item,
            backgroundColor = Ant.colors.nav_background,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                AntPagerLayoutIndicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            modifier = Modifier
                .height(42.dp)
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            pages.forEachIndexed { index, page ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier.zIndex(1f),
                    selected = selected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = Ant.colors.nav_background,
                    unselectedContentColor = Ant.colors.nav_item_focus
                ){
                    Text(text = page.label)
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            modifier = Modifier
                .fillMaxSize(),
            content = { page ->
                AntPagerLayoutPage(
                    page = page,
                    pages = pages
                )
            }
        )
    }
}

@Composable
fun AntPagerLayoutIndicator(
    modifier: Modifier = Modifier,
    color: Color = Ant.colors.nav_item_focus,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(3.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(color = color)
    )
}

@Composable
fun AntPagerLayoutPage(
    page: Int,
    pages: List<AntTabBarItem>
) {
    pages[page].content(page)
}
