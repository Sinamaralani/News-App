package com.example.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.common.ArticlesList
import com.example.newsapp.presentation.common.Dimens.MediumPadding1

@Composable
fun BookmarkScreen(state: BookmarkState, navigateToDetails: (Article) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = MediumPadding1, top = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.text_title)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            articles = state.article,
            onClick = { navigateToDetails(it) }
        )

    }
}

