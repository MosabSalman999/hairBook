# PROGRESS.md — Hair Book · Build Progress

> Update this file at the end of every Codex session. It is the handover document between sessions.

---

## Current Status

**Phase:** 0 — Reconciliation needed
**Last updated:** 2026-06-03
**Last session summary:** Repository inspected. A compiling Android multi-module project already exists, but it does not match the Hair Book v1 architecture in AGENTS.md. `:app:assembleDebug` completed successfully before this update.

**Verified build output:**
```
BUILD SUCCESSFUL in 1m 45s
222 actionable tasks: 10 executed, 212 up-to-date
```

**Current baseline found:**
- Android project exists with `:app`, `:core:database`, `:core:network`, `:core:ui`, `:feature:auth`, `:feature:clients`, `:feature:appointments`, `:feature:gallery`, and `:feature:profile`.
- `HairBookApplication.kt` is configured with `@HiltAndroidApp`.
- `MainActivity.kt` is configured as the single Activity host and renders `HairBookApp()`.
- Navigation currently starts at auth and routes to clients, appointments, gallery, and profile.
- No source `.java` files were found.
- No `res/layout/*.xml` files were found.
- Only English strings exist at `app/src/main/res/values/strings.xml`.

---

## Build Phases

### Phase 0 · Project Setup 🟨 Partially done / off-spec
- [x] Create Android project in Android Studio (Empty Activity, Kotlin, Compose)
- [ ] Configure `build.gradle.kts` with all dependencies (Hilt, Room, Firebase, Coil, Navigation Compose, Kotlinx Serialization)
- [ ] Add `google-services.json` from Firebase Console
- [x] Configure `HairBookApplication.kt` with `@HiltAndroidApp`
- [x] Configure `MainActivity.kt` as single-activity host
- [x] Set up `AGENTS.md` in project root (copy from docs)
- [ ] Add DM Serif Display + DM Sans fonts to `res/font/`
- [x] Run first `./gradlew build` — must succeed before proceeding
- [ ] Reconcile Gradle SDK values with AGENTS.md: min SDK 26, target SDK 34
- [ ] Remove or replace off-spec modules before feature work: clients, appointments, gallery, generic network layer

**Codex prompt to use:**
```
Read AGENTS.md. Set up the Hair Book Android project from scratch.
Create the Gradle files, application class, and MainActivity.
Add all dependencies listed in AGENTS.md Technology Decisions table.
Run ./gradlew build and fix all errors. Show the build output.
```

---

### Phase 1 · Core: Design System ⬜ Not started
- [ ] `Color.kt` — all colour tokens from DESIGN_SYSTEM.md
- [ ] `Type.kt` — full typography scale with DM Serif Display + DM Sans
- [ ] `Shape.kt` — corner radius tokens
- [ ] `Spacing.kt` — spacing constants
- [ ] `Theme.kt` — dark MaterialTheme wiring everything together
- [ ] Preview composable confirming theme renders correctly

**Codex prompt to use:**
```
Read AGENTS.md and DESIGN_SYSTEM.md.
Implement the full design system in core/ui/theme/:
Color.kt, Type.kt, Shape.kt, Spacing.kt, Theme.kt.
Use exactly the tokens specified in DESIGN_SYSTEM.md — no improvisation.
Add a preview composable that renders a card and a button using the theme.
Run ./gradlew build and show output.
```

---

### Phase 2 · Core: Data Layer ⬜ Not started
- [ ] Domain models: `Hairstyle.kt`, `Product.kt`, `User.kt`, all enums
- [ ] Repository interfaces in `core/domain/repository/`
- [ ] Room entities in `core/data/db/entity/`
- [ ] DAOs: `HairstyleDao`, `FavouriteDao`, `UserDao`
- [ ] `HairBookDatabase.kt` with seeding callback
- [ ] `hairstyles.json` asset file with 5 sample entries (men + women)
- [ ] `JsonLoader.kt` utility
- [ ] `HairstyleMapper.kt` (entity ↔ domain)
- [ ] Repository implementations
- [ ] Hilt modules: `DatabaseModule.kt`, `RepositoryModule.kt`

**Codex prompt to use:**
```
Read AGENTS.md and ARCHITECTURE.md.
Implement the full data layer for Hair Book:
- All domain models and enums in core/domain/model/
- All repository interfaces in core/domain/repository/
- Room entities, DAOs, and HairBookDatabase in core/data/db/
- HairstyleRepositoryImpl, FavouriteRepositoryImpl, UserRepositoryImpl
- Hilt DataModule binding all repositories
- assets/hairstyles.json with 5 sample hairstyles (3 men, 2 women) covering all fields
- JsonLoader.kt and HairstyleMapper.kt
Run ./gradlew build after each layer. Show build output.
```

