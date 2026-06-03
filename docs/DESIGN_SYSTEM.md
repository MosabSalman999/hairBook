# DESIGN_SYSTEM.md — Hair Book · Design System

## Visual Direction

**Modern salon · Premium luxury**
Black backgrounds, warm gold accents, clean white type, generous whitespace. The aesthetic should feel like a high-end barbershop or salon lookbook — confident, editorial, and tactile. Every screen should feel like it belongs on a glossy page.

---

## Colour Tokens

Define all colours in `core/ui/theme/Color.kt`. Use ONLY these tokens throughout the app — never hardcode hex values in composables.

```kotlin
// Backgrounds
val Black = Color(0xFF0A0A0A)          // Primary background
val SurfaceDark = Color(0xFF141414)    // Cards, sheets, dialogs
val SurfaceElevated = Color(0xFF1E1E1E) // Input fields, elevated surfaces

// Gold accent family
val Gold = Color(0xFFD4A843)           // Primary accent — CTAs, active states, icons
val GoldLight = Color(0xFFE8C97A)      // Hover / pressed states, highlights
val GoldMuted = Color(0xFF8A6B28)      // Disabled gold, subtle borders

// Text
val White = Color(0xFFFFFFFF)          // Primary text
val GreyLight = Color(0xFFB0B0B0)      // Secondary text, captions, placeholders
val GreyMuted = Color(0xFF606060)      // Tertiary text, dividers

// Semantic
val Success = Color(0xFF4CAF50)        // Confirmation states
val Error = Color(0xFFE53935)          // Errors, destructive actions
val Overlay = Color(0x99000000)        // Image overlays, modal scrim

// Transparent
val GoldAlpha12 = Color(0x1FD4A843)    // Gold tint on dark surfaces (12% opacity)
val GoldAlpha24 = Color(0x3DD4A843)    // Gold tint — selected chip background
```

### MaterialTheme colour scheme mapping

```kotlin
// In Theme.kt
private val HairBookColorScheme = darkColorScheme(
    primary         = Gold,
    onPrimary       = Black,
    primaryContainer    = GoldAlpha24,
    onPrimaryContainer  = GoldLight,
    background      = Black,
    onBackground    = White,
    surface         = SurfaceDark,
    onSurface       = White,
    surfaceVariant  = SurfaceElevated,
    onSurfaceVariant = GreyLight,
    error           = Error,
    onError         = White,
    outline         = GoldMuted,
    outlineVariant  = GreyMuted,
)
```

Hair Book is **dark-mode only** in v1 — no light theme is required.

---

## Typography

Define in `core/ui/theme/Type.kt`. Font: **DM Serif Display** (headings) + **DM Sans** (body). Both are available on Google Fonts and loaded via `res/font/`.

```kotlin
val HairBookTypography = Typography(
    // Display — hero text, style names on detail screen
    displayLarge  = TextStyle(fontFamily = DmSerifDisplay, fontSize = 48.sp, fontWeight = FontWeight.Normal, lineHeight = 56.sp, letterSpacing = (-0.5).sp),
    displayMedium = TextStyle(fontFamily = DmSerifDisplay, fontSize = 36.sp, fontWeight = FontWeight.Normal, lineHeight = 44.sp),

    // Headlines — section titles, screen titles
    headlineLarge  = TextStyle(fontFamily = DmSerifDisplay, fontSize = 28.sp, fontWeight = FontWeight.Normal, lineHeight = 36.sp),
    headlineMedium = TextStyle(fontFamily = DmSerifDisplay, fontSize = 22.sp, fontWeight = FontWeight.Normal, lineHeight = 30.sp),
    headlineSmall  = TextStyle(fontFamily = DmSans, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, lineHeight = 26.sp),

    // Titles — card names, list items
    titleLarge  = TextStyle(fontFamily = DmSans, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = 24.sp, letterSpacing = 0.1.sp),
    titleMedium = TextStyle(fontFamily = DmSans, fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 20.sp, letterSpacing = 0.1.sp),

    // Body — descriptions, paragraphs
    bodyLarge  = TextStyle(fontFamily = DmSans, fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 26.sp),
    bodyMedium = TextStyle(fontFamily = DmSans, fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 22.sp),
    bodySmall  = TextStyle(fontFamily = DmSans, fontSize = 12.sp, fontWeight = FontWeight.Normal, lineHeight = 18.sp),

    // Labels — chips, tags, captions
    labelLarge  = TextStyle(fontFamily = DmSans, fontSize = 13.sp, fontWeight = FontWeight.Medium, lineHeight = 18.sp, letterSpacing = 0.5.sp),
    labelMedium = TextStyle(fontFamily = DmSans, fontSize = 11.sp, fontWeight = FontWeight.Medium, lineHeight = 16.sp, letterSpacing = 0.5.sp),
    labelSmall  = TextStyle(fontFamily = DmSans, fontSize = 10.sp, fontWeight = FontWeight.Normal, lineHeight = 14.sp, letterSpacing = 0.8.sp),
)
```

---

## Spacing & Layout

Use a **4dp base grid** everywhere. Define constants in `core/ui/theme/Spacing.kt`:

```kotlin
object Spacing {
    val xs  = 4.dp
    val sm  = 8.dp
    val md  = 16.dp
    val lg  = 24.dp
    val xl  = 32.dp
    val xxl = 48.dp
    val xxxl = 64.dp

    val screenPaddingHorizontal = 16.dp
    val screenPaddingVertical   = 24.dp
    val cardPadding             = 12.dp
    val sectionGap              = 32.dp
    val itemGap                 = 12.dp
}
```

---

## Shape Tokens

