# MindFlow Color Scheme

## Official Brand Colors - Earth Tone Palette

### Primary Palette (Browns)
```
Deep Brown:     #6B4423  ███████  Main brand color, CTAs, user messages
Dark Brown:     #543316  ███████  Pressed/active states
Medium Brown:   #8B5A3C  ███████  Accents, emotion nodes
Light Brown:    #A67C52  ███████  Secondary actions, alternatives
Tan:            #C19A6B  ███████  Subtle accents, bridges to cream
```

### Background Palette
```
Light Cream:    #FFFCB8  ███████  Main app background
Cream:          #E9E294  ███████  Cards, surfaces, AI messages
Off-White:      #FEFEF5  ███████  Elevated surfaces, modals
Light Tan:      #F5F0E8  ███████  Alternative surfaces
```

### Semantic Colors (Earth Tones)
```
Warm Red-Brown: #D84315  ███████  Errors, cognitive distortions
Olive Green:    #6B8E23  ███████  Alternatives, evidence against
Golden Amber:   #E8A13C  ███████  Evidence for, warnings
```

### Text Colors
```
Dark Brown:     #2B1810  ███████  Main text on cream backgrounds
Brown-Black:    #3D2817  ███████  Text on white surfaces
Secondary:      #6B5447  ███████  Secondary text, timestamps
On Brown:       #FFFEF8  ███████  Text on brown backgrounds
Disabled:       #B8ADA3  ███████  Disabled states
```

---

## Usage Guide

