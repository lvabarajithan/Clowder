package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.black
import com.example.androiddevchallenge.ui.theme.grey

@Composable
fun AppChip(
    text: String,
    checked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (checked) MaterialTheme.colors.primary else grey
    val textColor = if (checked) MaterialTheme.colors.onPrimary else black
    AppChipText(
        text = text,
        color = textColor,
        backgroundColor = bgColor,
        modifier = modifier.clickable { onCheckedChange(!checked) })
}

@Composable
fun AppChipText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = black,
    backgroundColor: Color = grey
) {
    Text(
        text = text,
        color = color,
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(percent = 50))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}