# IMC Calculator

A clean, modern BMI calculator built with **Jetpack Compose** for Android. Designed as a portfolio project to demonstrate real-world usage of Compose UI, state management, and navigation.

---

## Screenshots

> *(Add your own screenshots here)*

---

## Features

- **Two-screen flow** — input screen and results screen with smooth animated transitions
- **Animated IMC scale** — a color-coded indicator that slides to the user's position on the BMI scale
- **Real-time validation** — handles empty fields and invalid input gracefully using Kotlin null safety
- **Dark mode UI** — gradient backgrounds, rounded inputs, and a vibrant purple-to-pink color palette
- **Classification feedback** — color changes dynamically based on BMI category (underweight, normal, overweight, obese)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Navigation | Navigation Compose |
| State | `remember` + `mutableStateOf` |
| Animations | `animateFloatAsState`, `LaunchedEffect` |
| Architecture | Single-activity, screen-based composables |

---

## Project Structure

```
ui/
  screens/
    FormScreen.kt       # Input screen — weight and height fields
    ResultScreen.kt     # Results screen — BMI value, classification, animated scale
  navigation/
    AppNavigation.kt    # NavHost with slide + fade transitions
MainActivity.kt         # Entry point
```

---

## BMI Classification

| BMI | Classification |
|---|---|
| Below 18.5 | Underweight |
| 18.5 – 24.9 | Normal weight |
| 25.0 – 29.9 | Overweight |
| 30.0 and above | Obese |

---

## What I Learned Building This

This project was my first hands-on dive into Jetpack Compose. A few things that clicked along the way:

- How `remember` and `mutableStateOf` replace the old `EditText.getText()` pattern — the field doesn't hold its own state anymore, you do
- Why `Modifier` is passed as a parameter rather than hardcoded — it keeps composables flexible and reusable
- How `NavHost` works as a route map, and why the `backStackEntry` is needed to extract navigation arguments
- The difference between `toDouble()` and `toDoubleOrNull()` — and why null safety matters in real user input scenarios
- How to chain animations with `animateFloatAsState` and trigger them on screen entry using `LaunchedEffect`

---

## Getting Started

1. Clone the repo
2. Open in Android Studio (Hedgehog or later recommended)
3. Run on an emulator or physical device with API 26+

```bash
git clone https://github.com/ranielschneider/imc-calculator.git
```

---

## Author

**Raniel Schneider**
Android Developer (Kotlin · Jetpack Compose)
[LinkedIn](https://linkedin.com/in/raniel-schneider-79006b50) · [GitHub](https://github.com/ranielschneider)
