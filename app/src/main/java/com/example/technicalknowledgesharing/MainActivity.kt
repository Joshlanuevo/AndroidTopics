package com.example.technicalknowledgesharing

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.technicalknowledgesharing.ui.theme.TechnicalKnowledgeSharingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechnicalKnowledgeSharingTheme {
                CoroutineBuildersDemo(mainViewModel)
            }
        }

        // Column
        // Row
        // LazyColumn

        // LaunchedEffect
        // rememberCoroutineScope

        // SideEffect
        // DisposableEffect
        // rememberUpdatedState

        // Coroutine Builders
        // launch
        // async
        // runBlocking
        // withContext
    }
}

@Composable
fun CoroutineBuildersDemo(viewModel: MainViewModel) {
    var seconds by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            seconds++
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { viewModel.asyncTask() }) {
            Text(text = "Task")
        }

//        Text(text = "Seconds: $seconds", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun SideEffectDemo() {
    val context = LocalContext.current
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var text by remember { mutableStateOf("") }
    val latestText by rememberUpdatedState(newValue = text)

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            Log.d("Test!!!", "Latest text value: $text")
        }
    }

//    SideEffect {
//        Log.d("Test!!!", "Text value: $text")
//    }

//    DisposableEffect(key1 = text) {
//        Log.d("Test!!!", "Initialized")
//        onDispose {
//            if (text.length > 4) {
//                Log.d("Test!!!", "Clean Text")
//                text = ""
//            }
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text(text = "Enter Text") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {}) {
                Text("Side Effect")
            }
        }
    }
}

@Composable
fun ColumnAndRowDemo()  {
    val time = measureTimeMillis {
        val scrollable = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(top = 40.dp)
                .horizontalScroll(scrollable)
                .fillMaxWidth()
        ) {
            for (i in 1..1000) {
                ItemExample(name = "Item $i")
            }
//    ItemExample(name = "Item 1")
//    ItemExample(name = "Item 2")
//    ItemExample(name = "Item 3")
//    ItemExample(name = "Item 4")
//    ItemExample(name = "Item 5")
        }
    }

    Log.d("Performance-Test-Column", "Column initial composition time: ${time}")

}


@Composable
fun LazyColumnDemo() {
    val time = measureTimeMillis {
        LazyColumn {
            item {
                LazyRow {
                    items(10) {
                        Text(
                            text = "Item: $it",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }

    Log.d("Performance-Test-LazyColumn", "LazyColumn initial composition time: ${time}")
}

@Composable
fun ItemExample(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(10.dp)
    )
}


