# Chaos - Android Learning App

An android app for students to follow while learning in the 12 week bootcamp.

## 🏠 App Structure

This app is organized into weekly learning modules:

## 📱 Week 2: Layouts and Interactions

An educational Android app demonstrating layout techniques and interactive UI development for Week 2 of a 12-week Android development bootcamp.

### Layout Comparison Demo
- **XML Layout**: Traditional Android layout using LinearLayout and XML resources
- **Jetpack Compose Layout**: Modern declarative UI showing the same content

### Learning Topics

1. **Making Better Layouts** - Interactive demos of:
   - LinearLayout with weight system
   - ConstraintLayout positioning
   - Material Design components

2. **Making Things Clickable** - Hands-on examples of:
   - Button click handlers
   - State management
   - Random number generation
   - Interactive color changes

3. **Build a Dice Game** - Complete project featuring:
   - Animated dice rolling
   - Single and double dice modes
   - Roll history tracking
   - Lucky number detection
   - Statistics display

## 📚 Week 3: Lists and Data

Week 3 introduces working with collections and displaying data in lists - essential skills for any Android app.

### Hour 1: RecyclerView Fundamentals
- **Simple Lists**: Basic string lists with LazyColumn
- **Data Classes**: Person objects with custom card layouts
- **Interactive Lists**: Add/remove functionality with state management

### Hour 2: Working with Collections
- **Lists**: Sorting, filtering, and manipulation operations
- **Sets**: Union, intersection, and difference operations
- **Maps**: Key-value relationships with practical examples
- **Real-World Data**: Birthday processing with date calculations

### Hour 3: Complete Birthday Tracker App
- **Scrollable Lists**: Display birthdays with custom layouts
- **Date Handling**: Countdown calculations and birthday detection  
- **Add/Remove**: Form input and list management
- **Animations**: Confetti effects and birthday celebrations
- **Data Processing**: Grouping by month, sorting, filtering

## 🎓 For Students

This app provides hands-on examples and interactive demos to help you learn:

**Week 2 Skills:**
- How to organize layouts effectively
- Making buttons respond to user interactions  
- Building a complete app from scratch

**Week 3 Skills:**
- Working with Kotlin collections (Lists, Sets, Maps)
- Displaying data in scrollable lists
- Managing app state and user input
- Processing and manipulating data
- Creating engaging user experiences with animations

Each section builds on the previous one, so start with Week 2 and progress through to Week 3!

## 🛠️ Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose + XML layouts
- **Architecture**: Activity-based navigation
- **Design**: Material Design 3
- **Target SDK**: 36
- **Min SDK**: 26

## 🏃‍♂️ Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle files
4. Run on device or emulator

## 🎨 App Structure

```
MainActivity → Weekly navigation hub
├── Week2Activity → Week 2 content
│   ├── AboutXmlActivity → XML layout example
│   ├── AboutComposeActivity → Compose layout example  
│   ├── Hour1LayoutsActivity → Layout demonstrations
│   ├── Hour2FunctionsActivity → Interaction examples
│   └── Hour3DiceRollerActivity → Complete dice game project
└── Week3Activity → Week 3 content
    ├── RecyclerViewActivity → List fundamentals
    ├── CollectionsActivity → Data manipulation examples
    └── BirthdayTrackerActivity → Complete app project
```

## 🚀 Key Learning Outcomes

By exploring this app, students will understand:
- Modern Android UI development with Jetpack Compose
- Traditional XML layouts and when to use each approach
- Data classes and object-oriented programming in Kotlin
- Collection operations and functional programming concepts
- State management in Android applications
- Building complete, interactive applications
