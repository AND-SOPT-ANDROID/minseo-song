package org.sopt.and.ui.home.model

data class HomeImage(
    val title: String,
    val image: String
)

fun getHomeTopBanner(): List<HomeImage> = listOf(
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231207/0454/P000940460.jpg/dims/resize/F_webp,400"
    ),
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231218/1141/P000821660.jpg/dims/resize/F_webp,400"
    ),
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20220520/P001602837.jpg/dims/resize/F_webp,400"
    ),
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240311/1030/P001516937.jpg/dims/resize/F_webp,400"
    ),
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231228/0220/P001751353.jpg/dims/resize/F_webp,400"
    ),
    HomeImage(
        title = "",
        image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1120/ko/20240720/0254/P001758020.jpg/dims/resize/F_webp,400"
    )
)