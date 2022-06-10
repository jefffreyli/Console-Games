import java.util.Scanner;

public class Hangman {
   private static String output = "";
   private static int computer = 0;
   private static int guessesLeft = 3;
   private static String status = "y";

   public static void main(String[] args) {
      while (status.equals("y")) {
         output = "";
         System.out.println("====================================================================");
         computer = (int) (Math.random() * 5 + 3);

         System.out.println("We will be playing hang man. You have 3 guesses. ");
         Scanner sc = new Scanner(System.in);
         String three = "cat";
         String four = "fish";
         String five = "shark";
         String six = "parrot";
         String seven = "gorilla";

         if (computer == 3) {
            Hangman(three, 3);
         } else if (computer == 4) {
            Hangman(four, 4);
         } else if (computer == 5) {
            Hangman(five, 5);
         } else if (computer == 6) {
            Hangman(six, 6);
         } else if (computer == 7) {
            Hangman(seven, 7);
         }
      }
   }

   // ==================================================================================================================
   // ==================================================================================================================
   // ==================================================================================================================

   public static void Hangman(String word, int num) {
      Scanner sc = new Scanner(System.in);
      guessesLeft = 3;
      output = "";
      for (int i = 1; i <= num; i++) { // populate output with underscores
         output += "_ ";
      }

      int randLetterIndex = (int) (Math.random() * num);

      printResult(word.substring(randLetterIndex, randLetterIndex + 1), randLetterIndex * 2, word);

      if (num > 4) {
         randLetterIndex = (int) (Math.random() * num);
         printResult(word.substring(randLetterIndex, randLetterIndex + 1), randLetterIndex * 2, word);
      }

      for (int i = 1; i <= 3; i++) {
         System.out.println("Guesses Left: " + guessesLeft);
         System.out.print("Guess: "); // user starts guessing
         String guess1 = sc.nextLine();
         System.out.println("---------------------------------------");
         guessesLeft -= 1;
         if (guess1.equals(word)) {
            System.out.println("You guessed the word!");
            System.out.println("Do you want to play again (y or n)?");
            status = sc.nextLine();
            break;
         }
         if (contains(word, guess1) == false) {
            System.out.println("Hint: The word is an animal!");
         }
         // System.out.println(guess1);
         // System.out.println(three.indexOf(guess1)*2);
         printResult(guess1, word.indexOf(guess1) * 2, word);

         String check = output.replace(" ", "");
         if (check.equals(word)) {
            System.out.println("Congratulations! Type 'y' to play again and 'n' to quit: ");
            status = sc.nextLine();
            if (status.equals("n")) {
               break;
            }
         }
         if (check.equals(word) == false && guessesLeft == 0) {
            System.out.println("The word was " + word);
            System.out.println("Do you want to play again (y or n)?");
            status = sc.nextLine();
            if (status.equals("n")) {
               break;
            }
         }
      }
   }

   public static void printResult(String guess, int guessIndex, String word) {
      // _ _ _
      if (contains(word, guess)) {
         output = output.substring(0, guessIndex) + guess + output.substring(guessIndex + 1);
      }
      System.out.println(output);
   }

   public static boolean contains(String str, String guess) {
      for (int i = 0; i <= str.length() - 1; i++) {
         if (str.substring(i, i + 1).equals(guess)) {
            return true;
         }
      }
      return false;
   }
}
