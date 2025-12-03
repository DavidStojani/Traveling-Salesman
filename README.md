# Traveling Salesman Visualizer

An interactive JavaFX application for exploring heuristic solutions to the Traveling Salesman Problem (TSP). The tool builds a complete weighted graph from a set of points, computes a Delaunay triangulation, and lets you compare several approximation and improvement steps (random, MST-based, Christofides, 2-opt, triangulation clean-up, and k-opt) while watching the tour evolve.

## Features
- Load a TSP instance from a tab-separated text file and immediately generate its triangulation and visualization. 【F:TSP/src/main/java/tsp/main/MainController.java†L43-L67】
- Choose an initial tour via random shuffle, MST-based 2-approximation, or Christofides 1.5-approximation. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L86-L115】
- Apply improvement steps: remove crossings with a 2-opt pass, synchronize the tour with Delaunay edges to avoid crossings, and run a k-opt search on a derived graph. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L96-L115】【F:TSP/src/main/java/tsp/main/MainController.java†L121-L155】
- Toggle visual layers (tour, MST, triangulation, or higher-order Delaunay edges) and re-center the canvas. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L55-L163】
- Save the current instance or tour back to disk for later use. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L70-L84】【F:TSP/src/main/java/tsp/main/MainController.java†L162-L216】

## Project Structure
- `TSP/src/main/java/tsp/main/` – JavaFX UI, controllers, and graph/tour orchestration.
- `TSP/src/main/java/delaunayTriang/` and `TSP/src/main/java/triang/` – Delaunay triangulation utilities and order calculations used to prune edges. 【F:TSP/src/main/java/tsp/main/Instance.java†L8-L134】
- `TSP/src/main/java/KOpt/` – K-opt solver components.
- `TSP/examples/` – Sample point sets for quick experiments.

## Prerequisites
- JDK 11+ with JavaFX 11 modules (managed automatically by Gradle via the OpenJFX plugin). 【F:TSP/build.gradle†L1-L32】
- Gradle is bundled via the wrapper, so no separate installation is required.

## Running the Application
1. Navigate to the project folder and start the app:
   ```bash
   cd TSP
   ./gradlew run
   ```
2. When the window opens, choose a point file via the file chooser dialog. 【F:TSP/src/main/java/tsp/main/main.java†L13-L32】
3. Use the **Algorithm** menu to pick an initial tour, remove crossings, sync with the triangulation, and run k-opt. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L86-L115】
4. Use the **View** menu to toggle overlays or center the canvas, and the **File** menu to save the instance or the current tour. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L55-L163】【F:TSP/src/main/java/tsp/main/ButtonBox.java†L70-L84】

## Input Format
Instance files are plain text with one point per line, using tab or space separators:
```
<id> <x> <y>
<id> <x> <y>
...
```
This format is parsed into `Vertex` objects before building the weighted graph. 【F:TSP/src/main/java/tsp/main/FileReader.java†L9-L47】

## How It Works
- The application constructs a complete weighted graph from the loaded points, tagging edges that belong to the tour or the Delaunay triangulation so they can be shown/hidden efficiently. 【F:TSP/src/main/java/tsp/main/Instance.java†L21-L133】
- Approximate tours can be initialized with JGraphT’s 2-approximation (MST) or Christofides algorithms, then improved with custom 2-opt and k-opt steps that respect triangulation structure. 【F:TSP/src/main/java/tsp/main/MainController.java†L103-L155】
- Masked subgraphs allow the UI to render the tour and triangulation independently without duplicating graph data. 【F:TSP/src/main/java/tsp/main/Instance.java†L21-L134】

## Saving Results
- **Save Instance** writes the current point set (id, x, y) to a text file.
- **Save Tour** writes the tour as an ordered list of coordinates.
Both options are available from the File menu and use the built-in file chooser for destination selection. 【F:TSP/src/main/java/tsp/main/ButtonBox.java†L70-L84】【F:TSP/src/main/java/tsp/main/MainController.java†L162-L216】

## Licensing
No explicit license information is provided in this repository.
