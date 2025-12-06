# 📚 Quilter -- OpenLibrary Book List App

![Kotlin](https://img.shields.io/badge/Kotlin-2.x-blue?logo=kotlin)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-blue?logo=jetpackcompose)
![Hilt](https://img.shields.io/badge/Hilt-Dependency%20Injection-orange)
![Retrofit](https://img.shields.io/badge/Retrofit-Networking-green)
![Coroutines](https://img.shields.io/badge/Coroutines-Flow-red)
![OpenLibrary](https://img.shields.io/badge/API-OpenLibrary-yellow)

A modern Android application built with **Jetpack Compose**, following
**MVVM + Clean Architecture**, powered by **Kotlin Coroutines & Flow**,
**Hilt for DI**, and **Retrofit + Moshi + OkHttp** for networking.

This project fetches categorised personal reading logs from the
**OpenLibrary API** and presents them in a clean, reactive UI with a
**bottom-sheet detail view**.

------------------------------------------------------------------------

## 🚀 Key Features

### ✅ Categorised Book Lists

-   Want To Read
-   Currently Reading
-   Already Read

### ✅ Bottom Sheet Book Details

On item selection: - Book cover image - Title - Author - First published
year - Logged date

### ✅ Modern Compose UI (Material 3)

-   Declarative, stateless UI
-   Reactive state via StateFlow
-   Clean component-based design
-   Smooth transitions & layout

### ✅ MVVM + Clean Architecture

-   Feature-based modular structure
-   Clear separation of concerns
-   Unidirectional data flow
-   Domain-driven use cases

### ✅ Safe & Robust Networking

-   Retrofit + Moshi (Kotlin adapters)
-   OkHttp with logging interceptor
-   Centralised `safeApiCall` error handling
-   Timeout & network error handling

### ✅ Reactive State Management

-   Kotlin Coroutines & Flow
-   StateFlow for UI state
-   Sealed UiState handling:
    -   Loading
    -   Success
    -   Error

### ✅ Test-Ready Setup

Infrastructure prepared for: - Repository tests - UseCase tests -
ViewModel tests - API tests with MockWebServer

------------------------------------------------------------------------

## 📂 Project Structure

**Feature-Based + Clean Architecture**

    app/
    └── src/main/java/subin/openlibrary/booklist/
        ├── core/
        │   ├── di/                   # Hilt modules (NetworkModule, RepositoryModule)
        │   ├── network/              # SafeApiCall, Retrofit helpers
        │   └── utils/                # CoverImageUtils, extensions
        │
        └── feature_book_list/
            ├── data/
            │   ├── remote/
            │   │   ├── api/          # Retrofit API interface
            │   │   ├── dto/          # OpenLibrary response DTOs
            │   │   └── mapper/       # DTO → Domain mappers
            │   ├── datasource/       # BookRemoteDataSource
            │   └── repository/       # BookRepositoryImpl
            │
            ├── domain/
            │   ├── model/            # Book, BookListTab
            │   ├── repository/       # BookRepository (interface)
            │   └── usecase/          # GetBookListUseCase
            │
            └── presentation/
                ├── screen/           # BookListScreen, BookListContent
                ├── viewmodel/        # BookListViewModel
                ├── state/            # BookListUiState (sealed)
                └── components/       # BookCard, Tabs, BottomSheet UI

------------------------------------------------------------------------

## 🏛 Architecture Overview

### 🌿 Clean Architecture Layers

### **Presentation Layer**

-   Jetpack Compose UI
-   MVVM pattern
-   `BookListViewModel`
-   `StateFlow<BookListUiState>`
-   Bottom sheet interaction
-   Stateless UI components

### **Domain Layer**

-   Pure Kotlin models (`Book`, `BookListTab`)
-   Business rules (`GetBookListUseCase`)
-   Repository contracts (`BookRepository`)
-   No Android or networking dependency

### **Data Layer**

-   Retrofit + Moshi + OkHttp
-   API → DTO → Domain mapping
-   Remote data source abstraction
-   Repository implementation
-   Safe API call wrapper

```text
                📱 Presentation Layer
          +------------------------------+
          | Jetpack Compose UI           |
          | - BookListScreen / BookCard  |
          |   / BottomSheet              |
          |                              |
          | BookListViewModel            |
          | - exposes StateFlow<         |
          |     BookListUiState >        |
          +------------------------------+
                         ▲
                         │
                     observes
                         │
                         ▼
                  🧠 Domain Layer
          +------------------------------+
          | Use Cases                    |
          | - GetBookListUseCase         |
          |                              |
          | Repository Contracts         |
          | - BookRepository             |
          |                              |
          | Pure Models                  |
          | - Book, BookListTab          |
          +------------------------------+
                         ▲
                         │
                       calls
                         │
                         ▼
                    🌐 Data Layer
          +------------------------------+
          | Repository Implementation    |
          | - BookRepositoryImpl         |
          |   → Flow<Resource<List<Book>>>|
          |                              |
          | Remote Data Source           |
          | - BookRemoteDataSource       |
          |                              |
          | Network Stack                |
          | - Retrofit + Moshi + OkHttp  |
          | - SafeApiCall (centralised   |
          |   error handling & mapping   |
          |   to Resource)               |
          | - DTOs + Mappers → Domain    |
          |   Models                     |
          +------------------------------+
```
------------------------------------------------------------------------

### 🔁 Unidirectional Data Flow (UDF)

    UI (Compose)
       ↓ observes
    ViewModel (StateFlow)
       ↓ executes
    UseCase (Business Logic)
       ↓ calls
    Repository Interface (Domain)
       ↓ implemented by
    RepositoryImpl (Data)
       ↓ fetches
    Retrofit API + DTO + Mapper

------------------------------------------------------------------------

### 🧠 State Management Strategy

#### Data Layer → `Resource<T>`

-   `Success`
-   `Error`

#### Presentation Layer → `BookListUiState`

-   `Loading`
-   `Success`
-   `Error`

This ensures: - Domain stays UI-agnostic
- UI controls loading & rendering
- Clear separation of data and presentation state

------------------------------------------------------------------------

##£ 🧩 Bottom Sheet Interaction

-   Item click updates `selectedBook` in ViewModel
-   `ModalBottomSheet` automatically reacts via StateFlow
-   Bottom sheet dismissed by:
    -   Swipe
    -   Close button
-   Fully lifecycle safe

------------------------------------------------------------------------

### 🌐 API Used

    https://openlibrary.org/people/mekBot/books/{category}.json

Categories: - `want-to-read` - `currently-reading` - `already-read`

------------------------------------------------------------------------

### 🛠 Tech Stack

-   **Language:** Kotlin
-   **UI:** Jetpack Compose + Material 3
-   **Architecture:** MVVM + Clean Architecture
-   **Async:** Kotlin Coroutines + Flow
-   **DI:** Hilt
-   **Networking:** Retrofit + Moshi + OkHttp
-   **Image Loading:** Coil
-   **Testing:** JUnit, Mockito, Coroutines Test, MockWebServer

------------------------------------------------------------------------

### 📸 Screenshots
<p float="left">
  <img src="screenshots/book-list.png.png" width="200"/>
  <img src="screenshots/book-list-details.png.png" width="200"/>
</p>

------------------------------------------------------------------------

### 🧪 Testing Strategy

-   DTO → Domain mapping tests
-   Repository tests with a fake data source
-   UseCase tests with mocked repository
-   ViewModel tests with coroutines test dispatcher
-   API parsing tests using MockWebServer

------------------------------------------------------------------------

### ✅ Why This Project Stands Out

-   Proper Clean Architecture
-   Real-world API integration
-   Safe error handling
-   Lifecycle-aware state management
-   Bottom sheet with business-driven UI state

------------------------------------------------------------------------

### 🙌 Author

**Subin Babu**
Senior Android Developer
Cambridge, UK

GitHub: https://github.com/subinbabusamueldeveloper

------------------------------------------------------------------------