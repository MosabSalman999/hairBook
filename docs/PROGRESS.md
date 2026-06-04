# PROGRESS.md — Hair Book · Build Progress

> Update this file at the end of every Codex session. It is the handover document between sessions.

---

## Current Status

**Phase:** Phase 15 complete — category gradient images live on HomeScreen, build clean
**Last updated:** 2026-06-04
**Last session summary:** Phase 15 delivered: 7 Android gradient drawable XMLs created in `feature/browse/src/main/res/drawable/` (one per category: fade gold-radial, undercut gold→black, textured navy diagonal, taper amber, bob rose-radial, curls forest-green, layers warm-gold). `CategoryCard` updated with optional `@DrawableRes imageRes` parameter — uses synchronous `painterResource()` when set (no Coil pipeline). `HomeScreen` `CategorySample` data class extended with `imageRes` field; all 7 entries wired to their `R.drawable.category_*` IDs. Build verified BUILD SUCCESSFUL. Next: HairstyleMapper.kt → HairstyleRepositoryImpl → RepositoryModule → use cases → ViewModel wiring.

**Verified build output (`.\gradlew.bat :app:assembleDebug --no-daemon --console plain`):**
```
BUILD SUCCESSFUL in 1m 9s
250 actionable tasks: 45 executed, 205 up-to-date
```

**Verified lint output (`.\gradlew.bat lint --no-daemon --max-workers=1 --console plain`):**
```
BUILD SUCCESSFUL in 1m 28s
476 actionable tasks: 16 executed, 460 up-to-date
```

**Current baseline:**
- `:core:ui` design system complete: `Color.kt`, `Type.kt`, `Shape.kt`, `Spacing.kt`, `Theme.kt` (dark-only MaterialTheme). `HairBookTopBar`, `HairBookButton` (+secondary), `HairstyleCard` (uses `HairBookImage`), `CategoryCard`, `HairBookImage` (Coil 3 wrapper) components.
- `:core:ui/build.gradle.kts` — `api(libs.androidx.material.icons.extended)` and `api(libs.coil.compose)` added so all feature modules get both transitively.
- `app/src/main/assets/hairstyles.json` — 13 entries (8 MEN: 5 fade + undercut + textured + taper; 5 WOMEN: bob×2 + curls + layers×2), all schema fields populated including `category`.
- Navigation: `AppNavHost` — start destination = `Home`; routes: Home → Category/{categoryId} → Detail; Browse/Finder/Favourites/Profile bottom nav. Auth/Admin/Booking reachable from Profile or internal nav.
- `HairBookApplication` configured as `SingletonImageLoader.Factory` (crossfade 300ms, memory cache 25%).
- All 8 feature screens (auth, browse, detail, finder, favourites, profile, admin, booking) — real UI with static demo data; no ViewModel wiring yet.
- All feature module strings updated in EN, AR, DE. App-level nav strings added.
- `:core:domain` — full domain models, enums, repository interfaces (no Android imports). Not yet wired as dependency of any feature module; screens use hardcoded sample data.
- Repository implementations, JsonLoader, HairstyleMapper, and RepositoryModule still outstanding (Phase 2 remainder).
- Firebase `google-services.json` still absent — FirebaseAuth not wired.

---

## Build / Sync Fixes

### 2026-06-04 · Gradle Sync stuck "downloading/importing dependencies" forever

**Symptom:** Android Studio Gradle Sync never completed — it appeared to continuously download/import dependencies.

**Root cause (evidence-backed):**
1. `gradle.properties` had been changed to `org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError …` (baseline was `-Xmx2048m`).
2. The Gradle daemon JVM then crashed with a **native OOM** — `hs_err_pid119312.log`: *"Native memory allocation (malloc) failed … the Java Heap may be blocking the growth of the native heap."* The oversized heap reservation (compounded by crash→restart spawning multiple ~4 GB daemons) exhausted system RAM.
3. The crash happened mid-download, leaving a stale lock + partial distribution (`gradle/wrapper/dists/gradle-9.3.1-bin/**/gradle-9.3.1-bin.zip.part` + `.zip.lck`). Each sync re-attempted the locked/partial download → the "never completes" symptom.
4. Crash-restart cycles also scattered stray `GRADLE_USER_HOME` trees into the project (`caches/`, `daemon/`, `wrapper/`, `native/`, `gradle/daemon/`). `android/FakeDependency.jar` was unreferenced junk (appears only in the JVM replay log).
5. **Confirmed during cleanup:** the running Android Studio process (`studio64.exe`) had `native-platform.dll` loaded from the project's `native/` folder, proving the IDE was configured to use the **project directory as its `GRADLE_USER_HOME`** instead of the default `~/.gradle`. This is why Gradle caches/daemon/wrapper kept materialising inside the project. **Prevent recurrence:** in Android Studio → *Settings → Build, Execution, Deployment → Build Tools → Gradle*, ensure "Gradle user home" is the default (`C:\Users\NTC-\.gradle`), and clear any `GRADLE_USER_HOME` env var pointing into the project.

