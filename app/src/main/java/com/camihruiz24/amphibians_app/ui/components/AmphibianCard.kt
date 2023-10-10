package com.camihruiz24.amphibians_app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.camihruiz24.amphibians_app.R
import com.camihruiz24.amphibians_app.data.Amphibian

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier,
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.large
    ) {

        var expanded: Boolean by remember { mutableStateOf(false) }
        val itemColor by animateColorAsState(
            targetValue = when (expanded) {
                true -> MaterialTheme.colorScheme.inversePrimary
                false -> MaterialTheme.colorScheme.primaryContainer
            },
            label = "Color animation"
        )

        Column(
            Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow,
                    )
                )
                .background(color = itemColor)
                .clickable { expanded = !expanded }
        ) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.frog),
                error = painterResource(id = R.drawable.frog),
                modifier = Modifier.fillMaxWidth()
            )
            if (expanded)
                Text(
                    text = amphibian.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
        }
    }
}

@Preview
@Composable
fun AmphibianItemPreview() {
    val amphibian = Amphibian(
        name = "Great Basin Spadefoot",
        type = "Toad",
        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
    )
    AmphibianCard(
        amphibian = amphibian
    )
}
