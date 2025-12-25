# MindFlow - Coding Agent Context

## Project Overview

You are helping build **MindFlow**, a Kotlin Multiplatform app that provides CBT-guided journaling with AI conversations, mindmap visualizations, and progress tracking.

**Core Purpose:** Help users explore and reframe negative thought patterns through structured AI conversations, then visualize these patterns as interactive mindmaps.

**Target Platforms:**
- **Android** (API 26+) - Compose Multiplatform UI
- **iOS** (15+) - Compose Multiplatform UI  
- **Web** (Modern browsers) - React UI, shared Kotlin business logic

**Platform Strategy:**
- Android + iOS: Share 100% of UI code (Compose Multiplatform)
- Web: Share business logic only, use React for UI
- All platforms: Share domain layer, data layer, and business logic completely

**Expected Code Sharing:** ~85% overall (100% of logic shared, UI shared for mobile only)

**Contest Entry:** KMP Contest 2025

---

## Architecture Overview

### Clean Architecture Layers

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  Mobile: Compose Multiplatform          │
│  Web: React + TypeScript                │
│  (ViewModels, UI Components, Screens)   │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│          Domain Layer                   │
│  (Use Cases, Business Logic, Models)    │
│  Shared across ALL platforms            │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│           Data Layer                    │
│  (Repositories, API Clients, Database)  │
│  Shared across ALL platforms            │
└─────────────────────────────────────────┘
```

**Pattern:** MVVM (Model-View-ViewModel) with Clean Architecture
- **Presentation**: UI + ViewModels (platform-specific for web, shared for mobile)
- **Domain**: Use cases, models, repository interfaces (100% shared)
- **Data**: Repository implementations, API clients, database (100% shared logic)

---

## Module Structure

```
project/
├── shared/                       # ⭐ Core module - ALL business logic
│   ├── commonMain/              # Shared across all platforms
│   │   ├── domain/              # Use cases, models, interfaces
│   │   └── data/                # Repositories, API, database
│   ├── androidMain/             # Android-specific implementations
│   ├── iosMain/                 # iOS-specific implementations
│   └── jsMain/                  # Web-specific implementations
│
├── composeApp/                   # Mobile app (Android + iOS)
│   ├── commonMain/              # Shared mobile UI
│   │   ├── App.kt              # Root composable
│   │   ├── presentation/        # Screens, ViewModels
│   │   └── theme/               # Material Design theme
│   ├── androidMain/             # Android entry + platform APIs
│   └── iosMain/                 # iOS platform APIs
│
├── web/                          # Web application (React)
│   ├── src/
│   │   ├── components/          # React components
│   │   ├── pages/               # React pages
│   │   ├── hooks/               # React hooks
│   │   └── bridge/              # Kotlin-JS interop
│   └── package.json
│
└── iosApp/                       # iOS app entry point
    └── iosApp.swift
```

**Key Principles:**
1. **shared/** is the heart - contains ALL business logic
2. **composeApp/** for mobile UI only (Compose Multiplatform)
3. **web/** for web UI only (React) but imports **shared** for logic
4. Platform folders handle platform-specific APIs (voice, camera, storage)

---

## Technology Stack

### Core (All Platforms)
- **Kotlin** 2.0+
- **Coroutines** 1.8.0 - Async operations
- **kotlinx-serialization** - JSON parsing
- **kotlinx-datetime** - Date/time handling
- **Ktor Client** 2.3.8 - HTTP networking (works on Android, iOS, JS)

### Mobile (Android + iOS)
- **Compose Multiplatform** 1.7.0+ - Shared UI
- **Material 3** - Design system
- **Voyager** - Navigation
- **Koin** - Dependency injection
- **Room** 2.7.0-alpha01 - Local database

### Web
- **React** 18.2+ with TypeScript
- **React Router** - Navigation
- **Vite** 5.0+ - Build tool
- **@tanstack/react-query** - Data fetching
- **IndexedDB** (via browser APIs) - Local storage

### AI Integration
- **Ktor Client** for calling OpenAI/Anthropic/Gemini APIs
- Works identically across all platforms
- Streaming support for real-time responses

---

## Platform-Specific Implementations

### Expect/Actual Pattern

Kotlin Multiplatform uses `expect/actual` for platform-specific code:

```kotlin
// In commonMain
expect class VoiceInput {
    fun startRecording(onResult: (String) -> Unit)
    fun stopRecording()
}