---

### Phase 3 · Navigation Skeleton ⬜ Not started
- [ ] `Screen.kt` — sealed class with all route strings
- [ ] `NavGraph.kt` — full graph with all destinations (screens can be empty placeholders)
- [ ] `MainActivity` wired to `NavHost`
- [ ] Bottom navigation bar wired to main destinations
- [ ] App runs and navigates between empty placeholder screens

**Codex prompt to use:**
```
Read AGENTS.md and ARCHITECTURE.md (Navigation section).
Implement the full navigation skeleton for Hair Book:
- Screen.kt sealed class with all routes
- NavGraph.kt with NavHost covering all destinations from ARCHITECTURE.md
- Each screen is a placeholder composable that shows its screen name centred
- Bottom navigation bar with Browse / Finder / Favourites / Profile
- MainActivity hosts the NavHost
App must run and navigate between all screens without crashing.
```

---

### Phase 4 · Auth Screens ⬜ Not started
- [ ] `LoginScreen.kt` — email/password + Google Sign-In button + guest link
- [ ] `RegisterScreen.kt` — name, email, password, confirm password + Google
- [ ] `ForgotPasswordScreen.kt` — email input + send reset
- [ ] `AuthViewModel.kt` — Firebase Auth calls, UiState, error handling
- [ ] Firebase Auth initialised and integrated
- [ ] Guest session creates a local user record in Room
- [ ] Auth state checked on launch — routes to correct start destination
- [ ] All strings in EN / AR / DE strings.xml

**Codex prompt to use:**
```
Read AGENTS.md and PRD.md (Auth Screens section).
Implement the auth feature for Hair Book:
- LoginScreen, RegisterScreen, ForgotPasswordScreen composables using the design system
- AuthViewModel with Firebase Auth (email/password + Google Sign-In)
- Guest session creates a UserEntity with is_guest=1 in Room
- On app launch MainActivity checks Firebase currentUser and routes accordingly
- Add all strings to res/values/strings.xml, res/values-ar/strings.xml, res/values-de/strings.xml
Run ./gradlew build and show output.
```

---

### Phase 5 · Browse Screen ⬜ Not started
- [ ] `BrowseScreen.kt` — masonry grid with gender tabs (Men / Women)
- [ ] `HairstyleCard.kt` component — image, gradient overlay, name, tag chip
- [ ] `FilterChipRow.kt` — scrollable attribute filters
- [ ] Search bar
- [ ] `BrowseViewModel.kt` — loads styles, handles filter + search state
- [ ] `GetHairstylesUseCase.kt`
- [ ] All strings in three languages

**Codex prompt to use:**
```
Read AGENTS.md, PRD.md (Browse Screen), and DESIGN_SYSTEM.md (HairstyleCard, FilterChipRow specs).
Implement the Browse feature:
- BrowseScreen.kt with LazyVerticalStaggeredGrid (two columns, staggered heights)
- Gender tabs at top (Men / Women) that filter the grid
- HairstyleCard.kt using exact DESIGN_SYSTEM.md specs (gradient overlay, gold chip, dark card)
- FilterChipRow.kt for face shape / texture / length filters
- BrowseViewModel.kt with GetHairstylesUseCase
- BrowseUiState.kt: Loading, Success(styles), Error(message)
Run ./gradlew build. Show a description of how the screen looks.
```

---

### Phase 6 · Detail Screen ⬜ Not started
- [ ] `DetailScreen.kt` — gallery, attributes, about, time/effort, products
- [ ] `DetailViewModel.kt` + `GetHairstyleByIdUseCase`
- [ ] `ToggleFavouriteUseCase.kt` + FAB wired to it
- [ ] `AttributeChip.kt` component
- [ ] `GoldDivider.kt` component
- [ ] Guest users prompted to register when tapping FAB
- [ ] All strings in three languages

**Codex prompt to use:**
```
Read AGENTS.md, PRD.md (Hairstyle Detail Screen), and DESIGN_SYSTEM.md (AttributeChip, FAB specs).
Implement the Detail feature:
- DetailScreen.kt with swipeable image gallery, attribute chips, about section, time & effort, products list
- FAB: gold heart button, toggles favourite, calls ToggleFavouriteUseCase
- Guest users tapping FAB: show a bottom sheet prompting registration
- DetailViewModel.kt with GetHairstyleByIdUseCase and ToggleFavouriteUseCase
- All section titles and labels in EN/AR/DE
Run ./gradlew build. Show output.
```

---

