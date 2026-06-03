# ARCHITECTURE.md — Hair Book · Technical Architecture

## Pattern: MVVM + Clean Architecture

Every feature follows the same three-layer structure. Data flows in one direction: Room/JSON → Repository → UseCase → ViewModel → Composable. Events flow in the opposite direction: user tap → composable → ViewModel → UseCase → Repository.

```
Composable  ──event──▶  ViewModel  ──calls──▶  UseCase  ──calls──▶  Repository (interface)
    ▲                       │                                               │
    └──── UiState ──────────┘                                    Repository (impl)
                                                                            │
                                                                     Room DAO / JSON
```

---

## Module Structure

```
app/
├── src/main/
│   ├── AndroidManifest.xml
│   ├── HairBookApplication.kt       ← @HiltAndroidApp
│   └── MainActivity.kt              ← Single activity, hosts NavHost
│
├── core/
│   ├── data/
│   │   ├── db/
│   │   │   ├── HairBookDatabase.kt  ← RoomDatabase
│   │   │   ├── dao/
│   │   │   │   ├── HairstyleDao.kt
│   │   │   │   ├── FavouriteDao.kt
│   │   │   │   └── UserDao.kt
│   │   │   └── entity/
│   │   │       ├── HairstyleEntity.kt
│   │   │       ├── FavouriteEntity.kt
│   │   │       └── UserEntity.kt
│   │   ├── repository/
│   │   │   ├── HairstyleRepositoryImpl.kt
│   │   │   ├── FavouriteRepositoryImpl.kt
│   │   │   └── UserRepositoryImpl.kt
│   │   ├── mapper/
│   │   │   └── HairstyleMapper.kt   ← Entity ↔ Domain model
│   │   └── di/
│   │       └── DataModule.kt        ← Hilt module binding repos
│   │
│   ├── domain/
│   │   ├── model/
│   │   │   ├── Hairstyle.kt         ← Pure Kotlin, no Android imports
│   │   │   ├── Product.kt
│   │   │   ├── User.kt
│   │   │   └── enums/
│   │   │       ├── Gender.kt
│   │   │       ├── FaceShape.kt
│   │   │       ├── HairTexture.kt
│   │   │       ├── HairLength.kt
│   │   │       ├── HairThickness.kt
│   │   │       └── HairColour.kt
│   │   ├── repository/
│   │   │   ├── HairstyleRepository.kt   ← interface
│   │   │   ├── FavouriteRepository.kt   ← interface
│   │   │   └── UserRepository.kt        ← interface
│   │   └── usecase/
│   │       ├── GetHairstylesUseCase.kt
│   │       ├── GetHairstyleByIdUseCase.kt
│   │       ├── FindStylesForProfileUseCase.kt  ← questionnaire matching
│   │       ├── ToggleFavouriteUseCase.kt
│   │       ├── GetFavouritesUseCase.kt
│   │       ├── AddHairstyleUseCase.kt
│   │       ├── UpdateHairstyleUseCase.kt
│   │       └── DeleteHairstyleUseCase.kt
│   │
│   ├── ui/
│   │   ├── theme/
│   │   │   ├── Color.kt             ← All colour tokens (see DESIGN_SYSTEM.md)
│   │   │   ├── Type.kt              ← Typography scale
│   │   │   ├── Shape.kt             ← Corner radius tokens
│   │   │   └── Theme.kt             ← MaterialTheme wrapper
│   │   └── components/
│   │       ├── HairBookTopBar.kt
│   │       ├── HairstyleCard.kt     ← Used in Browse + Favourites
│   │       ├── FilterChipRow.kt
│   │       ├── AttributeChip.kt
│   │       ├── LoadingScreen.kt
│   │       ├── ErrorScreen.kt
│   │       └── EmptyState.kt
│   │
│   └── utils/
│       ├── JsonLoader.kt            ← Reads hairstyles.json from assets
│       ├── LocaleHelper.kt          ← Language switching utility
│       └── Extensions.kt
│
├── feature/
│   ├── browse/
│   │   ├── BrowseScreen.kt
│   │   ├── BrowseViewModel.kt
│   │   └── BrowseUiState.kt
│   │
│   ├── detail/
│   │   ├── DetailScreen.kt
│   │   ├── DetailViewModel.kt
│   │   └── DetailUiState.kt
│   │
│   ├── finder/
│   │   ├── FinderScreen.kt          ← Hosts the wizard
│   │   ├── FinderViewModel.kt
│   │   ├── FinderUiState.kt
│   │   ├── FinderResultsScreen.kt
│   │   └── steps/
│   │       ├── GenderStepScreen.kt
│   │       ├── FaceShapeStepScreen.kt
│   │       ├── TextureStepScreen.kt
│   │       ├── LengthStepScreen.kt
│   │       ├── ThicknessStepScreen.kt
│   │       └── ColourStepScreen.kt
│   │
│   ├── favourites/
│   │   ├── FavouritesScreen.kt
│   │   ├── FavouritesViewModel.kt
│   │   └── FavouritesUiState.kt
│   │
│   ├── auth/
│   │   ├── LoginScreen.kt
│   │   ├── RegisterScreen.kt
│   │   ├── ForgotPasswordScreen.kt
│   │   └── AuthViewModel.kt
│   │
│   ├── profile/
│   │   ├── ProfileScreen.kt
│   │   └── ProfileViewModel.kt
│   │
│   ├── admin/
│   │   ├── AdminPanelScreen.kt
│   │   ├── AdminAddEditScreen.kt
│   │   └── AdminViewModel.kt
│   │
│   └── booking/
│       └── BookingPlaceholderScreen.kt   ← Static screen, no ViewModel needed
│
└── navigation/
    ├── NavGraph.kt                  ← All routes defined here
    └── Screen.kt                   ← Sealed class of route strings
```

