import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashWords {
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
                }
            }

            // Print the words in order of their hash values, from least to greatest
            for (int i = 0; i < M; i++) {
                String word = words[i];
                if (word != null) {
                    // Print the hash address, the word, and the hash value
                    System.out.print(i + " " + word + " ");
                    System.out.println(i);
                } else {
                    // Print the hash address and the value null if there's no word at that position in the table
                    System.out.println(i + " null");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}