**Fix (minimal):** Reverted the single offending line in `gradle.properties` back to the proven baseline:
```
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
```
No version changes, no architectural changes. The stuck partial download/lock and stray in-project Gradle homes are untracked junk that should be cleared (`gradle/wrapper/dists/`, root `caches/ daemon/ wrapper/ native/ .tmp/ android/`, `gradle/daemon/`, `hs_err_pid*.log`, `replay_pid*.log`, `debug.log`); the healthy distribution lives in `~/.gradle`.

**Verified (offline, against `~/.gradle`):**
```
.\gradlew.bat help --offline --no-daemon
> BUILD SUCCESSFUL in 42s

.\gradlew.bat :app:dependencies --configuration debugRuntimeClasspath --offline --no-daemon
> BUILD SUCCESSFUL in 35s
```
Full configuration + dependency-graph resolution (what Sync performs) now completes with no re-downloading and no OOM.

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

### Phase 1 · Core: Design System 🟩 Done (preview composable pending)
- [x] `Color.kt` — all colour tokens from DESIGN_SYSTEM.md
- [x] `Type.kt` — full typography scale (system font fallbacks; DM Serif Display + DM Sans pending)
- [x] `Shape.kt` — corner radius tokens
- [x] `Spacing.kt` — spacing constants
- [x] `Theme.kt` — dark MaterialTheme wiring everything together
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
- [x] Domain models: `Hairstyle.kt`, `Product.kt`, `User.kt`, all enums
- [x] Repository interfaces in `core/domain/repository/`
- [x] Room entities: `HairstyleEntity.kt`, `FavouriteEntity.kt`, `UserEntity.kt`
- [x] DAOs: `HairstyleDao`, `FavouriteDao`, `UserDao`
- [x] `HairBookDatabase.kt` wired to v1 entities (seeding callback deferred to repo impl step)
- [x] `hairstyles.json` asset file with 5 sample entries (men + women)
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

### Phase 3 · Navigation Skeleton 🟩 Done (type-safe routes pending)
- [ ] `Screen.kt` — sealed class with all route strings (using enum AppDestination for now)
- [x] `NavGraph.kt` — full graph with all destinations
- [x] `MainActivity` wired to `NavHost`
- [x] Bottom navigation bar wired to main destinations (Browse/Finder/Favourites/Profile)
- [x] App runs and navigates between all screens

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

### Phase 4 · Auth Screens 🟨 Partially done
- [x] `LoginScreen.kt` — email/password fields, Sign In CTA, Continue as Guest, Register link
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

### Phase 5 · Browse Screen 🟨 Partially done
- [x] `BrowseScreen.kt` — masonry staggered grid with gender TabRow + filter chips
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

### Phase 6 · Detail Screen 🟨 Partially done
- [x] `DetailScreen.kt` — hero image area, FlowRow attribute chips, About / Time&amp;Effort / Products sections, gold heart FAB
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

### Phase 7 · Style Finder 🟨 Partially done
- [x] `FinderScreen.kt` — 6-step wizard with LinearProgressIndicator, 2-col visual picker grid, Next/Back buttons
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

### Phase 8 · Favourites Screen 🟨 Partially done
- [x] `FavouritesScreen.kt` — staggered grid of saved styles + empty-state with icon/text; swipe-to-remove pending
- [ ] `FavouritesViewModel.kt` + `GetFavouritesUseCase.kt`
- [ ] Guest users see sign-in prompt
- [ ] All strings in three languages

---

### Phase 9 · Profile Screen 🟨 Partially done
- [x] `ProfileScreen.kt` — avatar, language selector (EN/AR/DE), Admin + Booking nav rows, Sign In / Sign Out buttons
- [ ] Language switching wired to `LocaleHelper.kt`
- [ ] Admin users see "Admin Panel" entry
- [ ] Sign out clears Firebase session + Room user record

---

### Phase 10 · Admin Panel 🟨 Partially done
- [x] `AdminScreen.kt` — style list with Edit/Delete icon buttons per row, Add FAB; drag-to-reorder pending
- [ ] `AdminAddEditScreen.kt` — form for all hairstyle fields + local image picker
- [ ] `AdminViewModel.kt` + `AddHairstyleUseCase`, `UpdateHairstyleUseCase`, `DeleteHairstyleUseCase`
- [ ] Role gate: non-admin users cannot navigate to admin routes

---

### Phase 11 · Booking Placeholder 🟨 Partially done
- [x] `BookingScreen.kt` — centred "Coming Soon" layout, gold star icon, subtitle, email input + Notify Me button; email-to-Room storage pending
- [ ] No ViewModel needed (or minimal one for the email storage)
- [ ] All strings in three languages

---

### Phase 14 · Category Navigation & Image Loading 🟩 Done

