/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        modifier = modifier.clickable { onCheckedChange(!checked) }
    )
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
