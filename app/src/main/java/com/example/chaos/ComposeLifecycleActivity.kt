package com.example.chaos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

data class ComposeEvent(
    val event: String,
    val timestamp: String,
    val color: Color
)

class ComposeLifecycleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ComposeLifecycle", "Activity onCreate")
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposeLifecycleScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeLifecycleScreen(modifier: Modifier = Modifier) {
    var showExamples by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "🎨 Compose Lifecycle",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Composable functions have their own lifecycle",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Compose Lifecycle Diagram
        ComposeLifecycleDiagram()

        Spacer(modifier = Modifier.height(24.dp))

        // Key Concepts
        Text(
            text = "🔑 Key Concepts",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        ComposeConceptCard(
            title = "Composition",
            emoji = "📝",
            description = "When Compose executes composables and builds UI tree",
            color = Color(0xFF4CAF50)
        )

        ComposeConceptCard(
            title = "Recomposition",
            emoji = "🔄",
            description = "Re-executing composables when state changes",
            color = Color(0xFF2196F3)
        )

        ComposeConceptCard(
            title = "Decommission",
            emoji = "🗑️",
            description = "When composable leaves composition (cleanup)",
            color = Color(0xFFF44336)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Side Effects
        Text(
            text = "⚡ Side-Effect APIs",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        SideEffectCard(
            name = "LaunchedEffect",
            emoji = "🚀",
            description = "Run suspend functions when composable enters composition",
            example = "LaunchedEffect(key) {\n  // Coroutine code\n  delay(1000)\n}",
            useCase = "API calls, animations, timers"
        )

        SideEffectCard(
            name = "DisposableEffect",
            emoji = "🧹",
            description = "Setup and cleanup effects when entering/leaving",
            example = "DisposableEffect(key) {\n  // Setup\n  onDispose {\n    // Cleanup\n  }\n}",
            useCase = "Listeners, observers, resources"
        )

        SideEffectCard(
            name = "remember",
            emoji = "💾",
            description = "Store value across recompositions (NOT config changes)",
            example = "val state = remember {\n  mutableStateOf(0)\n}",
            useCase = "UI state, temporary data"
        )

        SideEffectCard(
            name = "rememberSaveable",
            emoji = "💿",
            description = "Store value across recompositions AND config changes",
            example = "val state = rememberSaveable {\n  mutableStateOf(0)\n}",
            useCase = "User input, scroll position"
        )

        SideEffectCard(
            name = "SideEffect",
            emoji = "⚠️",
            description = "Execute on every successful recomposition",
            example = "SideEffect {\n  // Runs after composition\n}",
            useCase = "Analytics, non-Compose updates"
        )

        SideEffectCard(
            name = "derivedStateOf",
            emoji = "📊",
            description = "Calculate state from other states efficiently",
            example = "val derived = remember {\n  derivedStateOf { ... }\n}",
            useCase = "Computed values, filtering"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Interactive Demo
        Text(
            text = "🔬 Interactive Demo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Button(
            onClick = { showExamples = !showExamples },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (showExamples) "Hide Examples" else "Show Live Examples")
        }

        if (showExamples) {
            Spacer(modifier = Modifier.height(16.dp))

            // Example 1: LaunchedEffect
            LaunchedEffectExample()

            Spacer(modifier = Modifier.height(12.dp))

            // Example 2: DisposableEffect
            DisposableEffectExample()

            Spacer(modifier = Modifier.height(12.dp))

            // Example 3: remember vs rememberSaveable
            RememberExample()
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ComposeLifecycleDiagram() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Composable Lifecycle",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            ComposeLifecycleNode("Enter Composition", "📝", Color(0xFF4CAF50))
            ComposeArrow("First time composable is called")

            ComposeLifecycleNode("Recomposition", "🔄", Color(0xFF2196F3))
            ComposeArrow("State changes trigger re-execution")

            ComposeLifecycleNode("Leave Composition", "🗑️", Color(0xFFF44336))
            ComposeArrow("Composable is removed from UI")
        }
    }
}

@Composable
fun ComposeLifecycleNode(text: String, emoji: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(color.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ComposeArrow(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "↓",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ComposeConceptCard(title: String, emoji: String, description: String, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SideEffectCard(name: String, emoji: String, description: String, example: String, useCase: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = emoji, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = example,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(8.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Use case: $useCase",
                fontSize = 13.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun LaunchedEffectExample() {
    var counter by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            counter++
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "🚀 LaunchedEffect Demo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Counter (updates every second): $counter",
                fontSize = 15.sp
            )
            Text(
                text = "This will stop when composable leaves",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DisposableEffectExample() {
    var isActive by remember { mutableStateOf(true) }
    var logs by remember { mutableStateOf(listOf<String>()) }

    if (isActive) {
        DisposableEffect(Unit) {
            logs = logs + "✅ Setup: Listener registered"

            onDispose {
                logs = logs + "🧹 Cleanup: Listener removed"
            }
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "🧹 DisposableEffect Demo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { isActive = !isActive },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isActive) "Trigger Cleanup" else "Trigger Setup")
            }

            Spacer(modifier = Modifier.height(8.dp))

            logs.forEach { log ->
                Text(text = log, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun RememberExample() {
    var normalCounter by remember { mutableStateOf(0) }
    var savedCounter by rememberSaveable { mutableStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "💾 remember vs rememberSaveable",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("remember", fontWeight = FontWeight.Bold)
                    Text("$normalCounter", fontSize = 24.sp)
                    Button(onClick = { normalCounter++ }) {
                        Text("+", fontSize = 20.sp)
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("rememberSaveable", fontWeight = FontWeight.Bold)
                    Text("$savedCounter", fontSize = 24.sp)
                    Button(onClick = { savedCounter++ }) {
                        Text("+", fontSize = 20.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "🔄 Rotate device: remember resets, rememberSaveable persists",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
