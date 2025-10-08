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

data class FragmentEvent(
    val method: String,
    val timestamp: String,
    val emoji: String,
    val color: Color
)

class FragmentLifecycleActivity : ComponentActivity() {
    private val TAG = "FragmentLifecycle"

    companion object {
        val fragmentEvents = mutableStateListOf<FragmentEvent>()

        fun addEvent(method: String, emoji: String, color: Color) {
            val timestamp = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).format(Date())
            fragmentEvents.add(0, FragmentEvent(method, timestamp, emoji, color))
            if (fragmentEvents.size > 50) {
                fragmentEvents.removeLast()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "🎯 Activity onCreate()")
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FragmentLifecycleScreen(
                        modifier = Modifier.padding(innerPadding),
                        events = fragmentEvents
                    )
                }
            }
        }
    }
}

@Composable
fun FragmentLifecycleScreen(
    modifier: Modifier = Modifier,
    events: List<FragmentEvent>
) {
    var showFragment by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🔷 Fragment Lifecycle",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Fragments have a more complex lifecycle than Activities",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Fragment Lifecycle Diagram
        FragmentLifecycleDiagram()

        Spacer(modifier = Modifier.height(16.dp))

        // Control buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    showFragment = true
                    FragmentLifecycleActivity.addEvent("onAttach()", "📎", Color(0xFF9C27B0))
                    FragmentLifecycleActivity.addEvent("onCreate()", "🟢", Color(0xFF4CAF50))
                    FragmentLifecycleActivity.addEvent("onCreateView()", "🖼️", Color(0xFF2196F3))
                    FragmentLifecycleActivity.addEvent("onViewCreated()", "✅", Color(0xFF00BCD4))
                    FragmentLifecycleActivity.addEvent("onStart()", "▶️", Color(0xFF4CAF50))
                    FragmentLifecycleActivity.addEvent("onResume()", "🟢", Color(0xFF4CAF50))
                },
                modifier = Modifier.weight(1f),
                enabled = !showFragment
            ) {
                Text("Add Fragment", fontSize = 13.sp)
            }

            Button(
                onClick = {
                    showFragment = false
                    FragmentLifecycleActivity.addEvent("onPause()", "⏸️", Color(0xFFFFC107))
                    FragmentLifecycleActivity.addEvent("onStop()", "⏹️", Color(0xFFFF9800))
                    FragmentLifecycleActivity.addEvent("onDestroyView()", "🗑️", Color(0xFFF44336))
                    FragmentLifecycleActivity.addEvent("onDestroy()", "💥", Color(0xFFF44336))
                    FragmentLifecycleActivity.addEvent("onDetach()", "🔗", Color(0xFF9C27B0))
                },
                modifier = Modifier.weight(1f),
                enabled = showFragment,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Remove Fragment", fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (showFragment) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "🔷",
                            fontSize = 48.sp
                        )
                        Text(
                            text = "Fragment is VISIBLE",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
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
                    FragmentLifecycleActivity.fragmentEvents.clear()
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
                    Text(
                        text = "Click 'Add Fragment' to see lifecycle events",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(events) { event ->
                    FragmentEventCard(event)
                }
            }
        }
    }
}

@Composable
fun FragmentLifecycleDiagram() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Fragment Lifecycle Flow",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            FragmentLifecycleNode("onAttach()", "📎", Color(0xFF9C27B0))
            FragmentArrow()
            FragmentLifecycleNode("onCreate()", "🟢", Color(0xFF4CAF50))
            FragmentArrow()
            FragmentLifecycleNode("onCreateView()", "🖼️", Color(0xFF2196F3))
            FragmentArrow()
            FragmentLifecycleNode("onViewCreated()", "✅", Color(0xFF00BCD4))
            FragmentArrow()
            FragmentLifecycleNode("onStart()", "▶️", Color(0xFF4CAF50))
            FragmentArrow()
            FragmentLifecycleNode("onResume()", "🟢", Color(0xFF4CAF50))

            Text(
                text = "↕️ Fragment Active ↕️",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            FragmentLifecycleNode("onPause()", "⏸️", Color(0xFFFFC107))
            FragmentArrow()
            FragmentLifecycleNode("onStop()", "⏹️", Color(0xFFFF9800))
            FragmentArrow()
            FragmentLifecycleNode("onDestroyView()", "🗑️", Color(0xFFF44336))
            FragmentArrow()
            FragmentLifecycleNode("onDestroy()", "💥", Color(0xFFF44336))
            FragmentArrow()
            FragmentLifecycleNode("onDetach()", "🔗", Color(0xFF9C27B0))
        }
    }
}

@Composable
fun FragmentLifecycleNode(text: String, emoji: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = emoji, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FragmentArrow() {
    Text(
        text = "↓",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

@Composable
fun FragmentEventCard(event: FragmentEvent) {
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
                    .size(36.dp)
                    .background(event.color.copy(alpha = 0.3f), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = event.emoji, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.method,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = event.timestamp,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
