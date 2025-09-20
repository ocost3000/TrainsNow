# Project Guidelines

Project name: TrainsNow

Overview:
- This project is a Kotlin Multiplatform app that provides realtime updates for transit system routes.
- The app will compile into multiple region-specific variants depending on the transit systems in those regions.
- Code should be organized to support potential future Wear OS and watchOS experiences, even though watch targets may not be available in Kotlin Multiplatform yet.

Architecture and platforms:
- Primary module: composeApp (shared UI/business logic with Compose Multiplatform)
- Current targets: Android, Web (Wasm and JS)
- Planned: Keep platform boundaries clean to allow adding more targets later (e.g., iOS/desktop/wearables).

Regions (planned releases):
- New York City (MTA/NYCTA)
- San Francisco Bay Area (BART, Caltrain, Muni, SamTrans, AC Transit)
- Boston (MBTA)
- Chicago (CTA)

Asynchronous programming:
- Use Kotlin coroutines and Flow for all asynchronous operations and reactive data streams.
- Never use runBlocking (including in tests). Prefer coroutine scopes, suspend functions, and test utilities like runTest when needed.

Project structure:
- composeApp/src/commonMain – shared, cross-platform Kotlin code (Compose UI, domain, shared logic)
- composeApp/src/androidMain – Android-specific code and resources
- composeApp/src/webMain – Web-specific entry point and resources
- composeApp/src/jsMain and composeApp/src/wasmJsMain – platform-specific Kotlin for web targets
- api/transiter – API-related resources (e.g., docker-compose, HTTP request examples)
- gradle/libs.versions.toml – central dependency and plugin versions

Build and run:
- Android (Debug):
  - macOS/Linux: ./gradlew :composeApp:assembleDebug
  - Windows: .\\gradlew.bat :composeApp:assembleDebug
- Web (Development):
  - Wasm target (modern browsers):
    - macOS/Linux: ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
    - Windows: .\\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
  - JS target (broader compatibility):
    - macOS/Linux: ./gradlew :composeApp:jsBrowserDevelopmentRun
    - Windows: .\\gradlew.bat :composeApp:jsBrowserDevelopmentRun

Quality and style:
- Static analysis: Detekt is applied to the project, with ktlint formatting rules via the detekt-formatting plugin (sourced from the version catalog).
- Kotlin style: Follow idiomatic Kotlin. Keep functions small and testable. Prefer immutable data structures and pure functions where practical.
- Concurrency: Prefer structured concurrency with clearly scoped coroutine contexts; avoid global scope.

Testing:
- Place shared tests under composeApp/src/commonTest.
- Use kotlinx.coroutines test utilities for coroutine code. Avoid runBlocking.

Notes for contributors (Junie):
- Keep changes minimal and targeted to the issue at hand.
- Align new dependencies with gradle/libs.versions.toml (version catalog).
- If you add new region-specific logic, isolate it behind interfaces in commonMain with platform-specific implementations as needed.
