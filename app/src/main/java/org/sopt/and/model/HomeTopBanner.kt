package org.sopt.and.model

data class HomeTopBanner(
    val title: String,
    val image: String
)

fun getHomeTopBanner(): List<HomeTopBanner> = listOf(
    HomeTopBanner(
        title = "",
        image = "https://media.themoviedb.org/t/p/w600_and_h900_bestv2/c2JXlWzvXegSda8qaATr8I47kMx.jpg"
    ),
    HomeTopBanner(
        title = "",
        image = "https://media.themoviedb.org/t/p/w600_and_h900_bestv2/4Zb4Z2HjX1t5zr1qYOTdVoisJKp.jpg"
    ),
    HomeTopBanner(
        title = "",
        image = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true"
    ),
    HomeTopBanner(
        title = "",
        image = "https://img.imbc.com/adams/Program/20149/130552234491328038.jpg"
    ),
    HomeTopBanner(
        title = "",
        image = "https://i.namu.wiki/i/Up0fqwVo0924M6yoE9DInupC4_D0Jgon10AUN4Up3XgwtgLe96wQ3aqMDnwyntpD174HobyE91BEt8G33JTa1Q.webp"
    ),
    HomeTopBanner(
        title = "",
        image = "https://www.themoviedb.org/t/p/w1280/3flIDcZF3tnR7m5OU2h7lLPQwmr.jpg"
    )
)