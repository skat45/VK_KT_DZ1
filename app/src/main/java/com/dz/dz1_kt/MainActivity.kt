package com.dz.dz1_kt

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.dz.dz1_kt.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            renderActivity()
        }
    }
}


var elementsNumber: Int = 0;

@Composable
fun addButton(onClick: () -> Unit) {
    Button(onClick = onClick) {Text(text = btnText)}
}


@Composable
fun addBoxItem(number: Int) {
    Box(
        modifier = Modifier
            .size(boxSize)
            .background(if (number % 2 == 0) Color.Red else Color.Blue)
            .border(elementBorderWidth, Color.White)
    ) {
        Text(text = (number + 1).toString(), color = Color.White,
            modifier = Modifier.align(Alignment.Center))
    }
}


@Composable
fun renderActivity() {
    var boxNumber by remember { mutableStateOf(elementsNumber) }
    var numOfColumns: Int
    val configuration = LocalConfiguration.current
    numOfColumns = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {4}
        else -> {3}
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(numOfColumns),
        contentPadding = PaddingValues(paddingLazyVerticalGrid),
    ) {
        items(boxNumber) {addBoxItem(number =  it)}

        items(2 * numOfColumns - boxNumber % numOfColumns - 1) {}

        item { addButton(onClick = {
            elementsNumber++
            boxNumber = elementsNumber
        }) }
    }
}


