package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
            onClick = {}, modifier = Modifier
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
            onClick = { navController.navigateUp() }) {
            Text(text = "Go Back")
        }
    }
}