import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExploreHashTable {
    // This is the constant larger than every ord(ci)
    private static final int C = 123;
    // This is the modulus defining the size of our hash table
    private static final int M = 293;

    public static void main(String[] args) {
        // Read the file
        File file = new File("C:\\Users\\lukeg\\IdeaProjects\\HW5\\src\\EdgarAllanPoeBellsB2022groomed.txt");
        try (Scanner scanner = new Scanner(file)) {
            // Create a String array to store the words and their corresponding hash values
            String[] words = new String[M];

            // Create a set to store the unique words
            Set<String> uniqueWords = new HashSet<>();

            while (scanner.hasNext()) {
                // Read the next word
                String word = scanner.next();

                // Only consider words that consist of unbroken sequences of alphabetic characters, plus apostrophes and hyphens
                if (word.matches("^[a-zA-Z'-]+$")) {
                    // Hash the word
                    int h = 0;
                    for (int i = 0; i < word.length(); i++) {
                        // Take ord(c) to be the ASCII value of the characters in the word
                        h = (h * C + (int) word.charAt(i)) % M;
                    }

                    // Check if the hash value is already in the table
                    while (words[h] != null) {
                        // If the word at the current hash value is the same as the one we're trying to add, then we don't need to do anything
                        if (words[h].equals(word)) {
                            break;
                        }
                        // Otherwise, increment the hash value and try the next position in the table
                        h = (h + 1) % M;
                    }

                    // Add the word and its corresponding hash value to the table
                    words[h] = word;

                    // Add the word to the set of unique words
                    uniqueWords.add(word);
                }
            }

            // Calculate and print the load factor of the table
            double loadFactor = (double) uniqueWords.size() / M;
            System.out.println("Non-empty addresses: " + uniqueWords.size());
            System.out.println("Load factor: " + loadFactor);

            // Find the longest empty area in the table
            int maxEmptyArea = 0;
            int startOfMaxEmptyArea = 0;
            int endOfMaxEmptyArea = 0;
            int emptyAreaStart = -1;
            for (int i = 0; i < M; i++) {
                if (words[i] == null) {
                    if (emptyAreaStart == -1) {
                        // Start of an empty area
                        emptyAreaStart = i;
                    }
                } else {
                    if (emptyAreaStart != -1) {
                        // End of an empty area
                        int emptyAreaLength = i - emptyAreaStart;
                        if (emptyAreaLength > maxEmptyArea) {
                            maxEmptyArea = emptyAreaLength;
                            startOfMaxEmptyArea = emptyAreaStart;
                            endOfMaxEmptyArea = i - 1;
                        }
                        emptyAreaStart = -1;
                    }
                }
            }

            // Handle the case where the longest empty area extends to the end of the table
            if (emptyAreaStart != -1) {
                int emptyAreaLength = M - emptyAreaStart;
                if (emptyAreaLength > maxEmptyArea) {
                    maxEmptyArea = emptyAreaLength;
                    startOfMaxEmptyArea = emptyAreaStart;
                    endOfMaxEmptyArea = M;
                }
            }

            // Print the longest empty area
            System.out.println("Longest empty area is " + maxEmptyArea + " indexes starting at index " + startOfMaxEmptyArea + " and ending at index "
                    + endOfMaxEmptyArea);

            // Find the largest cluster in the table
            int maxClusterSize = 0;
            int startOfMaxCluster = 0;
            int endOfMaxCluster = 0;
            int clusterStart = -1;
            for (int i = 0; i < M; i++) {
                if (words[i] != null) {
                    if (clusterStart == -1) {
                        // Start of a cluster
                        clusterStart = i;
                    }
                } else {
                    if (clusterStart != -1) {
                        // End of a cluster
                        int clusterSize = i - clusterStart;
                        if (clusterSize > maxClusterSize) {
                            maxClusterSize = clusterSize;
                            startOfMaxCluster = clusterStart;
                            endOfMaxCluster = i - 1;
                        }
                        clusterStart = -1;
                    }
                }
            }

            // Handle the case where the largest cluster extends to the end of the table
            if (clusterStart != -1) {
                int clusterSize = M - clusterStart;
                if (clusterSize > maxClusterSize) {
                    maxClusterSize = clusterSize;
                    startOfMaxCluster = clusterStart;
                    endOfMaxCluster = M;
                }
            }

            // Print the largest cluster
            System.out.println("Largest cluster is " + maxClusterSize + " indexes starting at index " + startOfMaxCluster + " and ending at index " + endOfMaxCluster);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}