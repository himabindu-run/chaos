package com.example.chaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class BirthdayPerson(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val date: LocalDate,
    val favoriteColor: Color = Color.Blue
) {
    fun daysUntilBirthday(): Long {
        val today = LocalDate.now()
        var nextBirthday = date.withYear(today.year)
        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.withYear(today.year + 1)
        }
        return ChronoUnit.DAYS.between(today, nextBirthday)
    }
    
    fun isToday(): Boolean {
        val today = LocalDate.now()
        return date.month == today.month && date.dayOfMonth == today.dayOfMonth
    }
    
    fun age(): Int {
        return ChronoUnit.YEARS.between(date, LocalDate.now()).toInt()
    }
}

class BirthdayTrackerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BirthdayTrackerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayTrackerScreen(modifier: Modifier = Modifier) {
    var birthdays by remember {
        mutableStateOf(
            listOf(
                BirthdayPerson(name = "Alice Johnson", date = LocalDate.of(1995, 3, 15), favoriteColor = Color(0xFF2196F3)),
                BirthdayPerson(name = "Bob Smith", date = LocalDate.of(1992, 7, 22), favoriteColor = Color(0xFF4CAF50)),
                BirthdayPerson(name = "Charlie Brown", date = LocalDate.of(1998, 12, 3), favoriteColor = Color(0xFFFF9800)),
                BirthdayPerson(name = "Diana Prince", date = LocalDate.of(1990, 1, 8), favoriteColor = Color(0xFF9C27B0)),
                BirthdayPerson(name = "Today Birthday", date = LocalDate.now(), favoriteColor = Color(0xFFF44336)) // Someone with birthday today
            )
        )
    }
    var showAddDialog by remember { mutableStateOf(false) }
    
    Column(modifier = modifier.fillMaxSize()) {
        // Header with floating action button
        TopAppBar(
            title = {
                Text(
                    "🎂 Complete App Demo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Birthday")
                }
            }
        )
        
        // Today's birthdays section
        val todayBirthdays = birthdays.filter { it.isToday() }
        if (todayBirthdays.isNotEmpty()) {
            TodayBirthdaySection(todayBirthdays)
        }
        
        // All birthdays list
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Teaching Demo: Complete App Features (${birthdays.size})",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Show students: lists, forms, date calculations, animations",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            items(birthdays.sortedBy { it.daysUntilBirthday() }) { person ->
                BirthdayCard(
                    person = person,
                    onDelete = { birthdays = birthdays - person }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
    
    // Add birthday dialog
    if (showAddDialog) {
        AddBirthdayDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { name, date, color ->
                birthdays = birthdays + BirthdayPerson(
                    name = name,
                    date = date,
                    favoriteColor = color
                )
                showAddDialog = false
            }
        )
    }
}

@Composable
fun TodayBirthdaySection(todayBirthdays: List<BirthdayPerson>) {
    var isPlaying by remember { mutableStateOf(false) }
    
    LaunchedEffect(todayBirthdays) {
        if (todayBirthdays.isNotEmpty()) {
            isPlaying = true
            delay(3000)
            isPlaying = false
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF3E0)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🎉 Today's Birthdays! 🎉",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            todayBirthdays.forEach { person ->
                AnimatedVisibility(
                    visible = isPlaying,
                    enter = scaleIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                    exit = fadeOut()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = person.favoriteColor.copy(alpha = 0.2f)
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
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(person.favoriteColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = person.name.split(" ").map { it.first() }.joinToString(""),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(16.dp))
                            
                            Column {
                                Text(
                                    "${person.name} turns ${person.age() + 1}!",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "🎵 Happy Birthday! 🎂",
                                    fontSize = 14.sp,
                                    color = Color(0xFFE65100)
                                )
                            }
                        }
                    }
                }
            }
            
            // Confetti animation
            if (isPlaying) {
                ConfettiAnimation()
            }
        }
    }
}

@Composable
fun ConfettiAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val confettiPositions = remember {
        List(10) { index ->
            Pair(
                (index * 40).dp + (0..50).random().dp,
                (-100).dp
            )
        }
    }
    
    val animatedY by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        confettiPositions.forEachIndexed { index, (x, _) ->
            Box(
                modifier = Modifier
                    .offset(x = x, y = animatedY.dp + (index * 20).dp)
                    .size(8.dp)
                    .background(
                        color = listOf(
                            Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta
                        ).random(),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun BirthdayCard(
    person: BirthdayPerson,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(person.favoriteColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = person.name.split(" ").map { it.first() }.joinToString(""),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = person.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = person.date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Age: ${person.age()}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Days until birthday
            Column(
                horizontalAlignment = Alignment.End
            ) {
                val days = person.daysUntilBirthday()
                Text(
                    text = if (days == 0L) "TODAY!" else "$days days",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (days == 0L) Color.Red else MaterialTheme.colorScheme.primary
                )
                
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBirthdayDialog(
    onDismiss: () -> Unit,
    onAdd: (String, LocalDate, Color) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedColor by remember { mutableStateOf(Color.Blue) }
    
    val colors = listOf(
        Color(0xFF2196F3), // Blue
        Color(0xFF4CAF50), // Green
        Color(0xFFFF9800), // Orange
        Color(0xFF9C27B0), // Purple
        Color(0xFFF44336), // Red
        Color(0xFF00BCD4), // Cyan
    )
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Birthday") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("Pick a color:", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                
                LazyColumn {
                    items(colors.chunked(3)) { rowColors ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            rowColors.forEach { color ->
                                Card(
                                    onClick = { selectedColor = color },
                                    modifier = Modifier
                                        .size(40.dp)
                                        .weight(1f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = color
                                    ),
                                    border = if (selectedColor == color) {
                                        CardDefaults.outlinedCardBorder()
                                    } else null
                                ) {
                                    if (selectedColor == color) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("✓", color = Color.White, fontSize = 20.sp)
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    "Birthday: ${selectedDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))}",
                    fontSize = 14.sp
                )
                Text(
                    "(For demo: using current date. In real app, you'd use DatePicker)",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onAdd(name, selectedDate, selectedColor)
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BirthdayTrackerScreenPreview() {
    ChaosTheme {
        BirthdayTrackerScreen()
    }
}