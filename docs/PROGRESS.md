# PROGRESS.md — Hair Book · Build Progress

> Update this file at the end of every Codex session. It is the handover document between sessions.

---

## Current Status

**Phase:** 2 — Data layer partially done (entities + DAOs complete; domain layer + repositories pending)
**Last updated:** 2026-06-04
**Last session summary:** `:core:database` schema fully replaced with v1 spec. All three off-spec entities (Client, Appointment, GalleryPhoto) and their DAOs deleted. `HairstyleEntity`, `FavouriteEntity`, and updated `UserEntity` written. `HairstyleDao` and `FavouriteDao` written. `HairBookDatabase` and `DatabaseModule` rebuilt to reference only v1 types. Build verified: `BUILD SUCCESSFUL` with all tasks executed (not cached).

**Verified build output (`.\gradlew.bat :app:assembleDebug --no-daemon --max-workers=1 --console plain`):**
```
BUILD SUCCESSFUL in 4m 56s
250 actionable tasks: 250 executed
```

**Verified lint output (`.\gradlew.bat lint --no-daemon --max-workers=1 --console plain`):**
```
BUILD SUCCESSFUL in 2m 53s
441 actionable tasks: 180 executed, 261 up-to-date
```

**Current baseline:**
- `:core:database` contains only v1 entities (`HairstyleEntity`, `FavouriteEntity`, `UserEntity`) and v1 DAOs (`HairstyleDao`, `FavouriteDao`, `UserDao`).
- `HairBookDatabase` wired to all three v1 entities; `DatabaseModule` provides all three v1 DAOs.
- No `core:domain` Gradle module exists yet — domain enums, models, and repository interfaces have no home.
- `:core:ui` theme is still a skeleton (3 colour tokens, no Shape/Spacing/dark theme) — Phase 1 not yet started.

---

## Build Phases

### Phase 0 · Project Setup 🟨 Partially done
- [x] Create Android project in Android Studio (Empty Activity, Kotlin, Compose)
- [x] Configure `build.gradle.kts` with all dependencies (Hilt, Room, Firebase, Coil, Navigation Compose, Kotlinx Serialization)
- [ ] Add `google-services.json` from Firebase Console
- [x] Configure `HairBookApplication.kt` with `@HiltAndroidApp`
- [x] Configure `MainActivity.kt` as single-activity host
- [x] Set up `AGENTS.md` in project root (copy from docs)
- [ ] Add DM Serif Display + DM Sans fonts to `res/font/`
- [x] Run first `./gradlew build` — must succeed before proceeding
- [x] Reconcile Gradle SDK values with AGENTS.md: min SDK 26, target SDK 34
- [x] Remove or replace off-spec modules before feature work: clients, appointments, gallery, generic network layer

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

### Phase 2 · Core: Data Layer 🟨 Partially done
- [ ] Domain models: `Hairstyle.kt`, `Product.kt`, `User.kt`, all enums
- [ ] Repository interfaces in `core/domain/repository/`
- [x] Room entities: `HairstyleEntity.kt`, `FavouriteEntity.kt`, `UserEntity.kt`
- [x] DAOs: `HairstyleDao`, `FavouriteDao`, `UserDao`
- [x] `HairBookDatabase.kt` wired to v1 entities (seeding callback deferred to repo impl step)
- [ ] `hairstyles.json` asset file with 5 sample entries (men + women)
- [ ] `JsonLoader.kt` utility
- [ ] `HairstyleMapper.kt` (entity ↔ domain)
- [ ] Repository implementations
- [x] `DatabaseModule.kt` providing all v1 DAOs
- [ ] `RepositoryModule.kt`

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
| 2026-06-03 | 0 | Reconciled the Gradle module graph for v1: added browse/detail/finder/favourites/admin/booking placeholders, removed clients/appointments/gallery/network from the build, and verified `:app:assembleDebug`. | Implement Phase 1 design system tokens from DESIGN_SYSTEM.md. |
| 2026-06-03 | 0 | Added missing Phase 0 dependency catalog entries and app dependencies for Firebase Auth, Coil 3, Kotlinx Serialization, Material icons, Turbine, and MockK. Ignored local `debug.log`. | Commit Phase 0 reconciliation, then implement Phase 1 design system tokens. |
| 2026-06-04 | 2 | Replaced entire `:core:database` schema with v1 spec. Deleted 7 off-spec files (ClientEntity, AppointmentEntity, GalleryPhotoEntity + their DAOs + DateTimeConverters). Wrote HairstyleEntity (19 cols), FavouriteEntity (unique index on user+style), updated UserEntity (+role, is_guest, created_at). Wrote HairstyleDao and FavouriteDao. Rebuilt HairBookDatabase and DatabaseModule. Build verified `SUCCESSFUL` with all tasks executed. | Create `core:domain` Gradle module; write domain enums and models; write repository interfaces. |
| 2026-06-04 | 0/2 | Clean build and lint both passed for the current v1 baseline. Fixed auth/profile user mapping to provide `createdAt` for the updated `UserEntity`. | Commit current v1 baseline; then continue with `core:domain` or Phase 1 design tokens. |

---

## Known Issues / Decisions Pending

- Image assets for hairstyles not yet sourced — placeholder images needed for Phase 2 seeding
- Arabic translations need a native speaker review before Phase 12
- Firebase project not yet created and `google-services.json` not present — needed before Phase 4. The Google Services plugin alias exists but is not applied to `:app` until the real JSON file is available.
- DM Serif Display and DM Sans font files are not present yet — needed for Phase 1 typography.
- Admin user seeding strategy not finalised (hardcoded email in AGENTS.md or a first-run flag?)
- Compile SDK remains on the installed SDK 36.1 because the local SDK Platform 34 install failed with `FileAlreadyExistsException`; min SDK is 26 and target SDK is 34 as required.
- `android.disallowKotlinSourceSets=false` remains in `gradle.properties` because removing it currently breaks AGP/KSP configuration with built-in Kotlin source sets.
- Off-spec source folders (`core/network`, `feature/clients`, `feature/appointments`, `feature/gallery`) remain on disk for now but are no longer included in `settings.gradle.kts` or app dependencies.
- No `core:domain` Gradle module exists yet — domain enums, models (`Hairstyle.kt`, `User.kt`), and repository interfaces need a home before repository implementations can be written.
- Firebase project is not configured and Firebase Auth dependencies are not wired yet.
- Localisation for placeholder v1 modules exists in EN / AR / DE, but the full app still needs a localisation audit once real screens are implemented.
