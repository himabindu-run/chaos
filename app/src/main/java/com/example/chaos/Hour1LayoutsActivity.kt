package com.example.chaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme

class Hour1LayoutsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChaosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Hour1LayoutsDemoScreen()
                }
            }
        }
    }
}

@Composable
fun Hour1LayoutsDemoScreen() {
    var currentDemo by remember { mutableStateOf("linear") }
    var isVertical by remember { mutableStateOf(true) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Making Better Layouts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Demo Selection
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { currentDemo = "linear" },
                colors = if (currentDemo == "linear") 
                    ButtonDefaults.buttonColors() 
                else 
                    ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier.weight(1f)
            ) {
                Text("LinearLayout")
            }
            
            Button(
                onClick = { currentDemo = "constraint" },
                colors = if (currentDemo == "constraint") 
                    ButtonDefaults.buttonColors() 
                else 
                    ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier.weight(1f)
            ) {
                Text("ConstraintLayout")
            }
            
            Button(
                onClick = { currentDemo = "material" },
                colors = if (currentDemo == "material") 
                    ButtonDefaults.buttonColors() 
                else 
                    ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier.weight(1f)
            ) {
                Text("Material")
            }
        }
        
        when (currentDemo) {
            "linear" -> LinearLayoutDemo(isVertical) { isVertical = it }
            "constraint" -> ConstraintLayoutDemo()
            "material" -> MaterialDesignDemo()
        }
    }
}

@Composable
fun LinearLayoutDemo(isVertical: Boolean, onOrientationChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "LinearLayout Demo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Orientation Toggle
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Orientation: ")
                Switch(
                    checked = isVertical,
                    onCheckedChange = onOrientationChange
                )
                Text(if (isVertical) " Vertical" else " Horizontal")
            }
            
            // Demo Layout with Weight
            if (isVertical) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeightDemoBox("Weight: 1", Color(0xFFE3F2FD), 1f)
                    WeightDemoBox("Weight: 2", Color(0xFFBBDEFB), 2f)
                    WeightDemoBox("Weight: 1", Color(0xFF90CAF9), 1f)
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeightDemoBoxHorizontal("W:1", Color(0xFFE3F2FD), 1f)
                    WeightDemoBoxHorizontal("W:2", Color(0xFFBBDEFB), 2f)
                    WeightDemoBoxHorizontal("W:1", Color(0xFF90CAF9), 1f)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "💡 Tip: Weight determines how much space each view takes relative to others!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun RowScope.WeightDemoBoxHorizontal(text: String, color: Color, weight: Float) {
    Box(
        modifier = Modifier
            .weight(weight)
            .fillMaxHeight()
            .background(color, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ColumnScope.WeightDemoBox(text: String, color: Color, weight: Float) {
    Box(
        modifier = Modifier
            .weight(weight)
            .fillMaxWidth()
            .background(color, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ConstraintLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "ConstraintLayout Demo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Simulated ConstraintLayout using Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                // Top-left
                Surface(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.TopStart),
                    color = Color(0xFF4CAF50),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("TL", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                
                // Top-right
                Surface(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.TopEnd),
                    color = Color(0xFF2196F3),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("TR", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                
                // Center
                Surface(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    color = Color(0xFFFF9800),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("CENTER", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                
                // Bottom spanning width
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .align(Alignment.BottomCenter),
                    color = Color(0xFF9C27B0),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("BOTTOM SPANNING", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "💡 ConstraintLayout Benefits:\n• Flat view hierarchy\n• Responsive design\n• Complex layouts made simple\n• Better performance",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun MaterialDesignDemo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Material Design Components",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Button Variants
            Text(
                text = "Button Variants:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { }) {
                    Text("Filled")
                }
                OutlinedButton(onClick = { }) {
                    Text("Outlined")
                }
                TextButton(onClick = { }) {
                    Text("Text")
                }
            }
            
            // Cards with different elevations
            Text(
                text = "Card Elevations:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { index ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = ((index + 1) * 4).dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${(index + 1) * 4}dp", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            
            // Typography Scale
            Text(
                text = "Typography Scale:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
                Text("Title Medium", style = MaterialTheme.typography.titleMedium)
                Text("Body Large", style = MaterialTheme.typography.bodyLarge)
                Text("Label Small", style = MaterialTheme.typography.labelSmall)
            }
            
            Text(
                text = "🎨 Material Design creates consistent, beautiful, and accessible user experiences across all Android apps!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Hour1LayoutsDemoPreview() {
    ChaosTheme {
        Hour1LayoutsDemoScreen()
    }
}