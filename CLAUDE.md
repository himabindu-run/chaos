# Teaching Demo App - Setup Instructions

## Purpose
This app is designed for **instructor demonstration** during Android development classes. It showcases key concepts for Week 2 (Layouts & Interactions) and Week 3 (Lists & Data) through interactive examples.

## Build Setup Required

### JDK Configuration Issue Fix
If you encounter the error: `"Unable to find gradle tasks to build"` or `"Toolchain installation does not provide the required capabilities: [JAVA_COMPILER]"`

**Solution in Android Studio:**
1. Open **File** → **Settings** → **Build, Execution, Deployment** → **Build Tools** → **Gradle**
2. Under **Gradle JDK**, select **Embedded JDK** (recommended)
3. Apply changes and restart Android Studio
4. **Build** → **Clean Project**
5. **Build** → **Rebuild Project**

**Alternative:** Install full JDK if needed:
```bash
sudo apt install openjdk-17-jdk
```

## Teaching Structure

### Week 2 Demo Content
- **Layout Comparison**: XML vs Compose examples
- **Interactions**: Button clicks and functions  
- **Complete Example**: Dice rolling game

### Week 3 Demo Content
- **Demo 1**: RecyclerView fundamentals (adapters, ViewHolders)
- **Demo 2**: Collections (Lists, Sets, Maps with practical examples)
- **Demo 3**: Complete Birthday Tracker app with animations

## How to Use for Teaching

1. **Start with home screen** - explain the app structure
2. **Navigate through examples** - demonstrate each concept live
3. **Highlight key teaching points** shown in the UI descriptions
4. **Use interactive elements** to engage students
5. **Show complete birthday app** as the culminating example

## Key Teaching Points Covered

- Data classes and object-oriented concepts
- List operations (sorting, filtering, grouping)
- Date handling and calculations
- UI state management
- Animations and user interactions
- Complete app architecture

The app is designed to provide concrete, interactive examples for each concept you're teaching about lists and data in Android development.