// In androidMain - uses Android SpeechRecognizer
actual class VoiceInput { ... }

// In iosMain - uses iOS Speech framework
actual class VoiceInput { ... }

// In jsMain - uses Web Speech API
actual class VoiceInput { ... }
```

**Common Platform-Specific Features:**
- Voice input/output
- File system access
- Camera/media access
- Local storage (Room for mobile, IndexedDB for web)
- Platform UI (Compose for mobile, React for web)

---

## Web Platform Integration

### How Kotlin Shares Logic with React

The **shared** module compiles to JavaScript and exposes APIs that React can call:

```
┌─────────────────┐
│   React UI      │
│  (TypeScript)   │
└────────┬────────┘
         │ imports
         ▼
┌─────────────────┐
│  Kotlin/JS      │
│  (from shared)  │
│  - Use cases    │
│  - Repositories │
│  - Models       │
└─────────────────┘
```

**React calls Kotlin:**
```typescript
// In React component
import { SessionRepository, StartConversationUseCase } from './bridge/kotlin'

function ConversationPage() {
  const [messages, setMessages] = useState([])
  
  useEffect(() => {
    const useCase = new StartConversationUseCase()
    useCase.invoke().then(result => {
      // Handle Kotlin response
    })
  }, [])
}
```

**Kotlin exposes APIs:**
```kotlin
// In jsMain - export for JavaScript
@JsExport
class SessionRepositoryJs(private val repo: SessionRepository) {
    fun getMessages(sessionId: String): Promise<Array<Message>> {
        return GlobalScope.promise {
            repo.getMessages(sessionId).toTypedArray()
        }
    }
}
```

**Bridge Pattern:**
- Kotlin code in `shared/jsMain` creates JS-friendly wrappers
- React imports and uses these wrappers
- All business logic stays in Kotlin
- Only UI is React-specific

---

## Data Models (Shared)

All these models are defined once in `shared/commonMain` and used across all platforms:

### Core Entities

**Session** - A journaling session
- id, startTime, endTime, isCompleted
- coreThought, emotionInitial, emotionFinal
- cognitiveDistortions list

**Message** - Chat message in conversation
- id, sessionId, timestamp, role (User/AI)
- content, audioUrl (optional)

**MindmapData** - Mindmap visualization structure
- sessionId, rootNode, nodes, edges
- Auto-generated from session summary

**TrackedThought** - Thought being tracked over time
- id, sessionId, thoughtText
- initialIntensity, checkIns list, status

**CheckIn** - Progress check-in for a thought
- id, thoughtId, intensity (1-10)
- note, timestamp

### Enums

**MessageRole:** User, AI

**NodeType:** CoreThought, CognitiveDistortion, Evidence (For/Against), Alternative, Emotion

**CognitiveDistortionType:** AllOrNothing, Overgeneralization, MentalFiltering, Catastrophizing, Personalization, ShouldStatements, Labeling, EmotionalReasoning, MindReading, FortuneTelling

**ThoughtStatus:** Active, Resolved, Archived

---

## AI Integration (High Level)

### API Client
- Located in `shared/data/remote/AiApiClient.kt`
- Uses Ktor Client (works on all platforms)
- Supports streaming responses for real-time chat
- Methods:
  - `sendChatMessage()` - Stream AI responses
  - `generateSessionSummary()` - Extract core thought, distortions
  - `generateMindmap()` - Create visualization structure

### CBT Prompts
- Located in `shared/domain/prompts/CbtPrompts.kt`
- System prompts guide AI through 6-step CBT framework:
  1. Situation identification
  2. Automatic thought capture
  3. Emotion exploration
  4. Evidence examination
  5. Distortion identification
  6. Alternative perspective generation

**Key Requirements:**
- One question at a time
- Empathetic, non-judgmental tone
- Identify cognitive distortions when present
- Crisis handling (redirect to hotlines)
- Never diagnose or replace therapy

---

## Use Cases (Business Logic)

All use cases are in `shared/domain/usecases/` and work identically on all platforms:

### StartConversationUseCase
- Creates new session in database
- Triggers AI greeting
- Returns conversation state flow

### SendMessageUseCase
- Saves user message
- Sends to AI API with conversation history
- Streams AI response back
- Saves complete AI response

### EndSessionUseCase
- Generates session summary via AI
- Updates session metadata
- Creates mindmap structure
- Creates tracked thought if applicable

### GetProgressUseCase
- Retrieves tracked thoughts
- Calculates progress metrics
- Returns progress state

### CheckInThoughtUseCase
- Records intensity rating (1-10)
- Updates tracked thought
- Triggers celebrations if significant progress

---

## Color Theme

**Earth-Tone Palette** (Brown + Cream)

### Primary Colors (Browns)
```kotlin
val DeepBrown = Color(0xFF6B4423)      // Main brand
val DarkBrown = Color(0xFF543316)      // Pressed states
val MediumBrown = Color(0xFF8B5A3C)    // Accents
val LightBrown = Color(0xFFA67C52)     // Secondary
val Tan = Color(0xFFC19A6B)            // Subtle bridges
```

### Backgrounds
```kotlin
val LightCream = Color(0xFFFFFCB8)     // Main background
val Cream = Color(0xFFE9E294)          // Cards/surfaces
val OffWhite = Color(0xFFFEFEF5)       // Elevated
val LightTan = Color(0xFFF5F0E8)       // Alternative
```

### Semantic (Earth Tones)
```kotlin
val WarmRedBrown = Color(0xFFD84315)   // Errors, distortions
val OliveGreen = Color(0xFF6B8E23)     // Success, alternatives
val GoldenAmber = Color(0xFFE8A13C)    // Warnings, evidence
```

### Text
```kotlin
val TextDarkBrown = Color(0xFF2B1810)  // On cream
val TextOnBrown = Color(0xFFFFFEF8)    // On brown
val TextSecondary = Color(0xFF6B5447)  // Secondary
```

### Mindmap Node Colors
- Core Thought: Deep Brown
- Cognitive Distortion: Warm Red-Brown
- Evidence For: Golden Amber
- Evidence Against: Olive Green
- Alternative: Light Brown
- Emotion: Medium Brown
- Connection lines: Tan at 60% alpha
- Shadows: Brown-tinted, never gray

**Design Philosophy:** Warm, cohesive, journal-like feel. Brown naturally extends from cream creating a calming, trustworthy aesthetic for mental health journaling.

---

## State Management Patterns

### Mobile (Compose)
```kotlin
// ViewModel with StateFlow
class ConversationViewModel : ViewModel() {
    private val _state = MutableStateFlow<ConversationState>(Idle)
    val state: StateFlow<ConversationState> = _state.asStateFlow()
    
