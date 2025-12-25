# Product Requirements Document: MindFlow
## CBT Journaling Companion - KMP Contest 2025

**Version:** 1.0  
**Last Updated:** December 2025  
**Target Platforms:** iOS 15+, Android 8+  
**Status:** Pre-Development

---

## 1. Product Overview

### 1.1 Product Description
MindFlow is a Kotlin Multiplatform mobile application that provides structured self-reflection through AI-guided CBT (Cognitive Behavioral Therapy) conversations, innovative mindmap visualizations, and progress tracking.

### 1.2 Target Users
- Adults (18+) seeking structured journaling methods
- Individuals interested in understanding thought patterns
- People between therapy sessions wanting maintenance tools
- Personal development enthusiasts
- Students learning CBT concepts

### 1.3 Core Value Proposition
Transform scattered thoughts into structured insights through AI-guided conversations, then visualize and track progress with interactive mindmaps.

### 1.4 Success Metrics
- 70%+ session completion rate
- 50%+ users return within 7 days
- 80%+ users report feeling better post-session
- 60%+ mindmap review engagement

---

## 2. Feature Requirements

### 2.1 Feature 1: CBT Conversation Engine

#### 2.1.1 Functional Requirements

**FR-1.1: Session Initiation**
- User can start new session from home screen
- System displays welcoming message and initial prompt
- User can choose text or voice input mode
- Session begins with open-ended question: "What's on your mind today?"

**FR-1.2: Conversational Flow**
- AI follows 6-step CBT structure:
  1. Situation identification
  2. Automatic thought capture
  3. Emotional response identification
  4. Evidence examination (for/against)
  5. Cognitive distortion recognition
  6. Alternative perspective generation
- AI asks one question at a time
- System waits for user response before continuing
- AI maintains context throughout session

**FR-1.3: AI Response Generation**
- Responses must be empathetic and non-judgmental
- Questions should be open-ended (not yes/no when exploring)
- System identifies cognitive distortions when present
- AI adapts follow-up questions based on user's answers
- Responses should feel conversational, not clinical

**FR-1.4: Session Management**
- User can pause session (auto-saves progress)
- User can end session early (triggers summary)
- Session auto-saves every 5 messages
- Maximum session length: 50 exchanges (prevents infinite loops)
- Session summary generated at conclusion

**FR-1.5: Cognitive Distortion Detection**
System must recognize and gently highlight these distortions:
- All-or-nothing thinking
- Overgeneralization
- Mental filtering
- Catastrophizing
- Personalization
- Should statements
- Labeling
- Emotional reasoning
- Mind reading
- Fortune telling

#### 2.1.2 UI Requirements

**Conversation Interface:**
- Chat-style UI with messages aligned left (AI) and right (User)
- Timestamp on each message
- Typing indicator when AI is generating response
- Smooth scroll to latest message
- Message bubbles with subtle shadows
- Clean, calming earth-tone color scheme

**Input Area:**
- Text input field at bottom
- Microphone button for voice input
- Send button (disabled when empty)
- Character counter (optional, if implementing limits)
- Clear visual feedback for recording state

**Session Controls:**
- Pause button (top right)
- End session button (with confirmation)
- Session timer/message count (subtle, top bar)

#### 2.1.3 Non-Functional Requirements
- AI response latency: <3 seconds for 90th percentile
- Message send should feel instant (<100ms perceived delay)
- Smooth scrolling (60fps)
- Graceful handling of network errors
- Offline mode: Show informative message, save inputs for retry

---

### 2.2 Feature 2: Mindmap Visualization

#### 2.2.1 Functional Requirements

**FR-2.1: Mindmap Generation**
- System automatically generates mindmap from completed session
- Central node: Core negative thought (AI identifies primary concern)
- Primary branches (4-6):
  - Cognitive distortion type(s)
  - Supporting evidence
  - Contradicting evidence
  - Alternative perspectives
  - Emotional shifts
- Secondary branches: Specific examples from conversation

**FR-2.2: Mindmap Interaction**
- User can pan across mindmap (drag gesture)
- User can zoom in/out (pinch gesture or buttons)
- Tapping a node:
  - Highlights node
  - Shows tooltip with node content
  - Displays "View Conversation" button
- "View Conversation" navigates to specific messages related to node
- Double-tap returns to centered view

