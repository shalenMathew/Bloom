# Project Context: Bloom App

## Overview
Bloom is a women's wellness/PCOS assignment app.
**Goal:** High-fidelity UI from Figma, MVVM architecture, and specific AdMob requirements.

## Current Progress (as of today)
1.  **Dependencies:** Added Navigation, DataStore, AdMob, and Lifecycle dependencies.
2.  **Infrastructure:** Implementation plan and task list created in `.artifacts`.
3.  **Core Rule:** App Open Ads MUST NOT show on first launch (Onboarding flow).

## Tech Stack
*   Kotlin / Jetpack Compose
*   MVVM Architecture
*   DataStore Preferences
*   Google AdMob (Test IDs)

## Key Requirements & Gotchas
*   **Onboarding:** Accurate Figma recreation. Skip/Next/Continue buttons logic.
*   **Splash Logic:** Splash -> Onboarding (First time) OR Splash -> App Open Ad -> Home (Returning).
*   **AdMob:** Adaptive Banners on Home. App Open Ad must reload after use and handle backgrounding.

## TODO / Next Steps
*   Setup Figma design tokens (Colors, Typography).
*   Implement DataStore `PreferenceManager`.
*   Create Navigation Graph.
*   Build Onboarding Screens.

---
*This file is maintained for context persistence across different sessions/agents.*