    fun sendMessage(content: String) {
        viewModelScope.launch {
            sendMessageUseCase(content).collect { state ->
                _state.value = state
            }
        }
    }
}

// Sealed class for states
sealed interface ConversationState {
    data object Idle : ConversationState
    data class MessageSent(val message: Message) : ConversationState
    data object AiTyping : ConversationState
    data class MessageReceived(val message: Message) : ConversationState
    data class Error(val message: String) : ConversationState
}
```

### Web (React)
```typescript
// Custom hook using React Query
function useConversation(sessionId: string) {
  const [messages, setMessages] = useState<Message[]>([])
  
  const sendMessage = useMutation({
    mutationFn: async (content: string) => {
      const useCase = new SendMessageUseCase()
      return await useCase.invoke(sessionId, content)
    },
    onSuccess: (newMessage) => {
      setMessages(prev => [...prev, newMessage])
    }
  })
  
  return { messages, sendMessage }
}
```

**Both platforms use the same Kotlin use cases under the hood!**

---

## Navigation

### Mobile (Voyager)
```kotlin
// Screen-based navigation
sealed class Screen {
    object Home : Screen()
    data class Conversation(val sessionId: String) : Screen()
    data class Mindmap(val sessionId: String) : Screen()
    object Progress : Screen()
    object Settings : Screen()
}

