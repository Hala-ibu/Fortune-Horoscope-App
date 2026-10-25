# Fortune Horoscope

Fortune Horoscope is a native Android app for a little everyday cosmic guidance. It brings horoscope readings and zodiac profiles together with practical personal rituals and a gallery for keeping zodiac-inspired poster ideas.

The app opens on a featured horoscope with its message, mood, title, and lucky number. From the bottom navigation, you can explore zodiac signs, read sign details, manage daily habits, and maintain a poster collection. The current project is built around bundled starter content and a local database, so its readings and saved items are handled on the device.

## What you can do

- **Read a horoscope** — see a featured reading on Home, including its message, mood, and lucky number.
- **Explore zodiac signs** — search the sign list and filter it by astrological system.
- **View sign details** — read a sign’s date range, system, element, ruler, and description.
- **Keep daily rituals** — create a habit with a category, mark it complete, see its streak, or remove it.
- **Organize poster ideas** — add a poster title and color palette, mark an entry as downloaded, or delete it.
- **Register or sign in** — use the account screens to enter the app.

The poster gallery stores poster entries and their palette names; it does not generate poster artwork. Journal entries are represented in the data layer, but there is no journal screen in the current navigation.

## Screens and navigation

| Screen | What it contains |
| --- | --- |
| **Home** | Featured horoscope card and a shortcut to Explore |
| **Explore** | Searchable zodiac list, system filters, and spiritual-guidance content |
| **Zodiac Details** | Sign profile with dates, system, element, ruler, and description |
| **Daily Rituals** | Habit creation, completion toggles, streak display, and deletion |
| **Art Poster Gallery** | Poster title and palette entries, download status, and deletion |
| **Login / Registration** | Account entry screens |

## Where to find things

The Android application code lives in `app/src/main/java/com/example/fortune_horoscope/`.

### App and presentation

- `MainActivity.kt` — starts the Compose UI, applies the app theme, and hosts the top and bottom navigation bars.
- `presentation/navigation/NavGraph.kt` — connects each route to its screen and ViewModel.
- `presentation/navigation/Screen.kt` — declares the app’s navigation routes.
- `presentation/navigation/BottomBarNavigation.kt` — defines the main bottom-bar destinations.
- `presentation/ui/screens/` — Compose UI for Home, Explore, Zodiac Details, Daily Rituals, Art Poster Gallery, Login, and Registration.
- `presentation/viewmodel/` — screen state and actions that connect the UI to the data layer.
- `presentation/theme/` — colors, typography, and the visual theme.

### Data and storage

- `data/model/Models.kt` — app-level models for users, zodiac signs, horoscopes, habits, journal entries, and posters.
- `data/model/local/db/FortuneDatabase.kt` — Room database definition.
- `data/local/dao/` and `data/model/local/dao/` — database query interfaces.
- `data/model/local/entity/` — Room entities stored in the database.
- `data/local/util/DatabaseSeeder.kt` — bundled starter zodiac signs, readings, habits, and poster entries.
- `data/repository/` — repository interface and implementation that connect screens and storage.
- `data/di/` — Hilt setup for database and repository dependencies.

### Build configuration and resources

- `app/build.gradle.kts` — Android app settings and dependencies.
- `build.gradle.kts`, `settings.gradle.kts`, and `gradle/libs.versions.toml` — Gradle project and dependency configuration.
- `app/src/main/res/` — app strings, dimensions, drawables, and other Android resources.

## Technology

- Kotlin
- Jetpack Compose and Material 3
- AndroidX Navigation Compose
- Room for on-device persistence
- Kotlin Coroutines and Flow
- Hilt for dependency injection

## Requirements

- Android Studio with Android SDK 36 installed
- JDK 11 or newer
- Android 7.0 (API 24) or newer device or emulator

## Build and run

1. Clone the repository and open it in Android Studio.
2. Let Gradle sync and install any requested Android SDK components.
3. Run the `app` configuration on a device or emulator.

To build a debug APK from the project root:

```bash
./gradlew assembleDebug
```

On Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Current scope

Horoscope and zodiac content is supplied by the app’s bundled starter data and stored locally with Room. The repository does not currently configure a remote horoscope API.