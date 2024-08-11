import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Scanner;

public class fed {
   // Constant defining the shift amount for the Caesar cipher
   private static final int SHIFT = 3;

   // Default constructor
   public fed() {
   }

   // Main method to run the program
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in); // Create a scanner for user input

      // Display options to the user
      System.out.println("Choose an option:");
      System.out.println("1. Encrypt");
      System.out.println("2. Decrypt");

      // Read user choice for encryption or decryption
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      // Read input and output file paths from the user
      System.out.println("Enter the file path:");
      String inputFilePath = scanner.nextLine().replaceAll("^\"|\"$", "");
      System.out.println("Enter the output file path:");
      String outputFilePath = scanner.nextLine().replaceAll("^\"|\"$", "");

      try {
         // Read all bytes from the input file and convert them to a string
         String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

         String result; // Variable to store the processed text

         // Determine whether to encrypt or decrypt based on user choice
         if (choice == 1) {
            // Encrypt the content
            result = encrypt(content, SHIFT);
         } else {
            if (choice != 2) {
               // Invalid choice handling
               System.out.println("Invalid choice.");
               scanner.close();
               return;
            }
            // Decrypt the content
            result = decrypt(content, SHIFT);
         }

         // Write the processed text to the output file
         Files.write(Paths.get(outputFilePath), result.getBytes(), new OpenOption[0]);

         System.out.println("Operation successful. Output saved to: " + outputFilePath);
      } catch (IOException e) {
         // Handle any I/O exceptions
         e.printStackTrace();
      }

    
   }

   // Method to encrypt the text using a Caesar cipher
   private static String encrypt(String text, int shift) {
      return shiftText(text, shift);
   }

   // Method to decrypt the text using a Caesar cipher
   private static String decrypt(String text, int shift) {
      return shiftText(text, -shift);
   }

   // Method to perform the shift operation for both encryption and decryption
   private static String shiftText(String text, int shift) {
      StringBuilder shiftedText = new StringBuilder();
      char[] characters = text.toCharArray();
      int length = characters.length;

      for (int i = 0; i < length; i++) {
         char c = characters[i];
         if (Character.isLetter(c)) {
            int base = Character.isUpperCase(c) ? 65 : 97; // ASCII value for 'A' or 'a'
            shiftedText.append((char)((c - base + shift + 26) % 26 + base));
         } else {
            shiftedText.append(c); // Non-letter characters are appended unchanged
         }
      }

      return shiftedText.toString();
   }
}