// Navigator usage
Navigator(HomeScreen()) { navigator ->
    // Navigate to conversation
    navigator.push(ConversationScreen(sessionId))
}
```

### Web (React Router)
```typescript
// Route definitions
<Routes>
  <Route path="/" element={<HomePage />} />
  <Route path="/conversation/:sessionId" element={<ConversationPage />} />
  <Route path="/mindmap/:sessionId" element={<MindmapPage />} />
  <Route path="/progress" element={<ProgressPage />} />
  <Route path="/settings" element={<SettingsPage />} />
</Routes>
```

---

## Data Persistence

### Mobile (Room)
```kotlin
// In shared/androidMain and shared/iosMain
@Database(entities = [Session::class, Message::class, TrackedThought::class, CheckIn::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}

// DAOs provide suspend functions
interface SessionDao {
    @Query("SELECT * FROM sessions")
    suspend fun getAllSessions(): List<Session>
    
    @Insert
    suspend fun insertSession(session: Session)
}
```

### Web (IndexedDB via Kotlin/JS)
```kotlin
// In shared/jsMain
actual class SessionRepositoryImpl : SessionRepository {
    private val db = IndexedDB("mindflow-db")
    
    override suspend fun getAllSessions(): List<Session> {
        return db.getAll("sessions").map { it.toSession() }
    }
    
    override suspend fun insertSession(session: Session) {
        db.put("sessions", session.toJs())
    }
}
```

**Same interface, different implementations!**

---

## Development Workflow

### Running the Project

**Android:**
```bash
./gradlew :composeApp:installDebug
# Or open in Android Studio and run
```

**iOS:**
```bash
# Open iosApp/iosApp.xcworkspace in Xcode
# Select device and run
```

**Web:**
```bash
cd web
npm install
npm run dev  # Starts Vite dev server
```

### Building for Production

**Mobile:**
```bash
./gradlew :composeApp:assembleRelease  # Android APK
# iOS: Archive in Xcode
```

**Web:**
```bash
cd web
npm run build  # Creates optimized production build
```

---

## Testing Strategy

### Unit Tests (Shared Logic)
```kotlin
// Test use cases and business logic
// Located in shared/commonTest
class StartConversationUseCaseTest {
    @Test
    fun `should create session and return greeting`() = runTest {
        val fakeRepo = FakeSessionRepository()
        val useCase = StartConversationUseCase(fakeRepo, fakeAiClient)
        
        val states = mutableListOf<ConversationState>()
        useCase().collect { states.add(it) }
        
        assertTrue(states.any { it is ConversationState.SessionCreated })
        assertTrue(states.any { it is ConversationState.MessageReceived })
    }
}
```

### UI Tests
- **Mobile**: Compose UI testing
- **Web**: React Testing Library + Jest

### Integration Tests
- Test full flows (conversation creation → AI response → mindmap generation)
- Use fake repositories and mock AI responses

---

## Common Patterns & Best Practices

### 1. Error Handling
- Use `Result<T>` for operations that can fail
- Sealed classes for state representation
- Display user-friendly error messages
- Always handle network failures gracefully

### 2. Dependency Injection
- **Mobile**: Koin for DI
- **Web**: React Context API
- Both share the same repository and use case interfaces

### 3. Async Operations
- Use Kotlin Coroutines everywhere
- `Flow` for streams (AI responses, state updates)
- `suspend` functions for single async operations

### 4. Code Sharing Mindset
- Write business logic once in `shared/commonMain`
- Use `expect/actual` only when truly platform-specific
- Keep platform code thin (just wrappers around native APIs)

---

## Critical Implementation Notes

### Performance
- Lazy load conversation messages (pagination)
- Cache mindmap layouts (expensive computation)
- Debounce voice input API calls
- Use database indexes (sessionId, timestamp)

### Security
- Store API keys in environment variables (never commit)
- Encrypt sensitive user data at rest (if needed)
- Request permissions with clear rationale

### Accessibility
- Content descriptions for all interactive elements
- Support system font size settings
- Minimum 48dp touch targets (mobile)
- WCAG AA contrast ratios (all platforms)

### UX Polish
- Loading states (never show blank screens)
- Skeleton screens during data loading
- Error recovery with retry buttons
- Haptic feedback for voice recording (mobile)
- Smooth animations (300-400ms, ease-out curves)
- Brown-tinted shadows (never harsh grays)

---

## Web-Specific Considerations

### Kotlin/JS Compilation
- Kotlin code in `shared` compiles to JavaScript
- Use `@JsExport` for APIs consumed by React
- Wrap suspending functions in `Promise` for JavaScript

### Browser APIs
- IndexedDB for local storage
- Web Speech API for voice input/output
- Web Audio API (if needed for sound)
- Service Workers for offline support (optional)

### Performance
- Code splitting for lazy loading
- Tree shaking to reduce bundle size
- Optimize Kotlin/JS compilation output

### Deployment
- Static site (React SPA + Kotlin/JS bundle)
- Deploy to Vercel, Netlify, or GitHub Pages
- CDN for static assets

---

## Key Reminders for Coding Agents

### For Shared Logic (shared/commonMain)
- This code runs on ALL platforms
- Avoid platform-specific APIs
- Use `expect/actual` for truly platform-specific needs
- Write comprehensive unit tests

### For Mobile UI (composeApp/commonMain)
- Compose Multiplatform works on Android + iOS
- Use Material 3 components
- Follow earth-tone color scheme (browns + creams)
- Implement for both platforms simultaneously

### For Web UI (web/src)
- React + TypeScript
- Import Kotlin business logic from bridge
- Maintain similar UX to mobile
- Use same earth-tone palette
- Progressive Web App features optional

### For Platform-Specific Code
- Keep it minimal
- Create thin wrappers around native APIs
- Document platform differences
- Test on real devices

---

## Success Checklist

Before marking a feature complete:
- [ ] Works on Android, iOS, and Web
- [ ] Shared logic is in `shared/commonMain`
- [ ] UI is platform-appropriate (Compose for mobile, React for web)
- [ ] Handles errors gracefully
- [ ] Loading states implemented
- [ ] Accessible (content descriptions, screen reader tested)
- [ ] Follows earth-tone color scheme
- [ ] Unit tests for business logic
- [ ] Manual testing on real devices/browsers

---

## Quick Reference

### File Locations
- Business logic: `shared/commonMain/domain` and `shared/commonMain/data`
- Mobile UI: `composeApp/commonMain/presentation`
- Web UI: `web/src/components` and `web/src/pages`
- Platform APIs: `*Main/platform/` in respective modules

### Common Commands
```bash
# Build everything
./gradlew build

# Run Android
./gradlew :composeApp:installDebug

# Run iOS (from Xcode)
open iosApp/iosApp.xcworkspace

# Run Web
cd web && npm run dev

# Run tests
./gradlew test
```

### Getting Help
- Kotlin Multiplatform Docs: kotlinlang.org/docs/multiplatform.html
- Compose Multiplatform: jetbrains.com/lp/compose-multiplatform/
- React Docs: react.dev

---

## Final Notes

**This is a contest entry.** Code quality, architecture, and polish matter as much as features. The goal is to showcase:

1. **Effective code sharing** - 85%+ shared code across platforms
2. **Platform-appropriate UX** - Native feel on each platform
3. **Clean architecture** - Maintainable, testable, scalable
4. **Innovation** - Mindmap visualization as differentiator
5. **Real-world utility** - Solves genuine mental health accessibility problem

The multiplatform approach demonstrates that Kotlin can power mobile (Android + iOS) AND web applications with massive code reuse while maintaining excellent user experience on each platform.

Build with care. Make every interaction delightful. Good luck! 🚀