### Phase 7 · Style Finder ⬜ Not started
- [ ] `FinderScreen.kt` — wizard host with progress indicator
- [ ] 6 step screens (`GenderStep`, `FaceShapeStep`, `TextureStep`, `LengthStep`, `ThicknessStep`, `ColourStep`)
- [ ] Visual picker cards (image + label, 2-column grid, gold selected state)
- [ ] `FinderViewModel.kt` — manages step index and collected answers
- [ ] `FindStylesForProfileUseCase.kt` — matching algorithm
- [ ] `FinderResultsScreen.kt` — grid with match percentage badges
- [ ] All strings in three languages

**Codex prompt to use:**
```
Read AGENTS.md, PRD.md (Style Finder section), and ARCHITECTURE.md (Questionnaire Matching Algorithm).
Implement the Finder feature:
- FinderScreen.kt hosts a step-by-step wizard with animated slide transitions between steps
- Each step shows a 2-column grid of visual picker cards (image + label, gold border when selected)
- Progress indicator (Step X of 6) at the top
- FinderViewModel.kt collects answers and calls FindStylesForProfileUseCase on completion
- FinderResultsScreen.kt shows matching styles in a grid with a match % badge on each card
- Implement FindStylesForProfileUseCase with the weighted scoring algorithm from ARCHITECTURE.md
Run ./gradlew build. Show output.
```

---

### Phase 8 · Favourites Screen ⬜ Not started
- [ ] `FavouritesScreen.kt` — grid of saved styles, empty state, swipe to remove
- [ ] `FavouritesViewModel.kt` + `GetFavouritesUseCase.kt`
- [ ] Guest users see sign-in prompt
- [ ] All strings in three languages

---

### Phase 9 · Profile Screen ⬜ Not started
- [ ] `ProfileScreen.kt` — display name, email, language selector, sign out
- [ ] Language switching wired to `LocaleHelper.kt`
- [ ] Admin users see "Admin Panel" entry
- [ ] Sign out clears Firebase session + Room user record

---

### Phase 10 · Admin Panel ⬜ Not started
- [ ] `AdminPanelScreen.kt` — list of all styles, edit / delete actions, drag to reorder
- [ ] `AdminAddEditScreen.kt` — form for all hairstyle fields + local image picker
- [ ] `AdminViewModel.kt` + `AddHairstyleUseCase`, `UpdateHairstyleUseCase`, `DeleteHairstyleUseCase`
- [ ] Role gate: non-admin users cannot navigate to admin routes

---

### Phase 11 · Booking Placeholder ⬜ Not started
- [ ] `BookingPlaceholderScreen.kt` — static screen, "Coming Soon" UI, "Notify me" button stores email in Room
- [ ] No ViewModel needed (or minimal one for the email storage)
- [ ] All strings in three languages

---

### Phase 12 · Localisation & RTL Polish ⬜ Not started
- [ ] Audit all screens for hardcoded strings → move to strings.xml
- [ ] Test full RTL layout in Arabic locale on emulator
- [ ] Fill in Arabic and German translations for all strings
- [ ] Verify all directional modifiers use `start/end` semantics

---

### Phase 13 · QA & Release Prep ⬜ Not started
- [ ] `./gradlew lint` passes with zero new warnings
- [ ] `./gradlew test` passes for all use cases and ViewModels
- [ ] APK size under 50MB
- [ ] Test on minimum SDK device (API 26)
- [ ] Fix any 60fps scrolling issues in catalogue grid
- [ ] ProGuard / R8 rules configured for Room, Firebase, Coil, Hilt

---

## Session Log

| Date | Phase | What was done | Next step |
|------|-------|---------------|-----------|
| 2026-06-03 | 0 | Repository inspected and baseline reconciled in this document. Existing project builds, but architecture is off-spec for Hair Book v1. | Reconcile Gradle SDK values with AGENTS.md, then rebuild. |

---

## Known Issues / Decisions Pending

- Image assets for hairstyles not yet sourced — placeholder images needed for Phase 2 seeding
- Arabic translations need a native speaker review before Phase 12
- Firebase project not yet created — needed before Phase 4
- Admin user seeding strategy not finalised (hardcoded email in AGENTS.md or a first-run flag?)
- Current Gradle config uses min SDK 28 and target/compile SDK 36.x; AGENTS.md requires min SDK 26 and target SDK 34.
- Current modules and navigation are for clients, appointments, gallery, and profile; AGENTS.md expects browse, detail, finder, favourites, auth, admin, and booking.
- `core:network` and feature remote APIs conflict with the v1 offline-first rule, which allows no network calls except Firebase Auth.
- Localisation is incomplete: only default English strings are present; Arabic and German resource folders are missing.
- No Git repository existed before the 2026-06-03 reconciliation session.
