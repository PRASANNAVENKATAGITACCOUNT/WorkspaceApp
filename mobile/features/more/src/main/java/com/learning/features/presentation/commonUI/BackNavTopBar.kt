package com.learning.features.presentation.commonUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.features.R

@Composable
fun BackNavTopBar(
    onBackPress:()-> Unit={},
    shareData:()-> Unit={},
    menuButton:()-> Unit={}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(55.dp).padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onBackPress.invoke()
        }) {
            Icon(painter = painterResource(
                R.drawable.outline_arrow_back_ios_new_24),
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
        }

        Row (
            modifier = Modifier.fillMaxHeight().wrapContentWidth()
        ){
            IconButton(onClick = {
                shareData.invoke()
            }) {
                Icon(painter = painterResource(R.drawable.outline_ios_share_24), contentDescription = "")
            }
            HorizontalDivider(modifier = Modifier.width(50.dp))
            IconButton(onClick = {
                menuButton.invoke()
            }) {
                Icon(painter = painterResource(R.drawable.outline_more_horiz_24_3_dot_icon), contentDescription = "")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BavNavPrev() {
        BackNavTopBar()

}