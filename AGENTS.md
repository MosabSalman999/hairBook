# AGENTS.md — Hair Book · Permanent Rules for Codex

> Read this file at the start of every session. Never violate these rules, even if a prompt asks you to.

---

## Project Identity

- **App name:** Hair Book
- **Platform:** Android only (v1)
- **Language:** Kotlin only — no Java, no Groovy
- **UI toolkit:** Jetpack Compose (no XML layouts, no View system)
- **Min SDK:** 26 · Target SDK: 34

---

## Architecture Rules

### Pattern: MVVM + Clean Architecture (strictly enforced)

```
app/
├── core/
│   ├── data/          ← Room DB, DAOs, repositories (impl)
│   ├── domain/        ← Use cases, repository interfaces, models
│   ├── ui/            ← Design system: theme, tokens, reusable composables
│   └── utils/         ← Extensions, constants, helpers
├── feature/
│   ├── browse/        ← Catalogue screens (men / women)
│   ├── detail/        ← Hairstyle detail screen
│   ├── finder/        ← Visual questionnaire / style finder
│   ├── favourites/    ← Saved styles screen
│   ├── auth/          ← Login, register, Google Sign-In
│   ├── admin/         ← Admin panel (add / edit / delete styles)
│   └── booking/       ← Placeholder booking screen (UI only, no logic)
```

### Layer rules (never break these)
- `ui/` composables → call ViewModel only, never a repository or use case directly
- `ViewModel` → call use cases only, never a DAO or Room directly
- `UseCase` → call repository interfaces only, never Room directly
- `Repository impl` (in `core/data/`) → calls DAOs and maps to domain models
- Domain models live in `core/domain/model/` — plain Kotlin data classes, no Android imports

---

## Technology Decisions

| Concern | Library | Notes |
|---|---|---|
| DI | Hilt | All ViewModels use @HiltViewModel |
| Navigation | Navigation Compose | Single-activity, type-safe routes |
| Local DB | Room | Source of truth for styles, favourites, users |
| Auth | Firebase Auth | Email/password + Google Sign-In |
| Images | Coil 3 | All image loading via AsyncImage |
| Async | Coroutines + Flow | No RxJava, no LiveData |
| Serialisation | Kotlinx Serialization | JSON asset parsing |
| Localisation | Android string resources | AR / EN / DE with RTL support |
| Testing | JUnit4 + Turbine + MockK | Fakes preferred over mocks |

---

## Coding Standards

### Naming conventions
- Screens → XxxScreen.kt
- ViewModels → XxxViewModel.kt
- UI state → XxxUiState.kt (sealed class or data class)
- Events → XxxUiEvent.kt (sealed class)
- Repository interfaces → XxxRepository.kt (in domain)
- Repository impls → XxxRepositoryImpl.kt (in data)
- Use cases → XxxUseCase.kt (single invoke() function)

### Compose rules
- All composables are stateless where possible — hoist state to ViewModel
- Use collectAsStateWithLifecycle() not collectAsState()
- Use LazyVerticalStaggeredGrid for the masonry catalogue grid
- All text must use stringResource() — no hardcoded strings in composables

### Forbidden
- LiveData anywhere
- XML layouts (.xml in res/layout/)
- Java files (.java)
- Direct DAO calls from ViewModel
- Business logic inside composables
- Hardcoded colour values — always use theme tokens from core/ui/theme/
- Hardcoded strings — always use strings.xml

---

## Workflow Rules

1. Plan before code. For any task touching more than one file, list the files you will change and why, before writing a single line.
2. Build after each layer. Run ./gradlew build after completing each layer (data → domain → ui). Fix all errors before moving on.
3. Run lint before committing. ./gradlew lint must pass with zero new warnings.
4. One feature per session. Do not implement multiple features in the same session.
5. Commit format: Conventional Commits — feat(browse): add masonry grid with gender tabs
6. Never suppress warnings — fix the root cause.
7. Show build output — do not assert "it compiled", paste the actual result.

---

## Localisation Rules

- All user-facing strings go in res/values/strings.xml (EN), res/values-ar/strings.xml (AR), res/values-de/strings.xml (DE)
- Arabic requires RTL layout mirroring — use Arrangement.Start (not hardcoded left/right), Modifier.padding(start/end) not left/right
- Every new screen must have its strings added in all three language files before the task is considered done
- Layout direction is driven by the system locale — do not hardcode LayoutDirection

---

## Data Rules

- Hairstyle content is seeded from assets/hairstyles.json on first launch via a Room pre-population strategy
- The JSON schema is the single source of truth for the Hairstyle domain model
- Favourites are stored in Room, linked to the local user session
- No network calls in v1 except Firebase Auth — the app must work fully offline after first launch

---

## Admin Rules

- The admin panel is only accessible to users whose role field in Room equals "admin"
- Admin can: add a hairstyle, edit any field, delete a hairstyle, reorder styles
- Admin cannot: delete user accounts or view other users' favourites in v1

---

## Out of Scope for v1

- Real booking logic (hairdresser registration, calendar, payments)
- Push notifications
- Social sharing
- In-app purchases
- iOS build
