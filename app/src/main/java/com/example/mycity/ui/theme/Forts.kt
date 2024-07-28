package com.example.mycity.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.Forts
import com.example.mycity.data.Temples

@Preview
@Composable
fun FortsItem(
    modifier: Modifier = Modifier,
    forts: Forts = Forts(R.drawable.redfort,"Red Fort", R.string.red_fort_description)
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
            FortsHeader(
                fortsHeader = forts.fortName, modifier =
                Modifier.padding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
            )

            FortsItemButton(forts.image,expanded = expanded,
                onClick = { expanded = ! expanded })
            if (expanded) {
                FortsInformation(
                    forts.fortDescription,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                )
            }

//            }
        }

    }

}
@Composable
fun FortsHeader(
    fortsHeader: String,
    modifier: Modifier = Modifier
){
    Text(
        text = fortsHeader,
        modifier = modifier
    )
}

@Composable
private fun FortsItemButton(
    @DrawableRes imageResource: Int,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(onClick = onClick, contentPadding = PaddingValues(0.dp),
        shape = MaterialTheme.shapes.large,
        modifier = modifier) {
        Image(modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.image_size))
            .padding(dimensionResource(id = R.dimen.padding_0))
            .clip(MaterialTheme.shapes.large),
            contentDescription = null,
            painter = painterResource(imageResource),
            contentScale = ContentScale.Crop,

            )
    }
}
@Composable
fun FortsInformation(
    @StringRes fortsInformation: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(fortsInformation),
        )

    }
}