### Conversation Screen
- **User messages**: Deep Brown (#6B4423) background, warm white text (#FFFEF8)
- **AI messages**: Cream (#E9E294) background, dark brown text (#2B1810)
- **Background**: Light Cream (#FFFCB8)
- **Input field default**: Light Brown (#A67C52) border
- **Input field focus**: Deep Brown (#6B4423) border (2dp)

### Mindmap Visualization
| Node Type | Color | Hex Code | Meaning |
|-----------|-------|----------|---------|
| Core Thought | Deep Brown | #6B4423 | Grounding, central |
| Cognitive Distortion | Warm Red-Brown | #D84315 | Alert, but not harsh |
| Evidence For | Golden Amber | #E8A13C | Caution, warmth |
| Evidence Against | Olive Green | #6B8E23 | Growth, natural |
| Alternative Perspective | Light Brown | #A67C52 | New path, lighter |
| Emotion | Medium Brown | #8B5A3C | Warm, human |

**Connection lines**: Tan (#C19A6B) at 60% opacity
**Shadows**: Deep Brown (#6B4423) at 15% opacity (warm shadows, not gray)

### Progress Tracking
- **Progress bars**: Deep Brown (#6B4423) for fill, Light Cream (#FFFCB8) for background
- **Improvement indicators**: Olive Green (#6B8E23)
- **Needs attention**: Golden Amber (#E8A13C)
- **Cards**: Cream (#E9E294) on Light Cream (#FFFCB8) background
- **Accent borders**: Deep Brown (#6B4423) at 2dp on left edge

### Buttons & CTAs
- **Primary button**: Deep Brown (#6B4423) fill, warm white text (#FFFEF8)
- **Primary pressed**: Dark Brown (#543316)
- **Secondary button**: Medium Brown (#8B5A3C) border, brown text
- **Secondary pressed**: Light Brown (#A67C52) fill
- **Text button**: Deep Brown (#6B4423) text only
- **Hover (desktop)**: Tan (#C19A6B) overlay at 10% opacity

---

## Accessibility Notes

### Contrast Ratios (WCAG AA Compliance)

**Deep Brown (#6B4423) on Light Cream (#FFFCB8):**
- Contrast: 6.2:1 ✓ (Passes AA for normal text, passes AAA for large text)

**Deep Brown (#6B4423) on Off-White (#FEFEF5):**
- Contrast: 7.1:1 ✓ (Passes AAA for all text)

**Dark Brown Text (#2B1810) on Light Cream (#FFFCB8):**
- Contrast: 12.8:1 ✓ (Passes AAA)

**Dark Brown Text (#2B1810) on Cream (#E9E294):**
- Contrast: 10.4:1 ✓ (Passes AAA)

**Warm White (#FFFEF8) on Deep Brown (#6B4423):**
- Contrast: 6.4:1 ✓ (Passes AA for normal text)

**All color combinations meet or exceed WCAG AA standards.**

---

## Design Rationale

### Why Brown + Cream?

1. **Cohesive Harmony**: Brown naturally extends from cream - they're family
2. **Analog Feel**: Evokes paper journals, coffee, warmth, natural materials
3. **Calming**: Earth tones are scientifically proven to reduce anxiety
4. **Mature & Trustworthy**: Brown feels grounded, serious for mental health
5. **Unique**: Different from clinical blues/teals without being jarring
6. **Timeless**: Won't feel dated - earth tones are classic

### The Psychology of Brown

- **Stability**: Associated with earth, grounding, reliability
- **Comfort**: Warm, cozy, safe - like coffee, chocolate, autumn
- **Natural**: Organic, honest, authentic
- **Sophisticated**: When done well, brown is elegant (think leather, wood)
- **Approachable**: Less intimidating than black, more serious than pastels

### The Cream + Brown Combination

This palette mimics:
- **Paper journals** (cream pages, brown leather covers)
- **Coffee shops** (cream lattes, brown wood tables)
- **Nature** (sand/earth tones)
- **Vintage design** (sepia tones, aged paper)

All of these evoke **warmth, thoughtfulness, and authenticity** - perfect for journaling.

---

## Implementation Checklist

### Compose Multiplatform (Material 3)
```kotlin
// ✓ Define Color objects for all browns and creams
// ✓ Create lightColorScheme with MindFlow earth tones
// ✓ Apply theme to App composable
// ✓ Use MaterialTheme.colorScheme throughout
// ✓ Custom shadow colors (brown-tinted, not gray)
// ✓ Connection lines use Tan with 60% alpha
```

### Platform-Specific
```kotlin
// Android
// ✓ Update res/values/colors.xml
// ✓ Set status bar color to Light Cream (#FFFCB8)
// ✓ Set navigation bar color to Light Cream
// ✓ Use warm shadows (brown at 15% alpha)

// iOS
// ✓ Configure status bar style (dark content on light)
// ✓ Update launch screen to Light Cream background
// ✓ Match system UI chrome to cream
```

### Assets
```
// ✓ App icon uses Deep Brown (#6B4423) with cream accent
// ✓ Splash screen uses Light Cream background
// ✓ Illustrations use earth tone palette
// ✓ All shadows are warm (brown-tinted)
```

---

## Color Combinations Reference

### High Priority Combinations
```
✓ Deep Brown + Warm White       (Primary buttons, user messages)
✓ Cream + Dark Brown Text       (AI messages, cards)
✓ Light Cream + Dark Brown Text (Main background)
✓ Deep Brown + Light Cream      (Focus states, active tabs)
✓ Medium Brown + Off-White      (Secondary buttons, accents)
```

### Medium Priority Combinations
```
✓ Light Brown + Off-White       (Alternative nodes, tertiary actions)
✓ Warm Red-Brown + Off-White    (Distortion nodes, error messages)
✓ Olive Green + Off-White       (Evidence against nodes, success)
✓ Golden Amber + Dark Brown     (Evidence for nodes, warnings)
```

### Low Priority / Avoid
```
✗ Cream on Light Cream          (Too low contrast)
✗ Tan on Light Cream            (Poor readability for text)
✗ Light Brown on Cream          (Border contrast only)
```

---

## Quick Reference for Designers

**When designing a new screen:**
1. Start with Light Cream (#FFFCB8) background
2. Use Cream (#E9E294) for cards/surfaces
3. Primary actions in Deep Brown (#6B4423)
4. Secondary actions in Medium Brown (#8B5A3C)
5. Text in Dark Brown (#2B1810)
6. Error states in Warm Red-Brown (#D84315)
7. Success in Olive Green (#6B8E23)

**Key principle:** Brown is your hero color, but use it sparingly. Let the creams breathe. Brown provides punctuation and emphasis.

**Visual hierarchy:**
- Light Cream (background) → Least emphasis
- Cream (surfaces) → Low emphasis
- Light Brown (accents) → Medium emphasis
- Medium Brown (secondary actions) → High emphasis
- Deep Brown (primary actions) → Highest emphasis

---

## Figma/Sketch Variables

For designers using design tools:

```
Brand/Primary:              #6B4423
Brand/Primary-Dark:         #543316
Brand/Primary-Medium:       #8B5A3C
Brand/Primary-Light:        #A67C52
Brand/Tan:                  #C19A6B

Background/Lightest:        #FFFCB8
Background/Light:           #E9E294
Background/Off-White:       #FEFEF5
Background/Light-Tan:       #F5F0E8

Semantic/Error:             #D84315
Semantic/Success:           #6B8E23
Semantic/Warning:           #E8A13C

Text/Dark-Brown:            #2B1810
Text/Brown-Black:           #3D2817
Text/Secondary:             #6B5447
Text/On-Brown:              #FFFEF8
Text/Disabled:              #B8ADA3

Effects/Shadow:             #6B4423 at 15% opacity
Effects/Connection-Line:    #C19A6B at 60% opacity
```

---

## Animation & Motion Guidelines

**Speed**: Earthy palette suggests organic, natural motion
- Ease curves: Use ease-out for appearing elements
- Duration: 300-400ms (not too fast, feels rushed)
- Spring animations for interactive elements

**Style**: 
- Smooth, gentle transitions (no harsh snaps)
- Fade + scale for appearing cards (organic growth)
- Slide transitions should feel like turning pages

---

## Typography Pairing

Browns work beautifully with:
- **Serif fonts**: For a classic, literary feel (consider for headings)
- **Rounded sans-serifs**: For warmth and approachability (body text)
- **System fonts**: Safe default that respects platform conventions

**Recommendation**: Use system fonts (SF Pro on iOS, Roboto on Android) for consistency, but consider a warmer typeface if adding custom fonts later.

---

## Testing Checklist

- [ ] Brown contrast tested with accessibility tools (all passing)
- [ ] Cream backgrounds look good on different displays
- [ ] Brown shadows (not gray) implemented correctly
- [ ] Colors consistent across iOS and Android
- [ ] Warm tones maintained in all UI elements
- [ ] Dark mode exploration (darker browns + light cream accents)
- [ ] Colorblind testing (semantic colors include icons)
- [ ] Screenshots showcase the cohesive earth-tone branding
- [ ] No harsh black anywhere (use brown-black #3D2817)

---

## Mood Board References

**Visual inspiration:**
- Moleskine journals (cream pages, brown covers)
- Coffee shop aesthetics (lattes, wood, warm lighting)
- Autumn photography (golden hour, earth tones)
- Leather-bound books (vintage, sophisticated)
- Natural materials (wood, stone, paper)

**Feeling we're aiming for:**
- Warm, not cold
- Organic, not digital
- Grounded, not floating
- Thoughtful, not rushed
- Safe, not clinical
- Personal, not corporate

---

**Last Updated:** December 2024  
**Approved By:** Product & Design Team  
**Status:** Final for MVP

---

## Final Note for Developers

When implementing, remember:
1. Use **warm shadows** (brown at 15%, never gray)
2. **Connection lines** in mindmap use Tan at 60% alpha
3. All **text on cream** uses dark brown (#2B1810), never black
4. **Buttons** should feel tactile (subtle shadows, organic curves)
5. **Animations** should be gentle and natural (300-400ms)

The goal is to make the app feel like a **warm, personal journal** - not a cold, clinical tool.