Define in `core/ui/theme/Shape.kt`:

```kotlin
val HairBookShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),   // chips, tags
    small      = RoundedCornerShape(8.dp),   // input fields, small cards
    medium     = RoundedCornerShape(12.dp),  // catalogue cards
    large      = RoundedCornerShape(16.dp),  // bottom sheets, dialogs
    extraLarge = RoundedCornerShape(24.dp),  // full-screen cards, FABs
)
```

---

## Component Specifications

### HairstyleCard (used in Browse + Favourites)
- Background: `SurfaceDark`
- Corner radius: `medium` (12dp)
- Image: fills the card top, aspect ratio varies (masonry layout)
- Gradient overlay on image bottom: `Black` 0% → 70% (so text is legible)
- Style name: `titleMedium`, `White`, pinned to image bottom-left inside gradient
- Tag pill: `labelSmall`, `Gold` text on `GoldAlpha12` background, top-right of image
- Minimum card width: 150dp (grid uses two columns with staggered heights)

### AttributeChip
- Background: `GoldAlpha24` (selected) / `SurfaceElevated` (unselected)
- Border: 1dp `GoldMuted` (unselected) / none (selected)
- Text: `labelLarge`, `Gold` (selected) / `GreyLight` (unselected)
- Corner radius: `extraSmall` (4dp)
- Height: 32dp · Horizontal padding: 12dp

### FilterChipRow
- Horizontally scrollable row of `AttributeChip` components
- "All" chip always first and selected by default
- No visible scrollbar

### PrimaryButton (CTA — e.g. "See results", "Sign in")
- Background: `Gold`
- Text: `titleMedium`, `Black`
- Corner radius: `extraLarge` (fully rounded pill)
- Height: 52dp · Full width of container
- Pressed state: background `GoldLight`
- Disabled state: background `GoldMuted`, text `GreyMuted`

### SecondaryButton (e.g. "Continue as guest")
- Background: transparent
- Border: 1dp `Gold`
- Text: `titleMedium`, `Gold`
- Same size and radius as PrimaryButton
- Pressed state: background `GoldAlpha12`

### GoldDivider
- Height: 1dp · Colour: `GoldMuted` · Used between sections on Detail screen

### HairBookTopBar
- Background: `Black`
- Title: `headlineSmall`, `White`, centred
- Navigation icon (back arrow): `Gold`
- No elevation / shadow — flat

### BottomNavigationBar
- Background: `SurfaceDark`
- Top border: 1dp `GoldMuted`
- Selected icon + label: `Gold`
- Unselected icon + label: `GreyMuted`
- Icon size: 24dp
- Label: `labelMedium`
- Items: Browse · Finder · Favourites · Profile

### FAB (Favourite button on Detail screen)
- Shape: circle
- Background: `Gold`
- Icon: heart outline (unfavourited) / heart filled (favourited), `Black`
- Size: 56dp standard FAB
- Positioned: bottom-end, 16dp from edges

### Input Field (Auth screens)
- Background: `SurfaceElevated`
- Border: 1dp `GreyMuted` (default) / 1dp `Gold` (focused) / 1dp `Error` (error)
- Text: `bodyLarge`, `White`
- Placeholder: `bodyLarge`, `GreyLight`
- Corner radius: `small` (8dp)
- Height: 56dp

### Finder Step Card (questionnaire answer option)
- Background: `SurfaceDark`
- Border: 1dp `GreyMuted` (default) / 2dp `Gold` (selected)
- Corner radius: `medium` (12dp)
- Image or illustration fills the top 60% of the card
- Label: `titleMedium`, `White`, bottom-aligned with 12dp padding
- Selected state: `GoldAlpha12` overlay on top of everything + gold border
- Cards arranged in 2-column grid

---

## Iconography

Use **Material Symbols Rounded** via `androidx.compose.material:material-icons-extended`. All icons in `GreyLight` by default, `Gold` when active/selected.

Key icons used:
- Bottom nav: `AutoAwesomeMosaic` (Browse), `AutoFixHigh` (Finder), `Favorite` (Favourites), `Person` (Profile)
- Detail FAB: `Favorite` / `FavoriteBorder`
- Back navigation: `ArrowBackIosNew`
- Admin: `AdminPanelSettings`
- Search: `Search`
- Filter: `Tune`

---

## Motion & Animation

- Screen transitions: fade through (300ms) using Navigation Compose `AnimatedNavHost`
- Finder step transitions: slide left on advance, slide right on back (250ms, ease-in-out)
- FAB toggle (favourite): scale pulse — scale to 1.3× then back to 1.0× (200ms)
- Card press: scale down to 0.97× (100ms) — applied via `Modifier.clickable` + `graphicsLayer`
- All animations must respect `LocalInspectionMode` and `reduceMotion` accessibility setting

---

## Accessibility

- Minimum touch target: 48dp × 48dp for all interactive elements
- All images must have a `contentDescription` (or `null` for decorative images)
- Colour contrast: White on Black = 21:1 ✓ · Gold on Black = 5.2:1 ✓ (passes AA)
- Scalable text: use `sp` for all font sizes, never `dp`
- Focus order follows natural reading direction (respects RTL)

---

## RTL Considerations

- Use `Modifier.padding(start = ..., end = ...)` — never `left/right`
- Use `Arrangement.Start` / `Arrangement.End` — never `Left/Right`
- Icons that indicate direction (back arrow, chevrons) should be auto-mirrored — set `autoMirror = true` in `Icon()`
- The masonry grid layout is direction-agnostic — no changes needed
- Gold gradient on cards: ensure `Brush.verticalGradient` — no horizontal gradient that would look reversed in RTL
