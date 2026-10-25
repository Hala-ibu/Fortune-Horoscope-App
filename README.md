# Fortune Horoscope App

Fortune Horoscope is an Android app for exploring horoscope readings and zodiac information, keeping personal daily rituals, and organizing zodiac-inspired poster ideas.

## Features

- **Daily horoscope home** — view a featured reading with its message, mood, title, and lucky number.
- **Explore horoscopes** — browse the horoscope collection.
- **Zodiac details** — see a sign’s date range, astrological system, element, ruler, and description.
- **Daily rituals** — create habits with categories, mark them complete, track streaks, and delete them.
- **Art poster gallery** — create poster entries with a title and palette, mark them downloaded, and delete them.
- **Account screens** — register and sign in to the app.
- **Local persistence** — app data is stored on-device with Room.

## Tech stack

- Kotlin and Jetpack Compose (Material 3)
- AndroidX Navigation Compose
- Room
- Hilt dependency injection
- Kotlin Coroutines and Flow

## Requirements

- Android Studio with Android SDK 36 available
- JDK 11 or newer
- Android 7.0 (API 24) or newer device/emulator

## Build and run

1. Clone this repository and open it in Android Studio.
2. Allow Gradle to sync and install the required Android SDK components.
3. Run the `app` configuration on an emulator or connected Android device.

To build a debug APK from the repository root:

```bash
./gradlew assembleDebug
```

On Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Project structure

- `app/src/main/java/com/example/fortune_horoscope/presentation` — Compose screens, navigation, and view models
- `app/src/main/java/com/example/fortune_horoscope/data` — data models, Room database, repositories, and dependency injection

## Notes

The app currently uses a local Room database. Horoscope and zodiac content comes from the app’s bundled starter data; the project does not currently describe a remote horoscope service.
