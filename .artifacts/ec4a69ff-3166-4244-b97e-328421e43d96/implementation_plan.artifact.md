# Implementation Plan - Bloom Women's Wellness App

Building a PCOS wellness app with a focus on a high-fidelity onboarding experience, AdMob integration, and a clean MVVM architecture.

## User Review Required

> [!IMPORTANT]
> **AdMob Logic:** As per the detailed requirements, the **App Open Ad will NOT be shown on the very first launch**. It will only appear for returning users (after onboarding is complete and the app is restarted/brought to foreground).

> [!NOTE]
> **Figma Assets:** I will need the specific font names, colors, and image assets from the Figma to ensure the "as accurately as possible" requirement is met. I'll start with placeholders if they aren't available yet.

## Proposed Changes

I will organize the project into a Clean MVVM structure to ensure maintainability.

---

### 1. Data Layer (Persistence)
Implement DataStore to track onboarding and ad-related states.

#### [NEW] [PreferenceManager.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/data/PreferenceManager.kt)
*   Manages `isOnboardingCompleted` flag.
*   Provides a Flow for the UI/Navigation to react to.

---

### 2. Navigation Layer
Define the flow between Splash, Onboarding, and Home.

#### [NEW] [NavGraph.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/navigation/NavGraph.kt)
*   Routes: `Splash`, `Onboarding`, `Home`.
*   Handles conditional start destination based on DataStore.

---

### 3. UI Layer (Screens & Components)
Recreate Figma screens using Compose.

#### [NEW] [OnboardingScreen.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/screens/onboarding/OnboardingScreen.kt)
*   HorizontalPager for onboarding steps.
*   Logic for Skip/Next/Get Started.

#### [NEW] [HomeScreen.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/screens/home/HomeScreen.kt)
*   Responsive layout with scrollable content.
*   Container for the Banner Ad.

#### [NEW] [AdComponents.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/components/AdComponents.kt)
*   Reusable `BannerAdView` Composable.

---

### 4. AdMob Logic (Lifecycle & Management)
Robust handling of App Open Ads.

#### [NEW] [AppOpenAdManager.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/util/AppOpenAdManager.kt)
*   Handles loading, showing, and reloading of ads.
*   Check `PreferenceManager` to ensure it doesn't show during first-launch onboarding.
*   Integrates with `ProcessLifecycleOwner`.

#### [MODIFY] [MainActivity.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/MainActivity.kt)
*   Initialize MobileAds SDK.
*   Set up NavHost and AppOpenAdManager.

---

### 5. Utilities & Theme
Fonts, Colors, and Constants.

#### [MODIFY] [Color.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/theme/Color.kt)
#### [MODIFY] [Type.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/java/com/example/bloom/ui/theme/Type.kt)
*   Update with Figma-specific design tokens.

#### [NEW] [AdConfig.kt](file:///E:/2. Coding (Android)/Intern Ass/Bllom%20app%20-%20New%20age/app/src/main/util/AdConfig.kt)
*   Centralized location for Test Ad IDs.

## Verification Plan

### Automated Tests
*   **Unit Tests:** Verify `PreferenceManager` correctly toggles and remembers the onboarding state.
*   **Navigation Tests:** Ensure Splash correctly redirects based on `isOnboardingCompleted`.

### Manual Verification
1.  **First Launch:** Confirm Splash -> Onboarding -> Home (No App Open Ad).
2.  **Returning User:** Force close app, reopen. Confirm Splash -> App Open Ad -> Home.
3.  **AdMob Interactions:** Verify Banner Ad is adaptive and doesn't overlap Home content.
4.  **Offline Handling:** Turn off internet, ensure app still functions and ads fail gracefully.
