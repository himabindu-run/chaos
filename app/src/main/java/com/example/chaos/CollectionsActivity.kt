package com.example.chaos

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class Birthday(
    val name: String,
    val date: LocalDate,
    val month: String = date.format(DateTimeFormatter.ofPattern("MMMM"))
) {
    fun daysUntilBirthday(): Long {
        val today = LocalDate.now()
        var nextBirthday = date.withYear(today.year)
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.withYear(today.year + 1)
        }
        return ChronoUnit.DAYS.between(today, nextBirthday)
    }
    
    fun isToday(): Boolean {
        val today = LocalDate.now()
        return date.month == today.month && date.dayOfMonth == today.dayOfMonth
    }
}

class CollectionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CollectionsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CollectionsScreen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Lists", "Sets", "Maps", "Birthday Data")
    
    Column(modifier = modifier.fillMaxSize()) {
        // Header
        Text(
            text = "Collections Teaching Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        // Tab selector
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title, fontSize = 12.sp) }
                )
            }
        }
        
        // Content based on selected tab
        when (selectedTab) {
            0 -> ListsExample()
            1 -> SetsExample()
            2 -> MapsExample()
            3 -> BirthdayDataExample()
        }
    }
}

@Composable
fun ListsExample() {
    var numbers by remember { mutableStateOf(listOf(5, 2, 8, 1, 9, 3)) }
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Lists - Ordered Collections",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Teach: sorting, filtering, list operations",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Original List: $numbers",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { numbers = numbers.sorted() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Sort", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { numbers = numbers.filter { it > 5 } },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Filter >5", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { numbers = listOf(5, 2, 8, 1, 9, 3) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reset", fontSize = 12.sp)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "String List Operations:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        val fruits = remember { mutableListOf("Apple", "Banana", "Cherry", "Apple", "Date") }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Text("• Original: $fruits", fontSize = 14.sp)
            }
            item {
                Text("• Distinct: ${fruits.distinct()}", fontSize = 14.sp)
            }
            item {
                Text("• First 3: ${fruits.take(3)}", fontSize = 14.sp)
            }
            item {
                Text("• Contains 'Apple': ${fruits.contains("Apple")}", fontSize = 14.sp)
            }
            item {
                Text("• Size: ${fruits.size}", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun SetsExample() {
    val hobbies1 = setOf("Reading", "Gaming", "Cooking", "Hiking")
    val hobbies2 = setOf("Gaming", "Swimming", "Cooking", "Dancing")
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Sets - No Duplicates",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Show: union, intersection, difference operations",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Person A hobbies:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(hobbies1.joinToString(", "), fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text("Person B hobbies:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(hobbies2.joinToString(", "), fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text("Common hobbies:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(
                    (hobbies1 intersect hobbies2).joinToString(", "),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text("All hobbies combined:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(
                    (hobbies1 union hobbies2).joinToString(", "),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text("Only Person A has:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(
                    (hobbies1 - hobbies2).joinToString(", "),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Set Operations Demo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MapsExample() {
    val studentGrades = mapOf(
        "Alice" to 95,
        "Bob" to 87,
        "Charlie" to 92,
        "Diana" to 98,
        "Eve" to 83
    )
    
    val countryCapitals = mapOf(
        "France" to "Paris",
        "Japan" to "Tokyo",
        "Brazil" to "Brasília",
        "Australia" to "Canberra",
        "Canada" to "Ottawa"
    )
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Maps - Key-Value Pairs",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Explain: lookups, data relationships, practical examples",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Student grades example
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Student Grades Map:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                studentGrades.forEach { (name, grade) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(name, fontSize = 14.sp)
                        Text(
                            "$grade%",
                            fontSize = 14.sp,
                            color = when {
                                grade >= 90 -> Color.Green
                                grade >= 80 -> Color.Blue
                                else -> Color.Red
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    "Average: ${studentGrades.values.average().toInt()}%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Country capitals example
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Country-Capital Map:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                countryCapitals.forEach { (country, capital) ->
                    Text(
                        "$country → $capital",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BirthdayDataExample() {
    val birthdays = remember {
        listOf(
            Birthday("Alice Johnson", LocalDate.of(1995, 3, 15)),
            Birthday("Bob Smith", LocalDate.of(1992, 7, 22)),
            Birthday("Charlie Brown", LocalDate.of(1998, 12, 3)),
            Birthday("Diana Prince", LocalDate.of(1990, 1, 8)),
            Birthday("Eve Adams", LocalDate.of(1996, 9, 30)),
            Birthday("Today Person", LocalDate.now()) // Someone with birthday today
        )
    }
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Real-World Data Processing",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Show: date handling, grouping, filtering, calculations",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Group by month
        val birthdaysByMonth = birthdays.groupBy { it.month }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Birthdays Today:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                val todayBirthdays = birthdays.filter { it.isToday() }
                if (todayBirthdays.isNotEmpty()) {
                    todayBirthdays.forEach { birthday ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.Yellow.copy(alpha = 0.3f))
                        ) {
                            Text(
                                "🎉 ${birthday.name} - Happy Birthday! 🎂",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    Text("No birthdays today", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            
            item {
                Text(
                    text = "Next 5 Birthdays:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(birthdays.sortedBy { it.daysUntilBirthday() }.take(5)) { birthday ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                birthday.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                birthday.date.format(DateTimeFormatter.ofPattern("MMMM d")),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        Text(
                            "${birthday.daysUntilBirthday()} days",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "Grouped by Month:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            
            items(birthdaysByMonth.toList()) { (month, monthBirthdays) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "$month (${monthBirthdays.size})",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        monthBirthdays.forEach { birthday ->
                            Text(
                                "• ${birthday.name}",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionsScreenPreview() {
    ChaosTheme {
        CollectionsScreen()
    }
}