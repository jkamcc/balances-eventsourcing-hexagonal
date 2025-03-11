package org.devengineering.balances.adapters.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;

public class DirectoryDeleter {

    // Method to delete a directory with all its contents
    public static void deleteDirectoryRecursively(Path dir) throws IOException {

        if (!Files.exists(dir)) {
            return;
        }
        // Use Files.walkFileTree to recursively walk the directory
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            // For each file, delete it
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            // For each directory, delete it after its contents have been deleted
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void main(String[] args) {
        Path dirPath = Path.of("path/to/directory"); // Specify the directory to delete

        try {
            deleteDirectoryRecursively(dirPath);
            System.out.println("Directory and its contents deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete directory: " + e.getMessage());
        }
    }
}