**FR-2.3: Mindmap Navigation**
- Breadcrumb showing: Session Title > Mindmap
- "Back to Conversation" button
- "Share" button (exports as image)
- "Edit Labels" option (rename nodes if AI misidentified)

**FR-2.4: Multiple Session Comparison** (Nice-to-have)
- View mindmaps from different sessions side-by-side
- Highlight recurring cognitive distortions across sessions

#### 2.2.2 UI Requirements

**Visual Design:**
- Clean, modern mindmap aesthetic with earthy, organic feel
- Color coding:
  - Central node: Deep Brown (#6B4423) - grounding the core thought
  - Cognitive distortions: Warm Red-Brown (#D84315)
  - Evidence against: Olive Green (#6B8E23)
  - Evidence for: Golden Amber (#E8A13C)
  - Alternatives: Light Brown (#A67C52)
  - Emotions: Medium Brown (#8B5A3C)
- Node shapes vary by type (circles, rectangles, rounded rectangles)
- Connecting lines with subtle curves (tan #C19A6B at 60% opacity)
- Soft shadows for depth (no harsh blacks)
- Smooth, organic animations when opening/expanding

**Layout:**
- Radial layout (branches spread evenly around center)
- Auto-sizing nodes based on content length
- Collision detection (nodes don't overlap)
- Responsive to different screen sizes

**Controls:**
- Zoom buttons (+ / -) in corner
- Reset view button (returns to centered, default zoom)
- Toggle to show/hide secondary branches

#### 2.2.3 Technical Requirements
- Mindmap must render in <2 seconds for typical session
- Support up to 20 nodes without performance degradation
- Export functionality generates PNG at 2x resolution
- Layout algorithm must be deterministic (same input = same layout)

---

### 2.3 Feature 3: Progress Tracking System

#### 2.3.1 Functional Requirements

**FR-3.1: Session Storage**
- All sessions saved with metadata:
  - Date/time
  - Duration
  - Message count
  - Core negative thought (AI-identified)
  - Cognitive distortions found
  - Initial emotion intensity (1-10 scale)
  - Final emotion intensity (1-10 scale)
- Sessions organized chronologically
- Quick filtering by cognitive distortion type

**FR-3.2: Thought Check-ins**
- System identifies "core thoughts" from sessions
- After 3 days, prompts: "You mentioned [thought] - how true does this feel now?" (1-10 scale)
- Repeat check-in after 7 days, 14 days, 30 days
- User can skip check-ins
- Track intensity changes over time

**FR-3.3: Progress Dashboard**
- Home screen shows:
  - Total sessions count
  - Current streak (consecutive days with sessions)
  - Active tracked thoughts (list with progress bars)
  - Recent session thumbnails
- "View Full Progress" navigates to detailed analytics

**FR-3.4: Progress Analytics**
- Line graph showing thought intensity over time (per thought)
- Bar chart of cognitive distortion frequency
- Success celebrations when:
  - Thought intensity drops >3 points
  - 5 sessions completed
  - 30-day streak achieved
- Insights generated: "Your catastrophizing has decreased 40% this month"

#### 2.3.2 UI Requirements

**Home Dashboard:**
- Card-based layout
- Progress cards show:
  - Thought label (truncated if long)
  - Progress bar (initial intensity → current intensity)
  - Last check-in date
  - "Check in now" button if due
- Visual hierarchy: Active items larger than history

**Analytics Screen:**
- Tab-based navigation:
  - Tab 1: Thought Progress (graphs per thought)
  - Tab 2: Pattern Insights (cognitive distortions over time)
  - Tab 3: Session History (list of all sessions)
- Charts with clear labels and legends
- Date range selector (Last 7 days, 30 days, 90 days, All time)

**Check-in Prompt:**
- Modal or bottom sheet
- Shows original thought
- Slider for 1-10 rating with labels ("Completely false" to "Absolutely true")
- Optional text input for notes
- Submit button

#### 2.3.3 Technical Requirements
- Historical data should load in <1 second
- Charts must render smoothly (no jank)
- Support minimum 100 sessions without performance issues
- Data export to CSV available

---

### 2.4 Feature 4: Multimodal Input/Output

#### 2.4.1 Functional Requirements

**FR-4.1: Voice Input**
- Hold-to-record interaction pattern
- Real-time visual feedback while recording
- Automatic speech-to-text transcription
- Text appears in input field after release
- User can edit transcription before sending
- Maximum recording duration: 60 seconds
- Error handling for denied microphone permissions

**FR-4.2: Voice Output**
- Toggle in settings: "AI Voice Responses" (on/off)
- When enabled, AI messages are read aloud automatically
- User can stop playback by tapping message
- Playback indicator shows which message is being read
- Voice characteristics:
  - Conversational tone (not robotic)
  - Natural pacing
  - Gender-neutral or user-selectable
- Playback continues even if screen locks (audio-only mode)

**FR-4.3: Platform-Specific Implementation**
- iOS: Use Speech framework for STT, AVFoundation for TTS
- Android: Use SpeechRecognizer for STT, TextToSpeech for TTS
- Shared Kotlin code handles UI state and business logic
- Platform-specific code wrapped in expect/actual declarations

#### 2.4.2 UI Requirements

**Voice Input UI:**
- Microphone button replaces send button when input is empty
- When pressed:
  - Button background changes color (visual feedback)
  - Waveform animation appears
  - Recording duration timer shows
- On release:
  - Transcription appears in input field with subtle animation
  - Edit cursor positioned at end
  - Send button now enabled

**Voice Output UI:**
- Speaker icon on AI messages when voice is enabled
- Animated equalizer bars during playback
- Tap anywhere on message to stop
- Settings toggle clearly labeled

**Settings Screen:**
- "Voice Features" section
- Toggle: "Enable Voice Input" (default: on)
- Toggle: "AI Voice Responses" (default: off)
- Voice selection dropdown (if multiple voices available)
- "Test Voice" button to hear sample

#### 2.4.3 Technical Requirements
- STT accuracy target: >85% for clear speech
- TTS must sound natural (not choppy or robotic)
- Latency: Voice transcription <2 seconds
- Handle background noise gracefully
- Clear error messages for permissions/connectivity issues

---

## 3. User Flows

### 3.1 First-Time User Flow
1. **App Launch** → Splash screen → Onboarding (3 screens)
   - Screen 1: "Welcome to MindFlow" (overview)
   - Screen 2: "How it works" (CBT explanation)
   - Screen 3: "Important Note" (disclaimers, crisis resources)
2. **Home Screen** → Shows "Start Your First Session" CTA
3. **Tap CTA** → Conversation screen opens
4. **AI greets user** → "I'm here to help you explore your thoughts. What's on your mind today?"
5. **User responds** (text or voice) → AI asks follow-up questions
6. **Session continues** → 10-20 exchanges following CBT structure
7. **Session ends** → Summary screen with "View Mindmap" button
8. **User taps "View Mindmap"** → Mindmap visualization opens
9. **User explores mindmap** → Taps nodes, sees insights
10. **Return to home** → Dashboard shows first session, explains progress tracking

### 3.2 Returning User Flow
1. **App Launch** → Home dashboard
2. **User sees**:
   - Active tracked thoughts with progress
   - Recent sessions
   - "Start New Session" button
3. **Choose action**:
   - **Option A**: Start new session (same as first-time flow from step 3)
   - **Option B**: View past session → Opens session detail with mindmap
   - **Option C**: Complete check-in → Rate thought intensity
   - **Option D**: View progress analytics → See graphs and insights

### 3.3 Check-in Flow
1. **Notification appears** (3/7/14 days after session): "How do you feel about [thought] now?"
2. **User opens app** → Check-in prompt displayed
3. **User sees**:
   - Original thought text
   - Previous rating(s) if any
   - Slider to rate current intensity
4. **User adjusts slider** → Selects 1-10
5. **Optional**: Add note about change
6. **Tap "Submit"** → Data saved, progress bar updates
7. **Celebration if significant improvement** → "Your belief in this thought has decreased 40%!"

### 3.4 Mindmap Review Flow
1. **From home** → Tap on session thumbnail
2. **Session detail screen** → Shows:
   - Date/time
   - Emotion shift (before/after)
   - "View Conversation" button
   - "View Mindmap" button (primary CTA)
3. **Tap "View Mindmap"** → Mindmap renders
4. **User interacts**:
   - Pan/zoom to explore
   - Tap cognitive distortion node → Tooltip: "All-or-nothing thinking"
   - Tap "View Conversation" → Navigates to specific messages where distortion appeared
5. **Navigate back** → Return to mindmap or session detail

---

## 4. Information Architecture

### 4.1 Screen Hierarchy

```
App
├── Splash Screen (first launch only)
├── Onboarding (first launch only)
│   ├── Welcome
│   ├── How It Works
│   └── Disclaimers
├── Home Dashboard (main entry)
│   ├── Active Tracked Thoughts (cards)
│   ├── Recent Sessions (thumbnails)
│   ├── Start New Session (CTA)
│   └── View Progress (link to analytics)
├── Conversation Screen
│   ├── Chat Interface
│   ├── Input Area (text/voice)
│   └── Session Controls (pause, end)
├── Session Summary
│   └── View Mindmap (CTA)
├── Mindmap Visualization
│   ├── Interactive Canvas
│   ├── Zoom Controls
│   └── Export Options
├── Session Detail
│   ├── Metadata
│   ├── View Conversation
│   └── View Mindmap
├── Progress Analytics
│   ├── Thought Progress Tab
│   ├── Pattern Insights Tab
│   └── Session History Tab
├── Check-in Modal
│   ├── Thought Display
│   ├── Intensity Slider
│   └── Submit Button
└── Settings
    ├── Voice Settings
    ├── Notification Preferences
    ├── Data Export
    ├── About & Disclaimers
    └── Crisis Resources
```

### 4.2 Navigation Patterns

**Primary Navigation:**
- Bottom tab bar (if expanding to multiple top-level sections) OR
- Single home screen with navigation to all major features

**Secondary Navigation:**
- Top app bar with back button
- Modal sheets for temporary actions (check-ins, settings)
- Deep linking from notifications to check-in prompts

---

## 5. Data Models

### 5.1 Core Entities

#### Session
```
Session {
  id: UUID
  userId: UUID (for future multi-user support)
  startTime: DateTime
  endTime: DateTime?
  isCompleted: Boolean
  coreThought: String?
  emotionInitial: Int (1-10)
  emotionFinal: Int? (1-10)
  cognitiveDistortions: List<CognitiveDistortionType>
  messages: List<Message>
  mindmapData: MindmapData
}
```

#### Message
```
Message {
  id: UUID
  sessionId: UUID
  timestamp: DateTime
  role: MessageRole (User | AI)
  content: String
  audioUrl: String? (if voice input was used)
  metadata: Map<String, Any> (for AI annotations)
}
```

#### MindmapData
```
MindmapData {
  sessionId: UUID
  rootNode: MindmapNode
  nodes: List<MindmapNode>
  edges: List<MindmapEdge>
  generatedAt: DateTime
}
```

#### MindmapNode
```
MindmapNode {
  id: String
  type: NodeType (CoreThought | CognitiveDistortion | Evidence | Alternative | Emotion)
  label: String
  content: String
  relatedMessageIds: List<UUID>
  position: Position (x, y)
  color: String (hex)
}
```

#### TrackedThought
```
TrackedThought {
  id: UUID
  sessionId: UUID
  thoughtText: String
  initialIntensity: Int (1-10)
  checkIns: List<CheckIn>
  createdAt: DateTime
  status: ThoughtStatus (Active | Resolved | Archived)
}
```

#### CheckIn
```
CheckIn {
  id: UUID
  thoughtId: UUID
  intensity: Int (1-10)
  note: String?
  timestamp: DateTime
}
```

### 5.2 Enums

```
MessageRole: User, AI

NodeType: CoreThought, CognitiveDistortion, Evidence, Alternative, Emotion

CognitiveDistortionType:
  - AllOrNothing
  - Overgeneralization
  - MentalFiltering
  - Catastrophizing
  - Personalization
  - ShouldStatements
  - Labeling
  - EmotionalReasoning
  - MindReading
  - FortuneTelling

ThoughtStatus: Active, Resolved, Archived
```

---

## 6. AI Integration Specifications

### 6.1 AI Provider
- Primary: Gemini 2.5 Flash
- Fallback: Gemini 1.5 Pro
- Requirements: Conversation context maintenance, structured output support

### 6.2 Prompt Engineering

#### System Prompt
```
You are a compassionate CBT journaling guide. Your role is to help users explore their thoughts using the CBT framework.

STRUCTURE YOUR CONVERSATION:
1. Situation: Ask what happened or what's bothering them
2. Thoughts: Identify automatic negative thoughts
3. Emotions: Explore how they felt
4. Evidence: Examine evidence for/against the thought
5. Distortions: Gently identify cognitive distortions
6. Alternatives: Help generate balanced perspectives

TONE & STYLE:
- Empathetic and non-judgmental
- Ask one question at a time
- Use open-ended questions
- Validate feelings before challenging thoughts
- Be conversational, not clinical
- Never diagnose or prescribe

COGNITIVE DISTORTIONS TO IDENTIFY:
[List of distortions with brief examples]

When you identify a distortion, say something like:
"I notice this might be an example of [distortion type]. Let's explore that together."

IMPORTANT: You are a journaling tool, not a therapist. If user mentions crisis/self-harm, provide crisis hotline information immediately.
```

#### Few-Shot Examples
Provide 2-3 example conversations demonstrating ideal flow

### 6.3 Response Requirements
- Max tokens: 150 per response (keep responses concise)
- Temperature: 0.7 (balance between consistency and naturalness)
- Stop sequences: None (let AI complete thoughts)
- Streaming: Yes (display response as it generates for better UX)

### 6.4 Structured Outputs

**Session Summary Generation:**
```
At session end, generate JSON:
{
  "coreThought": "string",
  "cognitiveDistortions": ["type1", "type2"],
  "emotionShift": {
    "before": "anxious, overwhelmed",
    "after": "calmer, more hopeful"
  },
  "keyInsights": ["insight1", "insight2"],
  "alternativePerspectives": ["perspective1", "perspective2"]
}
```

**Mindmap Data Generation:**
```
From session conversation, generate JSON:
{
  "rootNode": {
    "type": "CoreThought",
    "label": "Brief version",
    "content": "Full thought text"
  },
  "branches": [
    {
      "type": "CognitiveDistortion",
      "label": "All-or-nothing thinking",
      "content": "Explanation",
      "relatedMessages": [message_ids]
    },
    // ... more branches
  ]
}
```

---

## 7. Technical Architecture

### 7.1 Architecture Pattern
- **MVVM** (Model-View-ViewModel)
- **Clean Architecture** layers:
  - Presentation (UI + ViewModels)
  - Domain (Use Cases + Entities)
  - Data (Repositories + Data Sources)

### 7.2 Module Structure

```
composeApp/
├── commonMain/
│   ├── presentation/
│   │   ├── screens/
│   │   │   ├── home/
│   │   │   ├── conversation/
│   │   │   ├── mindmap/
│   │   │   ├── progress/
│   │   │   └── settings/
│   │   └── components/ (shared UI components)
│   ├── domain/
│   │   ├── models/
│   │   ├── usecases/
│   │   └── repositories/ (interfaces)
│   └── data/
│       ├── repositories/ (implementations)
│       ├── local/ (database)
│       ├── remote/ (AI API)
│       └── mappers/
├── androidMain/
│   └── platform/
│       ├── VoiceInputImpl.kt
│       └── VoiceOutputImpl.kt
└── iosMain/
    └── platform/
        ├── VoiceInputImpl.kt
        └── VoiceOutputImpl.kt

shared/ (if needed for additional shared logic)
```

### 7.3 Key Dependencies

**Core:**
- Kotlin 2.0+
- Compose Multiplatform 1.7.0+
- Coroutines + Flow

**UI:**
- Compose Material 3
- Navigation: Voyager or Decompose
- Charts: KMP-Charts or custom Canvas implementations

**Data:**
- Local: Room or SQLDelight
- Serialization: kotlinx.serialization
- Networking: Ktor Client

**DI:**
- Koin (KMP-compatible)

**AI:**
- OpenAI Kotlin SDK or custom Ktor implementation
- Streaming support for real-time responses

**Platform-Specific:**
- Android: androidx.core (speech), android.speech.tts
- iOS: Speech framework, AVFoundation

**Utilities:**
- Logging: Napier or Kermit
- Date/Time: kotlinx-datetime
- UUID: kotlin-uuid

### 7.4 Data Persistence Strategy

**Local Database:**
- Use Room with KMP support
- Tables: Sessions, Messages, TrackedThoughts, CheckIns, MindmapNodes
- Relationships defined with foreign keys
- Indexes on sessionId, timestamp for query performance

**Shared Preferences:**
- User settings (voice preferences, notification settings)
- Onboarding completion flag
- Last session timestamp

**File Storage:**
- Audio recordings (if saving voice inputs)
- Exported mindmap images
- Platform-specific directories: iOS Documents, Android Internal Storage

---

## 8. Design System

### 8.1 Color Palette

**Primary Colors:**
- Primary: #6B4423 (Deep Brown) - Main brand color, CTAs, user messages
- Primary Variant: #543316 (Darker brown for pressed states)
- Secondary: #8B5A3C (Medium Brown) - Accents, secondary actions
- Secondary Variant: #A67C52 (Light Brown) - Hover states, tertiary actions
- Tertiary: #C19A6B (Tan) - Subtle accents, bridges to cream

**Background Colors:**
- Background Light: #FFFCB8 (Light Cream) - Main background in light mode
- Background: #E9E294 (Cream) - Surface color, cards in light mode
- Surface: #FEFEF5 (Off-White) - Elevated surfaces, modals
- Surface Variant: #F5F0E8 (Very light tan) - Alternative surfaces

**Semantic Colors:**
- Error/Distortion: #D84315 (Warm Red-Brown) - Cognitive distortions, errors
- Success/Alternative: #6B8E23 (Olive Green) - Alternative perspectives, success
- Warning/Evidence For: #E8A13C (Golden Amber) - Supporting evidence, warnings
- Info: #8B5A3C (Medium Brown) - Info messages, secondary highlights

**Text Colors:**
- On Primary: #FFFEF8 (Warm White text on brown backgrounds)
- On Background: #2B1810 (Dark Brown text on cream backgrounds)
- On Surface: #3D2817 (Brown-black on white surfaces)
- Secondary Text: #6B5447 (Muted brown for secondary text)
- Disabled: #B8ADA3 (Light brown-gray)

**Dark Mode (Optional for Phase 2):**
- Background: #1F1410
- Surface: #2D2015
- Primary: #A67C52 (Lighter brown for dark mode)
- On Background: #F5F0E8

### 8.2 Typography

**Font Family:** System defaults (SF Pro on iOS, Roboto on Android)

**Scale:**
- H1: 32sp, Bold (Screen titles)
- H2: 24sp, SemiBold (Section headers)
- H3: 20sp, SemiBold (Card titles)
- Body1: 16sp, Regular (Main content)
- Body2: 14sp, Regular (Secondary text)
- Caption: 12sp, Regular (Timestamps, labels)
- Button: 16sp, Medium (CTAs)

### 8.3 Spacing System
- xs: 4dp
- sm: 8dp
- md: 16dp
- lg: 24dp
- xl: 32dp
- xxl: 48dp

### 8.4 Components

**Buttons:**
- Primary: Filled, deep brown (#6B4423), warm white text (#FFFEF8), rounded corners (8dp)
- Secondary: Outlined, 1.5dp border (medium brown #8B5A3C), brown text
- Text: No background, deep brown (#6B4423) text only
- Icon: Square touch target (48dp), 24dp icon
- Pressed state: Darker brown (#543316) for primary, light brown fill (#A67C52) for secondary
- Hover state (desktop): Subtle tan (#C19A6B) overlay at 10% opacity

**Cards:**
- Elevation: 2dp with warm shadow (brown-tinted, not gray)
- Corner radius: 12dp
- Padding: 16dp
- Background: Cream (#E9E294) on light cream background (#FFFCB8)
- Border: None (use shadow for depth)
- Accent border option: 2dp left border in deep brown (#6B4423) for emphasis
- Hover state: Subtle lift to 4dp elevation

**Input Fields:**
- Outlined style with organic feel
- Corner radius: 8dp
- Background: Off-white (#FEFEF5) or light cream (#FFFCB8)
- Default border: Light brown (#A67C52) at 1dp
- Focus state: Deep brown (#6B4423) border (2dp)
- Error state: Warm red-brown (#D84315) border
- Label color: Secondary text (#6B5447), deep brown when focused
- Cursor: Deep brown (#6B4423)

**Messages (Chat):**
- AI: Left-aligned, cream (#E9E294) background, dark brown text (#2B1810), rounded corners (12dp, square on left)
- User: Right-aligned, deep brown (#6B4423), warm white text (#FFFEF8), rounded corners (12dp, square on right)
- Max width: 80% of screen
- Padding: 12dp vertical, 16dp horizontal
- Subtle shadow for depth

---

## 9. Accessibility Requirements

### 9.1 Screen Reader Support
- All interactive elements must have content descriptions
- Mindmap nodes announce content when focused
- Chat messages announce speaker and content
- Progress bars announce percentage values

### 9.2 Visual Accessibility
- Minimum contrast ratio: 4.5:1 for normal text, 3:1 for large text
- Text size must respect system font size settings
- Interactive elements minimum 44x44dp touch targets
- Focus indicators on all interactive elements

### 9.3 Multimodal Accessibility
- Voice input provides alternative to typing (helps motor impairments)
- Voice output provides alternative to reading (helps visual impairments)
- All features must work without voice (optional, not required)

---

## 10. Privacy & Security

### 10.1 Data Privacy
- All data stored locally by default
- No data sent to servers except AI API calls
- AI conversations are not stored by provider (use API settings to ensure this)
- Clear privacy policy in app

### 10.2 Data Export
- User can export all data as JSON
- Export includes sessions, messages, tracked thoughts
- Export excludes audio files (too large, user can access via file manager)

### 10.3 Data Deletion
- User can delete individual sessions
- User can clear all data (factory reset option)
- Deleted data is permanently removed (no soft deletes)

### 10.4 Disclaimers (Required)
**On Onboarding Screen:**
> "MindFlow is a self-reflection tool using CBT principles. It is NOT a replacement for professional mental health treatment. If you're in crisis, please contact [Crisis Hotline] immediately."

**In Settings:**
- Link to full disclaimers
- Link to crisis resources (988 Suicide & Crisis Lifeline, Crisis Text Line, etc.)
- "When to Seek Professional Help" information

---

## 11. Error Handling

### 11.1 Network Errors
**Scenario:** AI API call fails
- **User sees:** "I'm having trouble connecting. Your message is saved - tap to retry."
- **Behavior:** Message queued locally, retry button available
- **Recovery:** Retry on user action or auto-retry when connection restored

### 11.2 Permission Errors
**Scenario:** Microphone permission denied
- **User sees:** "Microphone access needed for voice input. Enable in Settings?"
- **Behavior:** Show system settings deep link
- **Recovery:** Re-check permission on next voice button tap

### 11.3 AI Generation Errors
**Scenario:** AI generates inappropriate or unhelpful response
- **User sees:** AI message with "Report" button
- **Behavior:** Log issue, regenerate response with modified prompt
- **Recovery:** User can tap "Regenerate" to get new response

### 11.4 Data Corruption
**Scenario:** Session data becomes corrupted
- **User sees:** "This session couldn't be loaded. It may be corrupted."
- **Behavior:** Mark session as corrupted, exclude from lists
- **Recovery:** User can attempt export as raw JSON or delete

---

## 12. Testing Requirements

### 12.1 Functional Testing

**Must Test:**
- Complete CBT conversation flow (10+ exchanges)
- Mindmap generation from various session types
- Progress tracking with multiple check-ins
- Voice input/output on both platforms
- Session pause/resume
- Data persistence across app restarts

**Edge Cases:**
- Very long user responses (500+ words)
- Rapid-fire messages (user sends 5 messages in 10 seconds)
- Session with 50 exchanges (max limit)
- Multiple sessions on same day
- Check-in after 30+ days

### 12.2 Performance Testing
- App launch time <2 seconds
- Conversation screen renders in <500ms
- Mindmap generation <2 seconds for 20-node map
- Smooth scrolling (60fps) with 50+ messages
- Database query time <100ms for session list

### 12.3 Platform Testing
- Test on iOS 15, 16, 17
- Test on Android 8, 10, 12, 14
- Various screen sizes (small phones to tablets)
- Dark mode and light mode
- Different system font sizes

---

## 13. Success Criteria for MVP

### 13.1 Must Have (P0)
- [ ] Complete CBT conversation flow working
- [ ] Mindmap visualization renders and is interactive
- [ ] Session history with basic list view
- [ ] Progress tracking with at least one thought check-in
- [ ] Voice input functional on iOS and Android
- [ ] Voice output functional on iOS and Android
- [ ] Data persists locally
- [ ] Onboarding with disclaimers
- [ ] Crisis resources accessible

### 13.2 Should Have (P1)
- [ ] Progress analytics screen with graphs
- [ ] Multiple session mindmaps
- [ ] Polished animations and transitions
- [ ] Dark mode support
- [ ] Export functionality (sessions to JSON)
- [ ] Notification for check-ins

### 13.3 Nice to Have (P2)
- [ ] Multiple voice options
- [ ] Session search functionality
- [ ] Tags/categories for sessions
- [ ] Guided CBT tutorials
- [ ] Comparison view for multiple mindmaps

---

## 14. Open Questions

**For Design:**
1. Exact mindmap layout algorithm - radial, tree, force-directed?
2. Animation style for mindmap appearance - fade-in, grow-in, or slide-in?
3. Should AI messages have avatars or just colored bubbles?

**For Development:**
1. Which AI provider offers best balance of quality and latency?
2. Room vs SQLDelight for KMP data persistence?
3. Custom mindmap rendering vs using a library (if any exist for KMP)?

**For Product:**
1. Default notification frequency for check-ins - user-configurable?
2. Maximum number of tracked thoughts to show on home screen?
3. Should we implement "favorites" for sessions user wants to revisit?

---

## 15. Future Roadmap (Post-MVP)

**Phase 2: Enhanced Insights**
- Pattern recognition across multiple sessions
- AI-generated weekly summaries
- Comparison of similar situations over time
- "Thought journal" showing evolution of specific beliefs

**Phase 3: Social Features**
- Anonymous community (share mindmaps/insights)
- Therapist collaboration mode (export for sessions)
- Shared progress with accountability partners

**Phase 4: Expanded Frameworks**
- DBT (Dialectical Behavior Therapy) modules
- ACT (Acceptance and Commitment Therapy) exercises
- Guided meditations integrated into sessions
- Crisis management tools (grounding exercises)

**Phase 5: Platform Expansion**
- Desktop app (macOS, Windows)
- Web version
- Apple Watch companion (quick check-ins)
- Widgets for iOS/Android home screens

---

## 16. Appendix

### 16.1 CBT Framework Quick Reference

**6-Step Process:**
1. **Situation**: What happened? (context)
2. **Automatic Thought**: What went through your mind?
3. **Emotion**: How did you feel? (name + intensity)
4. **Evidence For**: What makes you believe this thought?
5. **Evidence Against**: What challenges this thought?
6. **Alternative**: What's a more balanced perspective?

**Sample Questions:**
- "Can you tell me more about what happened?"
- "What thoughts were going through your mind?"
- "How did that make you feel?"
- "What evidence supports this thought?"
- "Is there any evidence that contradicts it?"
- "How might someone else view this situation?"

### 16.2 Cognitive Distortions Reference

1. **All-or-Nothing**: Seeing things in black/white
2. **Overgeneralization**: One event = always happens
3. **Mental Filter**: Focus only on negatives
4. **Catastrophizing**: Assuming worst-case scenario
5. **Personalization**: Taking things personally
6. **Should Statements**: Rigid rules about how things "should" be
7. **Labeling**: Attaching negative labels to self
8. **Emotional Reasoning**: "I feel it, so it must be true"
9. **Mind Reading**: Assuming you know what others think
10. **Fortune Telling**: Predicting negative outcomes

### 16.3 Glossary

- **CBT**: Cognitive Behavioral Therapy - evidence-based therapy focusing on thought patterns
- **Cognitive Distortion**: Irrational thought pattern that influences emotions
- **Thought Record**: CBT tool for examining thoughts systematically
- **Check-in**: Follow-up assessment of thought intensity over time
- **Core Thought**: Primary negative belief identified in a session
- **Mindmap**: Visual representation of thought structure and relationships
- **Session**: Single journaling conversation from start to finish

---

**Document Control**

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | Dec 2025 | Product Team | Initial PRD |

**Approvals**

- [ ] Product Owner
- [ ] Lead Designer
- [ ] Lead Developer
- [ ] QA Lead

---

*This PRD is a living document and will be updated as requirements evolve during development.*
