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
package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.data.ageString
import com.example.androiddevchallenge.data.cats
import com.example.androiddevchallenge.ui.components.AppCatBanner
import com.example.androiddevchallenge.ui.components.AppChipText

@Composable
fun DetailScreen(catId: Int, navController: NavHostController) {
    val cat = cats.find { it.id == catId }
    if (cat == null) {
        InvalidCatView(navController = navController)
        return
    }
    Column {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 0.dp) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
        }
        AppCatBanner(cat = cat, size = 250.dp, modifier = Modifier.padding(top = 16.dp))
        CatDetails(cat)
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(text = "Adopt Me")
        }
    }
}

@Composable
fun CatDetails(cat: Cat) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = Modifier.weight(2f)) {
                Text(text = cat.breed)
                Text(
                    text = cat.name,
                    style = MaterialTheme.typography.h4
                )
            }
            Text(
                text = "\$${cat.price}",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }
        Text(text = cat.ageString(), modifier = Modifier.padding(top = 16.dp))
        Text(text = cat.description, modifier = Modifier.padding(top = 16.dp))
        LazyRow(modifier = Modifier.padding(end = 4.dp, top = 16.dp)) {
            items(cat.tag) {
                AppChipText(text = it.type, modifier = Modifier.padding(end = 4.dp))
            }
        }
    }
}

@Composable
fun InvalidCatView(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Oops! That cat is not available.",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
        OutlinedButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { navController.navigateUp() }
        ) {
            Text(text = "Go Back")
        }
    }
}
