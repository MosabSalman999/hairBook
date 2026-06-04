# PRD.md — Hair Book · Product Requirements Document

## Overview

Hair Book is a premium Android app that serves as a digital encyclopaedia of hairstyles for men and women. Users browse a curated catalogue of styles, explore detailed information for each style, and use a visual questionnaire to discover the styles best suited to their unique features. The app targets the general public and is available in Arabic, English, and German.

---

## Problem Statement

Most people choose a hairstyle by scrolling through Instagram or describing something vague to their hairdresser. There is no structured, offline-capable reference that combines style discovery, personalised recommendations, and professional product guidance in one place. Hair Book fills that gap.

---

## User Roles

### Guest
- Can browse the full catalogue (men and women sections)
- Can view hairstyle detail pages
- Can use the style finder questionnaire
- Cannot save favourites
- Cannot access admin panel

### Registered User
- Everything a Guest can do
- Can save and manage a personal favourites list
- Has a profile with display name and email
- Can switch app language from settings

### Admin
- Everything a Registered User can do
- Can add new hairstyles (fill all fields + upload images)
- Can edit any hairstyle field
- Can delete hairstyles
- Can reorder catalogue items
- Identified by role = "admin" in the local database

---

## Screens & Features

### 1. Splash / Onboarding
- App logo animation on launch
- Language selection on first launch (AR / EN / DE)
- "Continue as guest" or "Sign in / Register" options

### 2. Home Screen (Category Discovery)
- Gender tabs at top: Men | Women
- 2-column staggered grid of category folder cards (e.g. Fade, Undercut, Bob, Curls)
- Each card: cover image with gradient overlay, category name in gold, style count badge
- Tapping a category folder navigates to the Category Screen
- Navigation bar at the bottom: Browse · Finder · Favourites · Profile

### 2b. Category Screen
- Displays all hairstyles belonging to the tapped category
- Back button in top bar returns to Home Screen
- Same masonry (Pinterest-style) grid of hairstyle cards as the classic browse view
- Tapping a card navigates to Detail Screen

### 3. Browse Screen (Men / Women)
- Masonry (Pinterest-style) grid of hairstyle cards
- Each card: hero image + style name + one key tag (e.g. "Curly · Medium")
- Filter bar at the top: filter by face shape, hair texture, length, thickness, colour
- Search bar (searches by style name)
- Tapping a card navigates to Detail Screen

### 4. Hairstyle Detail Screen
- Full-width hero image with swipeable gallery (multiple angles)
- Style name and category (men / women)
- Attribute chips: face shape suitability, texture, length, thickness, colour
- Sections:
  - "About this style" — description paragraph
  - "Time & effort" — estimated time + difficulty level (1–5 stars)
  - "Recommended products" — list of product names with type (shampoo, wax, etc.)
  - "More angles" — horizontal scrollable image row
- Floating action button: Save to Favourites (heart icon; filled if already saved)
- Guest users tapping FAB → prompted to register

### 5. Style Finder (Questionnaire)
- Visual step-by-step wizard, one question per screen
- Progress indicator at top (e.g. Step 3 of 6)
- Each answer is a tappable image card — user taps their answer, moves to next step
- Questions and answer options:
  1. Gender / look → Men | Women
  2. Face shape → Oval | Round | Square | Heart | Diamond | Oblong (illustrated silhouettes)
  3. Hair texture → Straight | Wavy | Curly | Coily (photo examples)
  4. Hair length → Short | Medium | Long (photo examples)
  5. Hair thickness → Fine | Medium | Thick (illustrated cross-sections)
  6. Hair colour → Black | Brown | Blonde | Red | Grey | Coloured (colour swatches)
- Results screen: grid of matching styles with match percentage, sorted by best match
- Tapping a result card → Detail Screen

### 6. Favourites Screen
- Grid of saved hairstyle cards (same card component as Browse)
- Empty state: illustrated prompt to explore the catalogue
- Swipe to remove from favourites
- Guests see a "Sign in to save styles" prompt instead

### 7. Auth Screens
- **Login:** Email + password fields, "Sign in with Google" button, "Forgot password" link, link to Register
- **Register:** Display name, email, password, confirm password, "Sign up with Google"
- **Forgot password:** Email field, sends Firebase reset email
- All auth screens share the same dark/gold visual language as the rest of the app

### 8. Profile Screen
- Display name and email (editable)
- Language selector (AR / EN / DE)
- "Sign out" button
- App version number
- Link to booking placeholder screen
- Admin users see an "Admin Panel" entry here

### 9. Admin Panel
- Accessible only to admin role
- List of all hairstyles with edit / delete actions
- "Add new style" button → form with all fields + image picker (local images in v1)
- Reorder via drag handles

### 10. Booking Placeholder Screen
- Headline: "Coming Soon — Book with a Hairdresser"
- Descriptive paragraph explaining the upcoming feature
- Illustration or lottie animation
- "Notify me" button (stores email locally, no backend in v1)

---

## Hairstyle Data Model

Each hairstyle entry contains:

| Field | Type | Description |
|---|---|---|
| id | String (UUID) | Unique identifier |
| nameEn | String | Style name in English |
| nameAr | String | Style name in Arabic |
| nameDe | String | Style name in German |
| gender | Enum: MEN / WOMEN | Target gender |
| heroImage | String (asset path) | Primary image |
| gallery | List<String> | Additional angle images |
| descriptionEn | String | About paragraph (EN) |
| descriptionAr | String | About paragraph (AR) |
| descriptionDe | String | About paragraph (DE) |
| faceShapes | List<FaceShape> | Suitable face shapes |
| texture | List<HairTexture> | Suitable textures |
| lengths | List<HairLength> | Suitable lengths |
| thickness | List<HairThickness> | Suitable thickness |
| colours | List<HairColour> | Suitable colours |
| timeMinutes | Int | Estimated styling time |
| difficulty | Int (1–5) | Effort level |
| products | List<Product> | Recommended products |
| category | String | Style category for grouping (e.g. "fade", "bob", "undercut") |
| sortOrder | Int | Display order in catalogue |

---

## Non-Functional Requirements

- **Offline-first:** All content available without internet after first launch (Firebase Auth is the only online dependency)
- **RTL support:** Full Arabic RTL layout mirroring throughout
- **Performance:** Catalogue grid must scroll at 60fps on a mid-range device (Pixel 4a equivalent)
- **Accessibility:** Minimum touch target 48dp, content descriptions on all images, sufficient colour contrast (WCAG AA)
- **App size:** Under 50MB APK for v1 (images stored as compressed assets)

---

## Out of Scope for v1

- Real hairdresser booking (registration, calendar, payments)
- Social sharing of styles
- Push notifications
- User-uploaded photos
- iOS version
- Backend CMS (admin edits are local-only in v1)
