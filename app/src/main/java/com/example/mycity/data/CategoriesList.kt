package com.example.mycity.data

import com.example.mycity.R

object CategoriesList {
    val defaultCategory = getCategoryData()[0]

    fun getCategoryData(): List<Categories> {
        return listOf(
        Categories(1,true,R.drawable.indiagate, "Monuments"),
        Categories(2,true,R.drawable.snajayvan,"Parks"),
        Categories(3,true,R.drawable.hauzkhasfort,"Forts"),
        Categories(4,true,R.drawable.akshardhamtemple,"Temples")
        )
    }

    val monumentsList = listOf(

        Monuments(R.drawable.rashtrapatibhawan,"Rashtrapati Bhawan", R.string.rashtrapati_bhawan_description),
        Monuments(R.drawable.indiagate,"India Gate",  R.string.india_gate_description),
        Monuments(R.drawable.qutubminar,"Qutub Minar", R.string.qutub_minar_description),
    )
    val parksList = listOf(
        Parks(R.drawable.lodhigarden,"Lodhi Garden", R.string.lodhi_garden_description),
        Parks(R.drawable.mughalgarden,"Mughal Garden", R.string.mughal_garden_description),
        Parks(R.drawable.snajayvan,"Sanjay Van", R.string.snajayvan_description)
    )
    val fortsList = listOf(
        Forts(R.drawable.redfort,"Red Fort", R.string.red_fort_description),
        Forts(R.drawable.tughlakabad,"Tughlakabad Fort", R.string.tughlakabad_fort_description),
        Forts(R.drawable.hauzkhasfort,"Hauz Khas Fort ", R.string.hauz_khas_fort_description),

    )
    val templesList = listOf(
        Temples(R.drawable.isckontemple,"Isckon Temple", R.string.isckon_temple_description),
        Temples(R.drawable.lotustemple,"Lotus Temple", R.string.lotus_temple_description),
        Temples(R.drawable.akshardhamtemple,"Akshardham Temple", R.string.akshardham_temple_description)
    )
}