# Implementation Tasks - Bloom

## Project Infrastructure
- [ ] Create `project_context.md` in root for persistence [/]
- [ ] Define Package Structure (data, ui, util, viewmodel) [ ]
- [ ] Configure AdConfig (Test IDs) [ ]

## Data & Logic
- [ ] Implement `PreferenceManager` (DataStore) [ ]
- [ ] Implement `MainViewModel` to expose app state [ ]

## Navigation & Splash
- [ ] Create `Screen` sealed class for routing [ ]
- [ ] Implement `SplashScreen` logic [ ]
- [ ] Set up `NavHost` in `MainActivity` [ ]

## UI Implementation (Figma Matching)
- [ ] Setup Theme (Colors, Typeface from Figma) [ ]
- [ ] Build Onboarding Pager Flow [ ]
- [ ] Build Home Screen Layout [ ]

## AdMob Integration
- [ ] Initialize SDK in `MainActivity` [ ]
- [ ] Implement `AppOpenAdManager` [ ]
- [ ] Implement `BannerAdView` Composable [ ]
- [ ] Wire up "Returning User" logic for App Open Ad [ ]

## Verification
- [ ] Test fresh install flow [ ]
- [ ] Test returning user flow [ ]
- [ ] Verify Ad behavior (Background/Foreground) [ ]
