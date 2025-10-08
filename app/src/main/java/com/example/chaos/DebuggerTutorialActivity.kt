package com.example.chaos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import kotlin.random.Random

class DebuggerTutorialActivity : ComponentActivity() {
    private val TAG = "DebuggerTutorial"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity created")
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DebuggerTutorialScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DebuggerTutorialScreen(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "🐛 Debugger Tutorial",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Learn to debug your Android apps like a pro",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // What is Debugging Section
        TutorialSection(
            title = "What is Debugging?",
            emoji = "🔍",
            content = {
                Text(
                    text = "Debugging is the process of finding and fixing errors (bugs) in your code. " +
                            "The debugger lets you pause execution, inspect variables, and step through code line by line.",
                    fontSize = 14.sp
                )
            }
        )

        // Setting Breakpoints
        TutorialSection(
            title = "1. Setting Breakpoints",
            emoji = "🔴",
            content = {
                Column {
                    Text(
                        text = "A breakpoint pauses execution at a specific line of code.",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    BulletPoint("Click in the left gutter next to line number")
                    BulletPoint("Red dot appears when breakpoint is set")
                    BulletPoint("Click again to remove breakpoint")
                    BulletPoint("Right-click breakpoint for conditions")

                    Spacer(modifier = Modifier.height(12.dp))

                    CodeExample(
                        code = """
                        fun calculateSum(a: Int, b: Int): Int {
                            val sum = a + b  // 🔴 Set breakpoint here
                            return sum
                        }
                        """.trimIndent()
                    )
                }
            }
        )

        // Starting Debug Session
        TutorialSection(
            title = "2. Starting Debug Session",
            emoji = "▶️",
            content = {
                Column {
                    BulletPoint("Click 'Debug' button (bug icon) instead of 'Run'")
                    BulletPoint("Or use Shift + F9 (Windows/Linux) / Ctrl + D (Mac)")
                    BulletPoint("App runs normally until hitting a breakpoint")
                    BulletPoint("Debug panel opens at bottom with variables view")
                }
            }
        )

        // Debug Controls
        TutorialSection(
            title = "3. Debug Controls",
            emoji = "🎮",
            content = {
                Column {
                    DebugControl(
                        name = "Resume (F9)",
                        icon = "▶️",
                        description = "Continue execution until next breakpoint"
                    )
                    DebugControl(
                        name = "Step Over (F8)",
                        icon = "⤵️",
                        description = "Execute current line and move to next"
                    )
                    DebugControl(
                        name = "Step Into (F7)",
                        icon = "⬇️",
                        description = "Go inside function calls to debug them"
                    )
                    DebugControl(
                        name = "Step Out (Shift+F8)",
                        icon = "⬆️",
                        description = "Finish current function and return to caller"
                    )
                    DebugControl(
                        name = "Stop (Ctrl+F2)",
                        icon = "⏹️",
                        description = "Stop debugging session"
                    )
                }
            }
        )

        // Variables & Watches
        TutorialSection(
            title = "4. Inspecting Variables",
            emoji = "👁️",
            content = {
                Column {
                    Text(
                        text = "When paused at breakpoint, you can:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    BulletPoint("See all variables and their values")
                    BulletPoint("Hover over variables in code to see values")
                    BulletPoint("Add watches for specific expressions")
                    BulletPoint("Evaluate expressions in Debug Console")
                    BulletPoint("Modify variable values during debugging")
                }
            }
        )

        // Practical Exercise
        TutorialSection(
            title = "🏋️ Practical Exercise",
            emoji = "🎯",
            content = {
                Column {
                    Text(
                        text = "Try debugging this calculation:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Button(
                        onClick = {
                            result = performComplexCalculation()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Run Calculation (Set breakpoint in code!)", fontSize = 14.sp)
                    }

                    if (result.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Result: $result", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Set a breakpoint in performComplexCalculation() " +
                                            "and step through to see how it works!",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        )

        // Common Debug Scenarios
        TutorialSection(
            title = "5. Common Debug Scenarios",
            emoji = "💡",
            content = {
                Column {
                    DebugScenario(
                        scenario = "App crashes",
                        solution = "Enable 'Break on exceptions' to pause when error occurs"
                    )
                    DebugScenario(
                        scenario = "Wrong calculation result",
                        solution = "Set breakpoint before calculation, step through and watch variables"
                    )
                    DebugScenario(
                        scenario = "Function not called",
                        solution = "Set breakpoint at function entry to confirm it's reached"
                    )
                    DebugScenario(
                        scenario = "Loop issue",
                        solution = "Set conditional breakpoint: only pause when i == specificValue"
                    )
                    DebugScenario(
                        scenario = "Null pointer",
                        solution = "Step through and watch when variable becomes null"
                    )
                }
            }
        )

        // Pro Tips
        TutorialSection(
            title = "6. Pro Tips",
            emoji = "⭐",
            content = {
                Column {
                    ProTip("Use conditional breakpoints for loops (right-click breakpoint)")
                    ProTip("Add watches for complex expressions you check frequently")
                    ProTip("Use 'Evaluate Expression' (Alt+F8) to test code without editing")
                    ProTip("Enable logcat while debugging to see logs and variables together")
                    ProTip("Use 'Drop Frame' to re-execute a function without restarting")
                    ProTip("Keyboard shortcuts are faster: F7 (into), F8 (over), F9 (resume)")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// Helper function to demonstrate debugging
fun performComplexCalculation(): String {
    Log.d("DebuggerTutorial", "🔴 Starting calculation - SET BREAKPOINT HERE!")

    val num1 = Random.nextInt(1, 10)
    Log.d("DebuggerTutorial", "num1 = $num1")

    val num2 = Random.nextInt(1, 10)
    Log.d("DebuggerTutorial", "num2 = $num2")

    val sum = num1 + num2
    Log.d("DebuggerTutorial", "sum = $sum")

    val product = num1 * num2
    Log.d("DebuggerTutorial", "product = $product")

    val average = (sum + product) / 2.0
    Log.d("DebuggerTutorial", "average = $average")

    return "num1=$num1, num2=$num2, sum=$sum, product=$product, average=$average"
}

@Composable
fun TutorialSection(
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
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = "• ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

@Composable
fun CodeExample(code: String) {
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
fun DebugControl(name: String, icon: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = icon, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DebugScenario(scenario: String, solution: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Problem: $scenario",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Solution: $solution",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun ProTip(tip: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "💡 ",
            fontSize = 16.sp
        )
        Text(
            text = tip,
            fontSize = 14.sp
        )
    }
}