---

## Navigation

Single `NavHost` in `MainActivity`. Bottom navigation bar controls the four main destinations. Auth screens are on a separate back stack.

```
NavGraph
├── auth/
│   ├── login          (start destination if not signed in)
│   ├── register
│   └── forgot_password
│
└── main/                           (start destination if signed in / guest)
    ├── home           ← gender selection landing
    ├── browse/{gender}
    ├── detail/{hairstyleId}
    ├── finder
    ├── finder_results
    ├── favourites
    ├── profile
    ├── admin_panel    (role-gated)
    ├── admin_add_edit/{hairstyleId?}
    └── booking_placeholder
```

Bottom nav bar items: Browse · Finder · Favourites · Profile

---

## Database Schema (Room)

### hairstyles table
```
id TEXT PRIMARY KEY
name_en TEXT NOT NULL
name_ar TEXT NOT NULL
name_de TEXT NOT NULL
gender TEXT NOT NULL          -- "MEN" | "WOMEN"
hero_image TEXT NOT NULL
gallery TEXT NOT NULL         -- JSON array of paths (stored as String)
description_en TEXT NOT NULL
description_ar TEXT NOT NULL
description_de TEXT NOT NULL
face_shapes TEXT NOT NULL     -- JSON array of enum names
texture TEXT NOT NULL         -- JSON array
lengths TEXT NOT NULL         -- JSON array
thickness TEXT NOT NULL       -- JSON array
colours TEXT NOT NULL         -- JSON array
time_minutes INTEGER NOT NULL
difficulty INTEGER NOT NULL
products TEXT NOT NULL        -- JSON array of {name, type}
sort_order INTEGER NOT NULL
```

### favourites table
```
id INTEGER PRIMARY KEY AUTOINCREMENT
user_id TEXT NOT NULL
hairstyle_id TEXT NOT NULL
saved_at INTEGER NOT NULL     -- epoch millis
UNIQUE(user_id, hairstyle_id)
```

### users table
```
id TEXT PRIMARY KEY           -- Firebase UID or local UUID for guest
email TEXT
display_name TEXT
role TEXT NOT NULL DEFAULT "user"   -- "user" | "admin"
is_guest INTEGER NOT NULL DEFAULT 0
created_at INTEGER NOT NULL
```

---

## Data Seeding Strategy

On first launch, `HairBookDatabase` is pre-populated from `assets/hairstyles.json` using a `RoomDatabase.Callback` that runs `onCreate`. The `JsonLoader` utility reads the file, deserialises it with Kotlinx Serialization, and bulk-inserts via `HairstyleDao.insertAll()`.

```kotlin
// Pseudocode — actual implementation in DataModule.kt
Room.databaseBuilder(context, HairBookDatabase::class.java, "hairbook.db")
    .addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            // Trigger coroutine seed job
        }
    })
    .build()
```

---

## Authentication Flow

```
App launch
    │
    ├─ Firebase.currentUser != null ──▶ Main graph (registered user)
    │
    ├─ currentUser == null, guest session active ──▶ Main graph (guest)
    │
    └─ No session ──▶ Auth graph (Login screen)
                          │
                          ├─ Email/password ──▶ Firebase signInWithEmailAndPassword
                          ├─ Google Sign-In ──▶ Firebase signInWithCredential(GoogleAuthProvider)
                          └─ Continue as guest ──▶ create local guest record in Room
```

---

## Questionnaire Matching Algorithm

`FindStylesForProfileUseCase` scores every hairstyle against the user's answers:

```
score = 0
for each attribute (faceShape, texture, length, thickness, colour, gender):
    if hairstyle.attribute contains userAnswer:
        score += weight[attribute]

matchPercentage = (score / maxPossibleScore) * 100
```

Weights (adjustable constants in `FinderConstants.kt`):
- gender: 30 (hard filter — mismatched gender scores 0 total)
- faceShape: 25
- texture: 15
- length: 15
- thickness: 10
- colour: 5

Results sorted by descending matchPercentage. Styles below 30% are excluded.

---

## Dependency Injection (Hilt)

```
@HiltAndroidApp HairBookApplication
    │
    ├── DatabaseModule    → provides HairBookDatabase, all DAOs
    ├── RepositoryModule  → binds XxxRepository interface to XxxRepositoryImpl
    ├── FirebaseModule    → provides FirebaseAuth instance
    └── UseCaseModule     → (optional) provide use cases if needed across features
```

All ViewModels are annotated with `@HiltViewModel` and injected with `@Inject constructor(...)`.

---

## Localisation Architecture

- `LocaleHelper.kt` wraps `AppCompatDelegate.setApplicationLocales()` (API 33+) with a fallback for older APIs
- Language preference stored in `DataStore<Preferences>` (not SharedPreferences)
- On language change, the Activity is recreated — NavBackStack is preserved via `rememberSaveable`
- RTL: Compose honours `LocalLayoutDirection` automatically when system locale is Arabic — no manual mirroring needed as long as layout uses `start/end` semantics throughout
