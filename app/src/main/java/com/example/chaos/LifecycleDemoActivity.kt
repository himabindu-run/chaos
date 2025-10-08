package com.example.chaos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme
import java.text.SimpleDateFormat
import java.util.*

data class LifecycleEvent(
    val method: String,
    val timestamp: String,
    val emoji: String,
    val color: Color
)

class LifecycleDemoActivity : ComponentActivity() {
    private val TAG = "LifecycleDemo"

    companion object {
        val lifecycleEvents = mutableStateListOf<LifecycleEvent>()

        fun addEvent(method: String, emoji: String, color: Color) {
            val timestamp = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(Date())
            lifecycleEvents.add(0, LifecycleEvent(method, timestamp, emoji, color))
            // Keep only last 50 events
            if (lifecycleEvents.size > 50) {
                lifecycleEvents.removeLast()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "🟢 onCreate() - Activity is being created")
        addEvent("onCreate()", "🟢", Color(0xFF4CAF50))

        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LifecycleDemoScreen(
                        modifier = Modifier.padding(innerPadding),
                        events = lifecycleEvents
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "🔵 onStart() - Activity is becoming visible")
        addEvent("onStart()", "🔵", Color(0xFF2196F3))
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "🟢 onResume() - Activity is now interactive")
        addEvent("onResume()", "🟢", Color(0xFF4CAF50))
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "🟡 onPause() - Activity is losing focus")
        addEvent("onPause()", "🟡", Color(0xFFFFC107))
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "🟠 onStop() - Activity is no longer visible")
        addEvent("onStop()", "🟠", Color(0xFFFF9800))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "🔴 onDestroy() - Activity is being destroyed")
        addEvent("onDestroy()", "🔴", Color(0xFFF44336))
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "🔄 onRestart() - Activity is restarting")
        addEvent("onRestart()", "🔄", Color(0xFF9C27B0))
    }
}

@Composable
fun LifecycleDemoScreen(
    modifier: Modifier = Modifier,
    events: List<LifecycleEvent>
) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🔬 Live Lifecycle Monitor",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Watch lifecycle methods in real-time!",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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
                    text = "Try These Actions:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "• Press Home button",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Return to the app",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Rotate the device",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Press Back button",
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lifecycle Events (${events.size})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if (events.isNotEmpty()) {
                TextButton(onClick = {
                    LifecycleDemoActivity.lifecycleEvents.clear()
                }) {
                    Text("Clear")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (events.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "👀",
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Perform actions to see lifecycle events",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(events) { event ->
                    LifecycleEventCard(event)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Current State Indicator
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (events.firstOrNull()?.method == "onResume()")
                    Color(0xFF4CAF50).copy(alpha = 0.2f)
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current State: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = when (events.firstOrNull()?.method) {
                        "onResume()" -> "🟢 RUNNING"
                        "onPause()" -> "🟡 PAUSED"
                        "onStop()" -> "🟠 STOPPED"
                        "onDestroy()" -> "🔴 DESTROYED"
                        else -> "⚪ UNKNOWN"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun LifecycleEventCard(event: LifecycleEvent) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = event.color.copy(alpha = 0.15f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(event.color.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = event.emoji, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.method,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = event.timestamp,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
