package com.example.kabar.presentation.onBoarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kabar.R
import com.example.kabar.presentation.Dimes.mediumPadding1
import com.example.kabar.presentation.Dimes.mediumPadding2
import com.example.kabar.presentation.onBoarding.Page
import com.example.kabar.presentation.onBoarding.pages
import com.example.kabar.ui.theme.KabarTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page:Page
){
    Column(modifier = modifier){
        Image(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(mediumPadding1))

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = mediumPadding2).padding(bottom = 10.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),//we can change fontWeight on this style so we use copy()
            color = colorResource(id = R.color.display_small)

        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = mediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPagePreview(){
    KabarTheme {
        OnBoardingPage(
            page = pages[0]
        )
    }
}