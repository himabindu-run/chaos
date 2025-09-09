package com.example.chaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaos.ui.theme.ChaosTheme

data class Person(
    val name: String,
    val profession: String,
    val skills: List<String>,
    val favoriteColor: Color
)

class RecyclerViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChaosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecyclerViewScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RecyclerViewScreen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Simple List", "Person Cards", "Interactive List")
    
    Column(modifier = modifier.fillMaxSize()) {
        // Header
        Text(
            text = "RecyclerView Teaching Examples",
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
                    text = { Text(title, fontSize = 14.sp) }
                )
            }
        }
        
        // Content based on selected tab
        when (selectedTab) {
            0 -> SimpleListExample()
            1 -> PersonCardsExample()
            2 -> InteractiveListExample()
        }
    }
}

@Composable
fun SimpleListExample() {
    val fruits = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew")
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Simple String List",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Show students basic list display with LazyColumn",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(fruits) { fruit ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = fruit,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PersonCardsExample() {
    val people = listOf(
        Person("Alex Johnson", "Android Developer", listOf("Kotlin", "Java", "UI Design"), Color.Blue),
        Person("Maria Garcia", "UX Designer", listOf("Figma", "Sketch", "User Research"), Color.Green),
        Person("James Chen", "Backend Engineer", listOf("Python", "AWS", "Docker"), Color.Red),
        Person("Sarah Wilson", "Data Scientist", listOf("Python", "Machine Learning", "SQL"), Color.Magenta),
        Person("Mike Thompson", "DevOps Engineer", listOf("Kubernetes", "CI/CD", "Monitoring"), Color.Cyan)
    )
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Data Class with Complex Cards",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Teach: Person data class, custom layouts, avatar generation",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(people) { person ->
                PersonCard(person = person)
            }
        }
    }
}

@Composable
fun PersonCard(person: Person) {
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
            // Avatar circle with initials
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
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = person.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = person.profession,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Skills as chips
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    person.skills.take(2).forEach { skill ->
                        SuggestionChip(
                            onClick = { },
                            label = { Text(skill, fontSize = 10.sp) }
                        )
                    }
                    if (person.skills.size > 2) {
                        Text(
                            text = "+${person.skills.size - 2} more",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InteractiveListExample() {
    var items by remember { mutableStateOf(listOf("Task 1", "Task 2", "Task 3")) }
    var newItemText by remember { mutableStateOf("") }
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Demo: Interactive List Operations",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Show: Dynamic list updates, state management, user input",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Add new item section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newItemText,
                onValueChange = { newItemText = it },
                label = { Text("New item") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newItemText.isNotBlank()) {
                        items = items + newItemText
                        newItemText = ""
                    }
                }
            ) {
                Text("Add")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // List of items
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
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
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { items = items - item },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Remove")
                        }
                    }
                }
            }
        }
        
        if (items.isEmpty()) {
            Text(
                text = "No items yet. Add some above!",
                modifier = Modifier.padding(32.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecyclerViewScreenPreview() {
    ChaosTheme {
        RecyclerViewScreen()
    }
}