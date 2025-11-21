# 🎵 MusicApp Backend API - Developer Guide

## 📘 Project Overview

This is a production-ready backend for a music streaming application. It features secure authentication, role-based
access control (RBAC), complex playlist management, and a playback simulation engine. Built with **Java 21**, **Spring
Boot 3.5**, and **PostgreSQL**.

---

## 🏗️ Architecture

The project follows a strict **Layered Architecture**:

- **Web Layer (`Controllers`)**: Handles HTTP requests and maps them to services.
- **Service Layer**: Contains business logic (Transactions, Validation, Complex operations).
- **Data Layer (`Repositories`)**: Interfaces with PostgreSQL using Spring Data JPA.

---

## 🔌 API Reference

### 🔐 Authentication (`AuthController`)

Base URL: `/auth`
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/register` | Register a new user (Role: USER or ADMIN) |
| `GET` | `/me` | Verify current session & logged-in username |

### 👤 User Management (`UserController`)

Base URL: `/user`
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/profile` | Get full profile details of logged-in user |
| `PUT` | `/password` | Update password (Body: `{"password": "newPass"}`) |
| `DELETE`| `/account` | Permanently delete account & all data |

### 🎵 Songs & Search (`SongController`)

Base URL: `/user/songs`
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | List all songs in the library |
| `GET` | `/{id}` | Get details of a specific song |
| `GET` | `/search?query=...` | Search by Title, Artist, or Genre |

### 📜 Playlists (`PlaylistController`)

Base URL: `/user/playlist`
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | View all my playlists |
| `POST` | `?name=MyJam` | Create a new empty playlist |
| `POST` | `/{pid}/songs/{sid}` | Add a song to a playlist |
| `DELETE`| `/{pid}/songs/{sid}` | Remove a song from a playlist |
| `DELETE`| `/deletePlaylist/{id}`| Delete an entire playlist |

### 🎧 Playback Engine (`PlaybackController`)

Base URL: `/player`
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/play/{songId}` | Start playing a song (Logs history) |
| `POST` | `/pause` | Pause current session |
| `POST` | `/resume` | Resume playback |
| `POST` | `/stop` | Stop playback & reset progress |
| `GET` | `/status` | Check what is currently playing |
| `GET` | `/history` | View listening history (Recently Played) |

### 🛡️ Admin Tools (`AdminController`)

Base URL: `/admin` (Requires `ROLE_ADMIN`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/song` | Add new music to the global library |
| `POST` | `/registerUser` | Manually create a user (Admin override) |
| `PUT` | `/updateUserPassword`| Force update a user's password |
| `GET` | `/users-dump-data` | Export dashboard data for all users |

---

## 🚀 Getting Started

### Prerequisites

- Java 21
- PostgreSQL (running on port 5432)
- Maven

### Setup

1. Clone the repository.
2. Update `application.properties` with your DB credentials.
3. Run `mvn spring-boot:run`.

### Testing with Postman

- **Auth**: Use **Basic Auth**.
- **Credentials**: Use the username/password you registered with.

---

## 🧪 Key Features Explained

### Playback Simulation

Since we don't stream physical audio files yet, we use a **Session-Based** approach:

- **`PlaybackSession`**: Tracks your current status (`PLAYING`, `PAUSED`).
- **`PlaybackHistory`**: Logs every song you listen to.

### Infinite Loop Handling

We use `@JsonIgnore` and `@JsonIgnoreProperties` to handle the bidirectional relationship between `User` and `Playlist`,
ensuring clean JSON responses without recursion errors.
