package com.example.chaos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme

class LogcatTutorialActivity : ComponentActivity() {
    private val TAG = "LogcatTutorial"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity created - Check Logcat!")
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogcatTutorialScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LogcatTutorialScreen(modifier: Modifier = Modifier) {
    val TAG = "LogcatDemo"

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "📋 Logcat Tutorial",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Master Android's logging and debugging console",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // What is Logcat
        LogcatSection(
            title = "What is Logcat?",
            emoji = "📋",
            content = {
                Text(
                    text = "Logcat is Android's logging system that displays system messages, " +
                            "including your app's log messages. It's essential for debugging and " +
                            "understanding what's happening in your app.",
                    fontSize = 14.sp
                )
            }
        )

        // Log Levels
        LogcatSection(
            title = "1. Log Levels (Priority)",
            emoji = "🎚️",
            content = {
                Column {
                    Text(
                        text = "Android has 5 log levels, from least to most important:",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LogLevelCard(
                        level = "VERBOSE",
                        code = "Log.v(TAG, \"message\")",
                        color = Color(0xFF808080),
                        description = "Detailed information, rarely used in production",
                        example = "Log.v(TAG, \"Button position: x=100, y=200\")"
                    )

                    LogLevelCard(
                        level = "DEBUG",
                        code = "Log.d(TAG, \"message\")",
                        color = Color(0xFF2196F3),
                        description = "Debug info useful during development",
                        example = "Log.d(TAG, \"User clicked submit button\")"
                    )

                    LogLevelCard(
                        level = "INFO",
                        code = "Log.i(TAG, \"message\")",
                        color = Color(0xFF4CAF50),
                        description = "General informational messages",
                        example = "Log.i(TAG, \"Network request completed successfully\")"
                    )

                    LogLevelCard(
                        level = "WARN",
                        code = "Log.w(TAG, \"message\")",
                        color = Color(0xFFFFC107),
                        description = "Warning about potential problems",
                        example = "Log.w(TAG, \"Battery is low, saving less frequently\")"
                    )

                    LogLevelCard(
                        level = "ERROR",
                        code = "Log.e(TAG, \"message\")",
                        color = Color(0xFFF44336),
                        description = "Errors that need attention",
                        example = "Log.e(TAG, \"Failed to save data: \${exception.message}\")"
                    )
                }
            }
        )

        // Using Tags
        LogcatSection(
            title = "2. Using Tags",
            emoji = "🏷️",
            content = {
                Column {
                    Text(
                        text = "Tags help identify where logs come from. Best practice:",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    CodeBlock(
                        """
                        class MyActivity : ComponentActivity() {
                            private val TAG = "MyActivity"  // or "MainActivity"

                            override fun onCreate(savedInstanceState: Bundle?) {
                                super.onCreate(savedInstanceState)
                                Log.d(TAG, "onCreate called")
                            }
                        }
                        """.trimIndent()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LogcatBullet("Use class name as tag for easy filtering")
                    LogcatBullet("Keep tags short but descriptive")
                    LogcatBullet("Use consistent naming across your app")
                }
            }
        )

        // Filtering Logs
        LogcatSection(
            title = "3. Filtering in Logcat",
            emoji = "🔍",
            content = {
                Column {
                    FilterOption(
                        "By Tag",
                        "Type 'tag:MyTag' in filter box",
                        "Shows only logs with that tag"
                    )
                    FilterOption(
                        "By Log Level",
                        "Select level from dropdown (Verbose/Debug/Info/Warn/Error)",
                        "Shows logs at or above selected level"
                    )
                    FilterOption(
                        "By Package",
                        "Shows logs only from your app",
                        "Filters out system logs"
                    )
                    FilterOption(
                        "By Regex",
                        "Use regex patterns for complex filtering",
                        "Example: 'lifecycle|onCreate'"
                    )
                }
            }
        )

        // Interactive Demo
        LogcatSection(
            title = "🔬 Try It Now!",
            emoji = "🎯",
            content = {
                Column {
                    Text(
                        text = "Click buttons below and watch Logcat window!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Button(
                        onClick = {
                            Log.v(TAG, "🔤 VERBOSE: This is very detailed information")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF808080)
                        )
                    ) {
                        Text("Log Verbose")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            Log.d(TAG, "🐛 DEBUG: User pressed debug button")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        )
                    ) {
                        Text("Log Debug")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            Log.i(TAG, "ℹ️ INFO: Operation completed successfully")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Log Info")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            Log.w(TAG, "⚠️ WARNING: This might cause issues!")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFC107)
                        )
                    ) {
                        Text("Log Warning")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            Log.e(TAG, "❌ ERROR: Something went wrong!")
                            Log.e(TAG, "Stack trace simulation", Exception("Sample exception"))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336)
                        )
                    ) {
                        Text("Log Error")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "📍 How to view logs:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("1. Open Logcat tab at bottom of Android Studio", fontSize = 13.sp)
                            Text("2. Filter by 'LogcatDemo' tag", fontSize = 13.sp)
                            Text("3. Press buttons above", fontSize = 13.sp)
                            Text("4. Watch logs appear in real-time!", fontSize = 13.sp)
                        }
                    }
                }
            }
        )

        // Practical Tips - Interactive Demos
        LogcatSection(
            title = "4. Practical Tips - Try Them!",
            emoji = "💡",
            content = {
                Column {
                    Text(
                        text = "Click each button and watch Logcat with specific tags:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Tip 1: Lifecycle Debugging
                    PracticalTipDemo(
                        title = "1. Lifecycle Debugging",
                        tag = "LifecycleDemo",
                        description = "Track activity lifecycle methods",
                        buttonText = "Simulate Lifecycle Events",
                        onClick = {
                            Log.d("LifecycleDemo", "📍 onCreate() - Activity created")
                            Log.d("LifecycleDemo", "📍 onStart() - Activity becoming visible")
                            Log.d("LifecycleDemo", "📍 onResume() - Activity now interactive")
                            Log.i("LifecycleDemo", "✅ Activity fully loaded and ready!")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tip 2: Variable Inspection
                    PracticalTipDemo(
                        title = "2. Variable Inspection",
                        tag = "VariableLog",
                        description = "Log variable values for debugging",
                        buttonText = "Log User Variables",
                        onClick = {
                            val userName = "Alice Johnson"
                            val userAge = 25
                            val userEmail = "alice@example.com"
                            val isActive = true

                            Log.d("VariableLog", "🔍 Inspecting user variables:")
                            Log.d("VariableLog", "   userName: $userName")
                            Log.d("VariableLog", "   userAge: $userAge")
                            Log.d("VariableLog", "   userEmail: $userEmail")
                            Log.d("VariableLog", "   isActive: $isActive")
                            Log.i("VariableLog", "✅ All user data logged successfully")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tip 3: Function Entry/Exit
                    PracticalTipDemo(
                        title = "3. Function Entry/Exit",
                        tag = "FunctionTracker",
                        description = "Track function calls and returns",
                        buttonText = "Calculate Total",
                        onClick = {
                            Log.d("FunctionTracker", "▶️ calculateTotal() ENTRY")

                            val items = listOf(10, 20, 30, 40)
                            Log.d("FunctionTracker", "   Processing ${items.size} items")

                            val total = items.sum()
                            Log.d("FunctionTracker", "   Calculated total: $total")

                            Log.d("FunctionTracker", "◀️ calculateTotal() EXIT - returning $total")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tip 4: Error Tracking
                    PracticalTipDemo(
                        title = "4. Error Tracking",
                        tag = "ErrorHandler",
                        description = "Log errors with stack traces",
                        buttonText = "Simulate Error",
                        onClick = {
                            try {
                                Log.d("ErrorHandler", "🔄 Attempting risky operation...")

                                // Simulate error
                                val result = 100 / 0

                            } catch (e: Exception) {
                                Log.e("ErrorHandler", "❌ ERROR: Operation failed!", e)
                                Log.e("ErrorHandler", "Error type: ${e.javaClass.simpleName}")
                                Log.e("ErrorHandler", "Error message: ${e.message}")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "🎯 How to view these logs:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text("1. Open Logcat panel", fontSize = 13.sp)
                            Text("2. Filter by specific tag:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Text("   • tag:LifecycleDemo", fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
                            Text("   • tag:VariableLog", fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
                            Text("   • tag:FunctionTracker", fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
                            Text("   • tag:ErrorHandler", fontSize = 12.sp, modifier = Modifier.padding(start = 16.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("3. Press buttons above", fontSize = 13.sp)
                            Text("4. Watch filtered logs appear!", fontSize = 13.sp)
                        }
                    }
                }
            }
        )

        // Best Practices
        LogcatSection(
            title = "5. Best Practices",
            emoji = "⭐",
            content = {
                Column {
                    BestPractice("✅ DO use meaningful, descriptive log messages")
                    BestPractice("✅ DO include context (what, where, why)")
                    BestPractice("✅ DO use appropriate log levels")
                    BestPractice("✅ DO log exceptions with stack traces")
                    BestPractice("❌ DON'T log sensitive information (passwords, tokens)")
                    BestPractice("❌ DON'T log too much (impacts performance)")
                    BestPractice("❌ DON'T use System.out.println() (use Log instead)")
                    BestPractice("❌ DON'T leave verbose logs in production")
                }
            }
        )

        // Common Logcat Shortcuts
        LogcatSection(
            title = "6. Useful Shortcuts",
            emoji = "⌨️",
            content = {
                Column {
                    ShortcutItem("Ctrl/Cmd + F", "Search in logcat")
                    ShortcutItem("Ctrl/Cmd + K", "Clear logcat")
                    ShortcutItem("Scroll Lock icon", "Pause/Resume auto-scroll")
                    ShortcutItem("Wrap text icon", "Toggle line wrapping")
                    ShortcutItem("Right-click log", "Copy, filter, or add to favorites")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun LogcatSection(
    title: String,
    emoji: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(text = emoji, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            content()
        }
    }
}

@Composable
fun LogLevelCard(
    level: String,
    code: String,
    color: Color,
    description: String,
    example: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.15f)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color, RoundedCornerShape(6.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = level,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = code,
                    fontSize = 12.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(text = description, fontSize = 13.sp)

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Example: $example",
                fontSize = 12.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun CodeBlock(code: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = code,
            fontSize = 12.sp,
            modifier = Modifier.padding(12.dp),
            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
        )
    }
}

@Composable
fun LogcatBullet(text: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "• ", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun FilterOption(name: String, how: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = how, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = description, fontSize = 12.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
        }
    }
}

@Composable
fun PracticalTip(title: String, description: String, example: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 13.sp, modifier = Modifier.padding(vertical = 4.dp))
            CodeBlock(example)
        }
    }
}

@Composable
fun BestPractice(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun ShortcutItem(shortcut: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = shortcut,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = description,
            fontSize = 13.sp,
            modifier = Modifier.weight(0.6f)
        )
    }
}

@Composable
fun PracticalTipDemo(
    title: String,
    tag: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Filter: tag:$tag",
                fontSize = 12.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(buttonText, fontSize = 14.sp)
            }
        }
    }
}
