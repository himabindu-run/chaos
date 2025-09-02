package com.example.chaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import kotlin.random.Random

class Hour2FunctionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChaosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Hour2FunctionsDemoScreen()
                }
            }
        }
    }
}

@Composable
fun Hour2FunctionsDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Making Things Clickable",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Challenge 1: Color Cycle Button
        ColorCycleChallenge()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Challenge 2: Counter with Milestones
        CounterWithMilestones()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Challenge 3: Random Quote Generator
        RandomQuoteGenerator()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Challenge 4: Magic 8-Ball
        Magic8Ball()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Bonus: Interactive Color Mixer
        InteractiveColorMixer()
    }
}

@Composable
fun ColorCycleChallenge() {
    val colors = listOf(
        Color(0xFFE57373), // Red
        Color(0xFF64B5F6), // Blue  
        Color(0xFF81C784), // Green
        Color(0xFFFFB74D), // Orange
        Color(0xFFBA68C8), // Purple
        Color(0xFF4DB6AC)  // Teal
    )
    
    var colorIndex by remember { mutableStateOf(0) }
    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(300),
        label = "color"
    )
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Challenge 1: Color Cycle Button",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Click the button to cycle through colors!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Button(
                onClick = {
                    colorIndex = (colorIndex + 1) % colors.size
                },
                colors = ButtonDefaults.buttonColors(containerColor = animatedColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "Color ${colorIndex + 1} of ${colors.size}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CounterWithMilestones() {
    var count by remember { mutableStateOf(0) }
    
    // Function to get background color based on count
    fun getBackgroundColor(count: Int): Color {
        return when (count) {
            in 0..9 -> Color(0xFFF5F5F5)    // Light Gray
            in 10..19 -> Color(0xFFE3F2FD)  // Light Blue
            in 20..29 -> Color(0xFFE8F5E8)  // Light Green
            else -> Color(0xFFFFF8E1)       // Light Gold
        }
    }
    
    fun getMilestoneMessage(count: Int): String {
        return when (count) {
            in 0..9 -> "Getting started!"
            in 10..19 -> "You're on fire! 🔥"
            in 20..29 -> "Unstoppable! 🚀"
            else -> "LEGENDARY! ⭐"
        }
    }
    
    val animatedBackground by animateColorAsState(
        targetValue = getBackgroundColor(count),
        animationSpec = tween(500),
        label = "background"
    )
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(animatedBackground)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Challenge 2: Counter with Milestones",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "Watch the background change as you click!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "$count",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = getMilestoneMessage(count),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { count++ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+1")
                }
                
                OutlinedButton(
                    onClick = { count = 0 },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Reset")
                }
            }
        }
    }
}

@Composable
fun RandomQuoteGenerator() {
    val quotes = listOf(
        "The best way to predict the future is to invent it.",
        "Life is what happens to you while you're busy making other plans.",
        "The future belongs to those who believe in the beauty of their dreams.",
        "It is during our darkest moments that we must focus to see the light.",
        "Success is not final, failure is not fatal: it is the courage to continue that counts.",
        "The only impossible journey is the one you never begin."
    )
    
    val colors = listOf(
        Color(0xFFFFE0B2), // Light Orange
        Color(0xFFE1BEE7), // Light Purple  
        Color(0xFFC8E6C9), // Light Green
        Color(0xFFBBDEFB), // Light Blue
        Color(0xFFFFCDD2), // Light Pink
        Color(0xFFF0F4C3)  // Light Yellow
    )
    
    var currentQuote by remember { mutableStateOf(quotes[0]) }
    var currentColor by remember { mutableStateOf(colors[0]) }
    
    val animatedColor by animateColorAsState(
        targetValue = currentColor,
        animationSpec = tween(400),
        label = "quote_color"
    )
    
    fun generateNewQuote() {
        currentQuote = quotes.random()
        currentColor = colors.random()
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(animatedColor)
                .padding(16.dp)
        ) {
            Text(
                text = "Challenge 3: Random Quote Generator",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "\"$currentQuote\"",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                lineHeight = 24.sp
            )
            
            Button(
                onClick = { generateNewQuote() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get New Quote")
            }
        }
    }
}

@Composable
fun Magic8Ball() {
    val responses = listOf(
        "Yes, definitely! ✨",
        "No way! ❌", 
        "Maybe... 🤔",
        "Ask again later 🔮",
        "Without a doubt! 💯",
        "Very doubtful 😕",
        "Absolutely! 🎉",
        "Don't count on it 🚫",
        "Signs point to yes 👍",
        "My sources say no 📰"
    )
    
    var currentResponse by remember { mutableStateOf("Ask me anything!") }
    var isShaking by remember { mutableStateOf(false) }
    
    fun shakeBall() {
        isShaking = true
        currentResponse = responses.random()
    }
    
    LaunchedEffect(isShaking) {
        if (isShaking) {
            kotlinx.coroutines.delay(300)
            isShaking = false
        }
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Challenge 4: Magic 8-Ball",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Magic 8-Ball
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Black, RoundedCornerShape(60.dp))
                    .clickable { shakeBall() },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF4A148C), RoundedCornerShape(40.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "8",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = currentResponse,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            
            Button(
                onClick = { shakeBall() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Shake the Ball!")
            }
        }
    }
}

@Composable
fun InteractiveColorMixer() {
    var redValue by remember { mutableStateOf(128) }
    var greenValue by remember { mutableStateOf(128) }
    var blueValue by remember { mutableStateOf(128) }
    
    val mixedColor = Color(red = redValue, green = greenValue, blue = blueValue, alpha = 255)
    
    fun randomizeColors() {
        redValue = Random.nextInt(0, 256)
        greenValue = Random.nextInt(0, 256) 
        blueValue = Random.nextInt(0, 256)
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bonus: Interactive Color Mixer",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Color preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(mixedColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RGB($redValue, $greenValue, $blueValue)",
                    color = if (redValue + greenValue + blueValue > 400) Color.Black else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Color sliders (simulated with buttons for simplicity)
            ColorControlRow("Red", redValue) { redValue = it }
            ColorControlRow("Green", greenValue) { greenValue = it }
            ColorControlRow("Blue", blueValue) { blueValue = it }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { randomizeColors() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Randomize Colors")
            }
        }
    }
}

@Composable
fun ColorControlRow(colorName: String, value: Int, onValueChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$colorName: $value",
            modifier = Modifier.width(100.dp)
        )
        
        Row {
            Button(
                onClick = { onValueChange(maxOf(0, value - 25)) },
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("-", fontSize = 18.sp)
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = { onValueChange(minOf(255, value + 25)) },
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("+", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Hour2FunctionsDemoPreview() {
    ChaosTheme {
        Hour2FunctionsDemoScreen()
    }
}