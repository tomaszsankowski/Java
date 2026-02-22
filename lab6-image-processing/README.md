# Lab 6: Image Processing

This mini-project demonstrates advanced multi-threading and parallel processing in Java using `ForkJoinPool` and Java Streams for image manipulation.

## Features

- **Parallel Processing:** Utilizes `ForkJoinPool` to process multiple images concurrently, improving performance.
- **Java Streams:** Uses Java 8 Streams to process collections of files efficiently.
- **Image Manipulation:** Reads images from an input directory, applies transformations (e.g., resizing, filtering), and saves them to an output directory.

## How to Run

1. Open the project in your IDE.
2. Ensure you have input images in the `src/tmp/input/` directory (or provide the correct path as an argument).
3. Run the `Main` class located in `src/main/java/main/Main.java`.
4. Provide two command-line arguments:
   - `args[0]`: Path to the input directory containing images.
   - `args[1]`: Path to the output directory where processed images will be saved.
5. The application will process the images in parallel and save the results to the output directory.

## Technologies

- Java
- Maven
- Java Concurrency (`java.util.concurrent`)
- Java Image I/O (`javax.imageio`)
