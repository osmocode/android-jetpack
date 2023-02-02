package fr.uge.plutus.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.selects.select


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntTopBar(navController : NavHostController){
    val pagerState = rememberPagerState()
    val pages = listOf("Transaction", "Categories", "Summary")




    val indicator = @Composable {tabPosition : List<TabPosition> ->
        CustomIndicator(tabPosition, pagerState)
    }

    TabRow(
        modifier = Modifier.height(50.dp),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Ant.colors.nav_background,
        indicator = indicator)
    {
        pages.forEachIndexed{index, title ->
           val active = pagerState.currentPage == index
            Tab(
                modifier = Modifier.zIndex(6f),
                selected = pagerState.currentPage == index,
                onClick = { /*TODO*/ }) {
                Text(text = title, color = if(active) { Ant.colors.nav_item} else {Ant.colors.nav_item_focus})
            }
        }
    }


    HorizontalPager(
        modifier = Modifier.zIndex(2f),
        count = pages.size,
        state = pagerState)
    { page ->
        Box(modifier = Modifier.fillMaxSize()){
            Text(modifier = Modifier.align(Alignment.Center), text = "Page $page")
        }
    }
}



@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CustomIndicator(tabPositions: List<TabPosition>, pagerState: PagerState){
    val transition = updateTransition(targetState = pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(transitionSpec = {
        if(initialState < targetState){
            spring(dampingRatio = 1f, stiffness = 50f)
        }else{
            spring(dampingRatio = 1f, stiffness = 1000f)
        }
    }, label = "")
    {
        tabPositions[it].left
    }


    val indicatorEnd by transition.animateDp(transitionSpec = {
        if(initialState < targetState){
            spring(dampingRatio = 1f, stiffness = 1000f)
        }else{
            spring(dampingRatio = 1f, stiffness = 50f)
        }
    }, label = "")
    {
        tabPositions[it].right
    }

    Box(modifier = Modifier
        .offset(x = indicatorStart)
        .wrapContentSize(align = Alignment.BottomStart)
        .width(indicatorEnd - indicatorStart)
        .padding(2.dp)
        .fillMaxSize()
        .background(color = Ant.colors.nav_item_focus, shape = RoundedCornerShape(50))
        .border(border = BorderStroke(2.dp, color = Ant.colors.nav_item_focus), shape = RoundedCornerShape(50))
        .zIndex(1f)
    )
}