- [x] `HaircutCategory.kt` domain model (core/domain/model/)
- [x] `category` field added to `Hairstyle.kt` and `HairstyleEntity.kt`
- [x] DB version bumped 1→2 with `Migration(1,2)` adding `category` column
- [x] `hairstyles.json` expanded to 13 entries with `category` field (5 fade variants + taper + undercut + textured for men; bob×2 + curls + layers×2 for women)
- [x] `HairBookImage.kt` Coil 3 wrapper component (core/ui/component/)
- [x] `CategoryCard.kt` folder card component (core/ui/component/)
- [x] `HomeScreen.kt` + `HomeRoute.kt` (feature/browse/ui/)
- [x] `CategoryScreen.kt` + `CategoryRoute.kt` (feature/browse/ui/)
- [x] `AppDestination` updated: `Home`, `Category` routes added; `Home` is new start destination
- [x] `AppNavHost` wired: new routes, start destination changed, bottom nav first tab uses `Home`
- [x] `HairstyleCard.kt` upgraded to accept optional `imageUrl` and use `HairBookImage`
- [x] `DetailScreen.kt` hero area upgraded to use `HairBookImage`
- [x] Coil singleton configured in `HairBookApplication.kt` (crossfade 300ms, memory cache 25%)
- [x] New strings in EN / AR / DE for home title and all category names
- [x] `core/ui/build.gradle.kts` — `api(libs.coil.compose)` added
- [x] docs/PRD.md, docs/ARCHITECTURE.md updated

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
| 2026-06-04 | 1–13 | **Multi-phase UI session.** Phase 1: full design system implemented (Color/Type/Shape/Spacing/Theme, plus HairBookTopBar/Button/HairstyleCard components). `api(material-icons-extended)` added to core:ui so all features get Icons transitively. Phase 2: `hairstyles.json` created with 5 sample entries. Phase 3: AppNavHost rebuilt with Scaffold + NavigationBar. Phases 4–11: all 8 feature screens upgraded to real UI (auth login, browse masonry grid, detail sections+FAB, finder 6-step wizard, favourites grid+empty state, profile, admin list, booking coming-soon). Phase 12: all strings updated EN/AR/DE for all features + app-level nav strings. Phase 13: `assembleDebug` BUILD SUCCESSFUL (250 tasks), `lint` BUILD SUCCESSFUL (476 tasks). | Next priority: wire ViewModels + use cases to screens; implement repository impls + Firebase Auth. |
| 2026-06-04 | 2/13 | Re-ran `:app:assembleDebug` after the UI/design-system reconciliation. Debug app build passes: `BUILD SUCCESSFUL in 1m 25s`, 250 actionable tasks. | Continue with mappers, JsonLoader, repository implementations, and `RepositoryModule`. |
| 2026-06-04 | 14 | **Category navigation + image loading.** HomeScreen (category folder grid) replaces Browse as start destination. CategoryScreen added. HairBookImage (Coil 3 wrapper) and CategoryCard components created. HairstyleCard/DetailScreen upgraded to use HairBookImage. Hairstyle domain model + HairstyleEntity updated with `category` field (DB migration 1→2). hairstyles.json expanded to 13 entries. AppNavHost/AppDestination updated. Coil singleton configured in Application. Strings (EN/AR/DE) updated. docs updated. | Run `:app:assembleDebug` and verify navigation flow. |
| 2026-06-04 | Build | **Fixed Gradle Sync stuck in a perpetual download loop.** Root cause: an uncommitted `gradle.properties` bump to `-Xmx4096m -XX:MaxMetaspaceSize=1024m` caused a native-OOM daemon crash mid distribution-download, leaving a stale `.lck` + `.part` and scattering in-project `GRADLE_USER_HOME` trees (Android Studio was using the project dir as its Gradle home). Reverted heap to baseline `-Xmx2048m` (now matches committed HEAD), cleared the stuck download + stray homes, repointed IDE Gradle home to `~/.gradle`. Verified `.\gradlew.bat help` → `BUILD SUCCESSFUL in 3s`, no re-download, no stray homes regenerated. See **Build / Sync Fixes** section above. | Wire ViewModels + use cases to screens; implement repository impls, JsonLoader, mappers, and `RepositoryModule` (Phase 2 remainder). |
| 2026-06-04 | 15 | **Category gradient images.** Created 7 gradient drawable XMLs in `feature/browse/src/main/res/drawable/` (fade radial gold, undercut linear 270°, textured linear 45° navy, taper linear 180° amber, bob radial rose, curls linear 135° green, layers linear 225° warm-gold). Added optional `@DrawableRes imageRes: Int? = null` to `CategoryCard` — renders via synchronous `painterResource()`, bypassing Coil. Extended `HomeScreen.CategorySample` with `imageRes` and wired all 7 entries. Build `SUCCESSFUL` in 1m 9s, 250 tasks. | Implement HairstyleMapper + HairstyleRepositoryImpl + RepositoryModule (Phase 2 remainder). |

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
- Firebase project is not configured and Firebase Auth dependencies are not wired yet.
- Localisation for placeholder v1 modules exists in EN / AR / DE, but the full app still needs a localisation audit once real screens are implemented.
