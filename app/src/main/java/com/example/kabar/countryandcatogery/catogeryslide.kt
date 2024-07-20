package com.example.kabar.countryandcatogery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kabar.ui.theme.KabarTheme


enum class Category(val title: String) {
    ALL("All"),
    GAMING("Gaming"),
    SPORTS("Sports"),
    ADULT1("Adult1"),
    ADULT2("Adult2"),
    ADULT3("Adult3"),
    ADULT4("Adult4")
}

@Composable
fun CategorySelectionBar(onCategorySelected: (Category) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(Category.values().size) { index ->
            val category = Category.values()[index]
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onCategorySelected(category) }
                    .background(Color.Gray, shape = shapes.medium)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = category.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Gray)
                        .clickable { onCategorySelected(category) }
                        .padding(8.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun previ(){
    KabarTheme {
        CategorySelectionBar{}
    }
}