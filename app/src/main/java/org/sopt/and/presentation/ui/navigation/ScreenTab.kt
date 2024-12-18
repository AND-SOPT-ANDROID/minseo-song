package org.sopt.and.presentation.ui.navigation

import org.sopt.and.R

enum class ScreenTab(
    val route: String,
    val selectedImage: Int,
    val unselectedImage: Int,
    val selectedColor: Int,
    val unselectedColor: Int,
    val label: String
) {
    HOME(
        route = Routes.Home.route,
        selectedImage = R.drawable.outline_home_24_white,
        unselectedImage = R.drawable.outline_home_24_gray,
        selectedColor = R.color.white,
        unselectedColor = R.color.gray,
        label = "홈"
    ),
    SEARCH(
        route = Routes.Search.route,
        selectedImage = R.drawable.outline_search_24_white,
        unselectedImage = R.drawable.outline_search_24_gray,
        selectedColor = R.color.white,
        unselectedColor = R.color.gray,
        label = "검색"
    ),
    MY_PAGE(
        route = Routes.My.route,
        selectedImage = R.drawable.img_profile_select,
        unselectedImage = R.drawable.img_profile_select,
        selectedColor = R.color.white,
        unselectedColor = R.color.gray,
        label = "MY"
    )
}