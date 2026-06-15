# Snake &amp; Ladder on SwiftBot

A first-year university robotics project that implements the classic Snake and Ladder board game on the SwiftBot platform. The game features a two-player mode (human vs. SwiftBot) with physical robot movement on a 5&times;5 grid, QR code–based player registration, randomised snake and ladder placement, and persistent game logging.

## Table of Contents

- [Overview](#overview)
- [The SwiftBot Platform](#the-swiftbot-platform)
- [Project Structure](#project-structure)
- [How to Play](#how-to-play)
- [Logging](#logging)
- [Development Notes](#development-notes)

## Overview

The game is written entirely in **Java** and runs on the **SwiftBot** — a Raspberry Pi–based Linux device equipped with motors, sensors, a camera, and other hardware peripherals. A human player interacts through the SwiftBot's physical buttons and QR camera, while the SwiftBot opponent moves autonomously across a custom-printed 5&times;5 board.

## The SwiftBot Platform

**SwiftBot** is a robotic platform built on a Raspberry Pi, running **SwiftBot OS** — a customised Debian-based operating system optimised for controlling hardware peripherals (wheels, sensors, camera, underlights, etc.).

The **SwiftBot API** is a Java library that interfaces with SwiftBot OS to provide high-level access to the robot's hardware. The API is distributed through a university-managed Maven repository, accessible only on campus.

## Project Structure

```
SnakeNladderSwiftbot/
|-- snake-n-ladder/           # Maven project root
|   |-- pom.xml               # Build configuration &amp; API dependency
|   |-- src/main/java/
|       |-- SnakeNladder.java      # Main game loop &amp; UI logic
|       |-- Sharing.java           # Shared API instance &amp; utility methods
|       |-- SwiftBotMovement.java  # Robot movement control
|-- README.md
```

The project was originally created in **Eclipse IDE for Java Developers** and later reorganised into a cleaner Maven structure when migrating to **VS Code**.

## How to Play

1. **Start** — Press the **Y** button to begin.
2. **Register** — Present a QR code containing your name (max. 10 characters) to the SwiftBot's camera.
3. **Turn Order** — Press **B** to roll dice and determine who goes first.
4. **Play** — Press **A** to roll the dice on your turn. The SwiftBot moves autonomously.
5. **Snakes &amp; Ladders** — Landing at the bottom of a ladder advances you; landing on a snake head sends you back.
6. **Win** — The first player to reach square **25** wins.
7. **Restart / Quit** — Press **A** to restart or **X** to exit (logs are saved automatically).

## Logging

On exit (button **X**), the game writes a log file to `/Logfile.txt` containing:

- Final positions of both players
- Timestamp
- Snake and ladder positions used during the session

## Development Notes

- **IDE Migration:** Originally developed in Eclipse, the project was migrated to VS Code with a restructured directory layout.
- **Main Class:** `SnakeNladder` — configured via the `exec-maven-plugin` in `pom.xml`.
