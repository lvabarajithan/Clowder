package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Cat

@Composable
fun AppCatBanner(cat: Cat, size: Dp = 172.dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AppCard(
            modifier = Modifier
                .size((size.div(1.3f)))
                .rotate(-25f),
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            content = {}
        )
        Image(
            modifier = Modifier
                .size(size)
                .padding(16.dp),
            painter = painterResource(id = cat.imageId),
            contentDescription = cat.name,
        )
    }
}