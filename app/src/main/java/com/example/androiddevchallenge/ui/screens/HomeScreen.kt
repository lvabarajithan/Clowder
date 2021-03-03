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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.data.Tag
import com.example.androiddevchallenge.data.ageString
import com.example.androiddevchallenge.data.cats
import com.example.androiddevchallenge.ui.components.AppCard
import com.example.androiddevchallenge.ui.components.AppCatBanner
import com.example.androiddevchallenge.ui.components.AppChip
import com.example.androiddevchallenge.ui.components.AppTextField

@Composable
fun HomeScreen(title: String, navController: NavHostController) {
    val searchText = rememberSaveable { mutableStateOf("") }
    val filteredTags = rememberSaveable { mutableStateOf(Tag.values().map { false }) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 16.dp)
        )
        SearchBox(searchText.value) { searchText.value = it }
        TagList(Tag.values(), filteredTags.value) { index ->
            val modified = filteredTags.value.toMutableList()
            modified[index] = !modified[index]
            filteredTags.value = modified
        }

        val allTags = Tag.values()
        var filteredCats: List<Cat> = cats
        if (filteredTags.value.contains(true)) {
            val filterableTags = mutableListOf<Tag>()
            filteredTags.value.forEachIndexed { index, isChecked ->
                if (isChecked) {
                    filterableTags.add(allTags[index])
                }
            }
            filteredCats = filteredCats.filter {
                it.tag.intersect(filterableTags.asIterable()).isNotEmpty()
            }
        }
        if (searchText.value.isNotEmpty()) {
            filteredCats = filteredCats.filter { it.name.contains(searchText.value, true) }
        }
        CatList(filteredCats, navController)
    }
}

@Composable
fun SearchBox(text: String, onTextChange: (String) -> Unit) {
    AppTextField(
        text = text,
        onTextChange = { onTextChange(it) },
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun TagList(tags: Array<Tag>, filterTags: List<Boolean>, onFilterChange: (index: Int) -> Unit) {
    LazyRow(modifier = Modifier.padding(bottom = 16.dp)) {
        itemsIndexed(tags) { index, tag ->
            val checkedState = remember { mutableStateOf(filterTags[index]) }
            AppChip(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = tag.type,
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    onFilterChange(index)
                }
            )
        }
    }
}

@Composable
fun CatList(cats: List<Cat>, navController: NavHostController) {
    LazyColumn {
        items(cats) { cat ->
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = MaterialTheme.colors.secondary
            ) {

                Column(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("details/${cat.id}")
                        }
                        .padding(16.dp)
                ) {
                    AppCatBanner(cat = cat)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                        Column(modifier = Modifier.weight(2f)) {
                            Text(
                                text = cat.name,
                                style = MaterialTheme.typography.h4
                            )
                            Text(
                                text = cat.ageString(),
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                        Text(
                            text = "\$${cat.price}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
