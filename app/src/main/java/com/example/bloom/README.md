# Bloom App


## Libraries Used
- Jetpack Compose
- MVVM
- Hilt
- DataStore
- Navigation

## Architecture
The project follows a clean MVVM-based architecture with UI, ViewModel, data, and utility layers.

### Folder Structure
```text
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/bloom/
│   │   │   ├── data/
│   │   │   ├── di/
│   │   │   ├── ui/
│   │   │   │   ├── components/
│   │   │   │   ├── navigation/
│   │   │   │   ├── screens/
│   │   │   │   └── theme/
│   │   │   ├── util/
│   │   │   └── viewmodel/
│   │   └── res/
│   │       ├── drawable/
│   │       ├── values/
│   │       └── xml/
│   └── AndroidManifest.xml
├── build.gradle.kts
└── proguard-rules.pro
```

## Features Implemented
- Splash screen
- Onboarding flow
- Local onboarding state persistence with DataStore
- Home screen UI
- Navigation between screens
- App Open Ad support
- Banner ad support
- Hilt dependency injection setup
- Lifecycle-aware ad handling



## Notes
- AdMob test IDs are used during development.
- The app is designed to avoid showing App Open Ads on the first launch before onboarding is completed.
