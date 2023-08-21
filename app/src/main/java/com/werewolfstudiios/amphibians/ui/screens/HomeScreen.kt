package com.werewolfstudiios.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.werewolfstudiios.amphibians.R
import com.werewolfstudiios.amphibians.network.Amphibian

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibianUiState.Success -> AmphibiansList(amphibianUiState.amphibians, modifier)
        is AmphibianUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun AmphibiansList(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(amphibians, key = { amp -> amp.name }) { amphibian ->
            AmphibianCard(amphibian, modifier = modifier)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .padding(8.dp)
            .padding(bottom = 8.dp)
    ) {
        Column(
//            modifier = modifier.padding(16.dp)
        ) {
            Text(
                stringResource(
                    R.string.amphibian_name,
                    formatArgs = arrayOf(amphibian.name, amphibian.type)
                ),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.app_name),
                modifier = modifier.fillMaxWidth()
            )
            Text(
                amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) { Text(stringResource(R.string.retry)) }
    }
}

@Preview
@Composable
fun AmphibianCardPreview() {
    val amphibian = Amphibian(
        name = "An Amphibian",
        type = "Toad",
        imgSrc = "imageUrl",
        description = "A small little unnecessarily long, but not enough, description"
    )
    AmphibianCard(amphibian = amphibian)
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AmphibianListPreview() {
    val amphibians = listOf(
        Amphibian(
            name = "An Amphibian",
            type = "Toad",
            imgSrc = "imageUrl",
            description = "A small little unnecessarily long, but not enough, description"
        ),
        Amphibian(
            name = "Second Amphibian",
            type = "Toad",
            imgSrc = "imageUrl2",
            description = "A small little unnecessarily long, but not enough, description, for the second one"
        ),
        Amphibian(
            name = "Another Amphibian",
            type = "Toad",
            imgSrc = "imageUrl3",
            description = "A small little unnecessarily long, but not enough, description, wait, again?"
        )
    )
    AmphibiansList(amphibians)
}