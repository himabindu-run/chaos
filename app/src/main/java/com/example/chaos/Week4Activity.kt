package com.example.chaos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme

class Week4Activity : ComponentActivity() {
    private val TAG = "Week4Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "🟢 onCreate() - Activity is being created")
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Week4Screen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "🔵 onStart() - Activity is becoming visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "🟢 onResume() - Activity is now interactive")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "🟡 onPause() - Activity is losing focus")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "🟠 onStop() - Activity is no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "🔴 onDestroy() - Activity is being destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "🔄 onRestart() - Activity is restarting")
    }
}

@Composable
fun Week4Screen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Week 4: Debugging & Lifecycles",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Master debugging tools and understand Android lifecycles",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📅 Session Flow",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("1️⃣ Student presentations (Birthday Tracker)", fontSize = 14.sp)
                Text("2️⃣ Debugger & Logcat tutorial", fontSize = 14.sp)
                Text("3️⃣ Activity, Fragment & Compose Lifecycles", fontSize = 14.sp)
                Text("4️⃣ Gradle files overview", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Section 1: Debugging Tools
        Text(
            text = "🔧 Debugging Tools",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Button(
            onClick = {
                val intent = Intent(context, DebuggerTutorialActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C27B0)
            )
        ) {
            Text(
                text = "🐛 Debugger Tutorial",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val intent = Intent(context, LogcatTutorialActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00BCD4)
            )
        ) {
            Text(
                text = "📋 Logcat Tutorial",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Section 2: Lifecycles
        Text(
            text = "♻️ Android Lifecycles",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Lifecycle Flow Diagram
        LifecycleDiagram()

        Spacer(modifier = Modifier.height(24.dp))

        // Lifecycle Method Cards
        LifecycleMethodCard(
            method = "onCreate()",
            emoji = "🟢",
            description = "Called when activity is first created",
            example = "Initialize views, set content, restore state"
        )

        LifecycleMethodCard(
            method = "onStart()",
            emoji = "🔵",
            description = "Activity is becoming visible to user",
            example = "Register listeners, start animations"
        )

        LifecycleMethodCard(
            method = "onResume()",
            emoji = "🟢",
            description = "Activity is now interactive",
            example = "Start camera, resume game, play audio"
        )

        LifecycleMethodCard(
            method = "onPause()",
            emoji = "🟡",
            description = "Activity is losing focus",
            example = "Pause video, save draft, release camera"
        )

        LifecycleMethodCard(
            method = "onStop()",
            emoji = "🟠",
            description = "Activity is no longer visible",
            example = "Stop animations, save data, release resources"
        )

        LifecycleMethodCard(
            method = "onDestroy()",
            emoji = "🔴",
            description = "Activity is being destroyed",
            example = "Clean up resources, cancel tasks"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Interactive Demo Buttons
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
                    text = "🔬 Try These Actions:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "• Press Home button → onPause() → onStop()",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Return to app → onRestart() → onStart() → onResume()",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Rotate device → onPause() → onStop() → onDestroy() → onCreate()",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "• Press Back button → onPause() → onStop() → onDestroy()",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "💡 Check Logcat for 'Week4Lifecycle' to see these events!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, LifecycleDemoActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "🟢 Activity Lifecycle Demo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val intent = Intent(context, FragmentLifecycleActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "🔷 Fragment Lifecycle Demo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val intent = Intent(context, ComposeLifecycleActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                text = "🎨 Compose Lifecycle Demo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Advanced Topics Section
        Text(
            text = "🎓 Advanced Topics",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AdvancedTopicCard(
            title = "Configuration Changes",
            emoji = "🔄",
            description = "What happens when device rotates",
            details = listOf(
                "Activity is destroyed and recreated",
                "Default: onPause() → onStop() → onDestroy() → onCreate() → onStart() → onResume()",
                "Data is lost unless saved properly",
                "Use savedInstanceState to preserve data"
            )
        )

        AdvancedTopicCard(
            title = "Saving Instance State",
            emoji = "💾",
            description = "Preserving data across configuration changes",
            details = listOf(
                "Override onSaveInstanceState(outState: Bundle)",
                "Save: outState.putString(\"key\", value)",
                "Restore in onCreate: savedInstanceState?.getString(\"key\")",
                "Called before onStop() when activity may be killed"
            )
        )

        AdvancedTopicCard(
            title = "Process Death",
            emoji = "☠️",
            description = "When system kills your app in background",
            details = listOf(
                "Happens when device is low on memory",
                "Activity state is saved automatically",
                "User returns: onCreate() called with savedInstanceState",
                "Looks like app never stopped to user"
            )
        )

        AdvancedTopicCard(
            title = "View Lifecycle in Compose",
            emoji = "🎨",
            description = "Composable lifecycle vs Activity lifecycle",
            details = listOf(
                "DisposableEffect: cleanup when composable leaves",
                "LaunchedEffect: run suspend functions in composition",
                "remember: survive recomposition, NOT config changes",
                "rememberSaveable: survive config changes too"
            )
        )

        AdvancedTopicCard(
            title = "Multi-Activity Navigation",
            emoji = "🧭",
            description = "Managing lifecycle across multiple screens",
            details = listOf(
                "Activity A → B: A.onPause() → B.onCreate() → B.onStart() → B.onResume() → A.onStop()",
                "Back from B → A: B.onPause() → A.onRestart() → A.onStart() → A.onResume() → B.onStop() → B.onDestroy()",
                "Home button: onPause() → onStop()",
                "Recent apps don't trigger lifecycle (just visibility)"
            )
        )

        AdvancedTopicCard(
            title = "Best Practices",
            emoji = "✅",
            description = "Key points to remember",
            details = listOf(
                "onCreate(): Initialize UI, restore state",
                "onStart(): Register listeners that don't need focus",
                "onResume(): Start animations, acquire exclusive resources",
                "onPause(): Pause animations, release camera/sensors",
                "onStop(): Save data, release resources",
                "onDestroy(): Clean up remaining resources"
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AdvancedTopicCard(
    title: String,
    emoji: String,
    description: String,
    details: List<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = emoji, fontSize = 28.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            details.forEach { detail ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp)
                ) {
                    Text(
                        text = "• ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = detail,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun LifecycleDiagram() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Activity Lifecycle Flow",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LifecycleNode("onCreate()", "🟢", Color(0xFF4CAF50))
            LifecycleArrow()
            LifecycleNode("onStart()", "🔵", Color(0xFF2196F3))
            LifecycleArrow()
            LifecycleNode("onResume()", "🟢", Color(0xFF4CAF50))

            Text(
                text = "↕️ App is Running ↕️",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LifecycleNode("onPause()", "🟡", Color(0xFFFFC107))
            LifecycleArrow()
            LifecycleNode("onStop()", "🟠", Color(0xFFFF9800))
            LifecycleArrow()
            LifecycleNode("onDestroy()", "🔴", Color(0xFFF44336))

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🔄", fontSize = 24.sp)
                    Text("onRestart()", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text("(if returning)", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun LifecycleNode(text: String, emoji: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .background(color.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .border(2.dp, color, RoundedCornerShape(8.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LifecycleArrow() {
    Text(
        text = "↓",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun LifecycleMethodCard(
    method: String,
    emoji: String,
    description: String,
    example: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = method,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = "Example: $example",
                    fontSize = 12.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Week4ScreenPreview() {
    ChaosTheme {
        Week4Screen()
    }
}
