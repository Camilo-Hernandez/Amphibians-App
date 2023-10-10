package com.camihruiz24.amphibians_app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import com.camihruiz24.amphibians_app.R
import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.ui.components.AmphibianCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    uiState: UiState<List<Amphibian>>,
    retry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        when (uiState) {
            is UiState.Success -> ListScreen(amphibianList = uiState.info)
            UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> ErrorScreen(retry = retry, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListScreen(amphibianList: List<Amphibian>) {

    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    AnimatedVisibility(
        modifier = Modifier,
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy),
        ),
        exit = fadeOut(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(items = amphibianList) { index, amphibian ->
                AmphibianCard(
                    amphibian = amphibian,
                    Modifier.animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow,
                                dampingRatio = Spring.DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * (index + 1) } // staggered entrance
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(retry: () -> Unit, modifier: Modifier) {
    Column(modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = null,
        )
        Button(onClick = retry) {
            Text(text = "Retry")
        }
    }
}
