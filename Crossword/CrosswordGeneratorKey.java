import java.util.*;

public class CrosswordGeneratorKey {
   // private static String[] words = new String[5];
   private static String[] hints = new String[5];
   private static String[] sameLetters = new String[100];
   private static int[] xPosition = new int[100];
   private static int[] yPosition = new int[100];
   private static String[][] grid = new String[40][40];
   private static boolean[] wordPlaced = new boolean[5];
   private static String[] wordsLeft = new String[5];
   private static String[] orientation = new String[5];

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String[] words = new String[5];
      // user input theme and words
      System.out.print("Enter a theme: ");
      String theme = sc.nextLine();

      for (int i = 0; i < words.length; i++) {
         System.out.print("Enter word " + (i + 1) + " : ");
         words[i] = sc.nextLine().toLowerCase();
         System.out.print("Enter hint for word " + (i + 1) + " : ");
         hints[i] = sc.nextLine();
      }

      int maxLen = 0;
      int maxIndex = 0;
      for (int i = 0; i < words.length; i++) {
         if (words[i].length() > maxLen) {
            maxLen = words[i].length();
            maxIndex = i;
         }
      }
      // put maxLen word into index 0
      String temp = words[0];
      words[0] = words[maxIndex];
      words[maxIndex] = temp;

      // reorder hints array
      temp = hints[0];
      hints[0] = hints[maxIndex];
      hints[maxIndex] = temp;

      int secondMaxLen = 0;
      int secondIndex = 0;
      for (int i = 0; i < words.length; i++) {
         if (words[i].length() != maxLen && words[i].length() > secondMaxLen) {
            secondMaxLen = words[i].length();
            secondIndex = i;
         }
      }

      temp = words[1];
      words[1] = words[secondIndex];
      words[secondIndex] = temp;

      temp = hints[1];
      hints[1] = hints[secondIndex];
      hints[secondIndex] = temp;

      // System.out.println(words[0]);
      // System.out.println(words[1]);
      makeCrossword(words, theme);

   }

   // =========================Helper Methods==================================\\
   // =========================Helper Methods==================================\\
   // =========================Helper Methods==================================\\
   // =========================Helper Methods==================================\\
   // =========================Helper Methods==================================\\
   // =========================Helper Methods==================================\\

   public static void print2DArray(String[][] arr) {
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr[i].length; j++) {
            System.out.print(arr[i][j] + " ");
         }
         System.out.println();
      }
   }

   public static void reset1DArrayInt(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
         arr[i] = 0;
      }
   }

   public static void reset1DArrayStr(String[] arr) {
      for (int i = 0; i < arr.length; i++) {
         arr[i] = "";
      }
   }

   public static void findSameLetters(String word, String[][] arr) {
      int index = 0;
      for (int i = 0; i < word.length(); i++) {
         for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[i].length; k++) {
               if (word.substring(i, i + 1).equals(arr[j][k])) {
                  sameLetters[index] = word.substring(i, i + 1);
                  xPosition[index] = j;
                  yPosition[index] = k;
                  index++;
               }
            }
         }
      }
   }

   public static void placeVertically(String word, int wordNumber, int rowStart, int rowInter, int columnStart) {
      if (hasSpaceVertically(rowStart, rowStart + word.length(), rowInter, columnStart) == false) {
         return;
      }
      int index = 0;
      for (int i = rowStart; i < rowStart + word.length(); i++) {
         if (index < word.length()) {
            grid[i][columnStart] = word.substring(index, index + 1);
            index++;
            wordPlaced[wordNumber] = true;
            orientation[wordNumber] = "down";
         }
      }
      grid[rowStart - 1][columnStart] = wordNumber + 1 + "";
   }

   public static boolean hasSpaceVertically(int rowStart, int rowEnd, int rowInter, int column) {
      if (rowStart == rowInter && grid[rowStart - 1][column].equals("-")) {
         rowStart += 1;
      }
      for (int i = rowStart; i < rowEnd; i++) {
         if (i + 1 == rowInter && grid[i][column].equals("-")) {
            i += 2;
         }
         if (grid[i][column].equals("-") == false || grid[i][column - 1].equals("-") == false
               || grid[i][column + 1].equals("-") == false || grid[i + 1][column].equals("-") == false) {
            return false;
         }
      }
      return true;
   }

   public static void placeHorizontally(String word, int wordNumber, int rowStart, int columnInter, int columnStart) {
      if (hasSpaceHorizontally(columnStart, columnStart + word.length(), columnInter, rowStart) == false) {
         return;
      }
      int index = 0;
      for (int i = columnStart; i < columnStart + word.length(); i++) {
         if (index < word.length()) {
            grid[rowStart][i] = word.substring(index, index + 1);
            index++;
            wordPlaced[wordNumber] = true;
            orientation[wordNumber] = "across";
         }
      }
      grid[rowStart][columnStart - 1] = wordNumber + 1 + "";
   }

   public static boolean hasSpaceHorizontally(int columnStart, int columnEnd, int columnInter, int row) {
      if (columnStart == columnInter && grid[row][columnStart - 1].equals("-")) {
         columnStart += 1;
      }
      for (int i = columnStart; i < columnEnd; i++) {
         if (i + 1 == columnInter && grid[row - 1][i].equals("-") && grid[row + 1][i].equals("-")
               && grid[row][i - 1].equals("-")) {
            i += 2;
         }
         if (grid[row][i].equals("-") == false || grid[row + 1][i].equals("-") == false
               || grid[row - 1][i].equals("-") == false || grid[row][i + 1].equals("-") == false
               || grid[row][i + 1].equals("-") == false) {
            return false;
         }
      }
      return true;
   }

   public static boolean allPlaced(boolean[] w) {
      for (int i = 0; i < w.length; i++) {
         if (w[i] == false)
            return false;
      }
      return true;
   }

   public static void replaceLettersWithSpace(String[][] arr) {
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr.length; j++) {
            if (grid[i][j].equals("-"))
               grid[i][j] = " ";
         }
      }
   }

   public static boolean isInt(String str) {
      for (int i = 1; i <= 5; i++) {
         if (str.equals("" + i)) {
            return true;
         }
      }
      return false;
   }

   public static void makeCrossword(String[] words, String theme) {
      // initialize orientation array
      for (int i = 0; i < orientation.length; i++) {
         orientation[i] = "";
      }

      // make grid

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            grid[i][j] = "-";
         }
      }

      // place first word in the middle
      int counter = 0;
      for (int i = 23; i < 25 + words[0].length(); i++) {
         for (int j = 15; j < 15 + words[0].length(); j++) {
            if (counter < words[0].length()) {
               grid[i][j] = words[0].substring(counter, counter + 1);
               counter++;
            }
         }
      }
      orientation[0] = "across";
      wordPlaced[0] = true;
      grid[23][15 - 1] = 1 + "";

      // initialize sameLettters array
      for (int i = 0; i < sameLetters.length; i++) {
         sameLetters[i] = "";
      }

      // CROSSWORD CROSSWORD CROSSWORD
      int numIterations = 0;
      boolean allPlaced = false;
      while (allPlaced == false && numIterations < 6) {

         for (int i = 1; i < 5; i++) {
            findSameLetters(words[i], grid);
            int filled = sameLetters.length;
            for (int j = 0; j < sameLetters.length; j++) {
               if (sameLetters[j].equals("")) {
                  filled--;
               }
            }
            int rowStart, rowInter, columnStart;

            for (int j = 0; j < filled; j++) {
               if (wordPlaced[i])
                  break;
               rowStart = xPosition[j] - words[i].indexOf(sameLetters[j]);
               columnStart = yPosition[j];
               rowInter = xPosition[j];

               if (rowStart >= 0 && rowInter >= 0 && columnStart >= 0)
                  placeVertically(words[i], i, rowStart, rowInter, columnStart);
            }
            reset1DArrayInt(xPosition);
            reset1DArrayInt(yPosition);
            reset1DArrayStr(sameLetters);
         }

         int wordsLeftIndex = 0;
         for (int i = 0; i < 5; i++) {
            if (wordPlaced[i] == false) {
               wordsLeft[wordsLeftIndex] = words[i];
               wordsLeftIndex++;
            }
         }

         int wordsLeftFilled = words.length;
         for (int i = 0; i < wordsLeft.length; i++) {
            if (wordsLeft[i] == null) {
               wordsLeftFilled--;
            }
         }
         int[] wordsLeftPrevIndex = new int[wordsLeftFilled];
         int tempIndex = 0;
         for (int i = 0; i < wordPlaced.length; i++) {
            if (wordPlaced[i] == false) {
               if (tempIndex < wordsLeftFilled) {
                  wordsLeftPrevIndex[tempIndex] = i;
                  tempIndex++;
               }
            }
         }

         // HORIZONTALLY HORIZONTALLY HORIZONTALLY HORIZONTALLY HORIZONTALLY HORIZONTALLY
         for (int i = 0; i < wordsLeftFilled; i++) {
            reset1DArrayInt(xPosition);
            reset1DArrayInt(yPosition);
            reset1DArrayStr(sameLetters);
            findSameLetters(wordsLeft[i], grid);
            int filled = sameLetters.length;

            // finds how many elements are in sameLetters array
            for (int j = 0; j < sameLetters.length; j++) {
               if (sameLetters[j].equals("")) {
                  filled--;
               }
            }
            for (int j = 0; j < filled; j++) {
               if (wordPlaced[wordsLeftPrevIndex[i]])
                  break;
               int rowStart, columnInter, columnStart;
               columnStart = yPosition[j] - wordsLeft[i].indexOf(sameLetters[j]);
               rowStart = xPosition[j];
               columnInter = yPosition[j];

               if (rowStart >= 0 && columnInter >= 0 && columnStart >= 0) {
                  placeHorizontally(wordsLeft[i], wordsLeftPrevIndex[i], rowStart, columnInter, columnStart);
               }
            }
         }
         allPlaced = allPlaced(wordPlaced);
         numIterations++;
      }
      System.out.println("-------------------------------------");
      System.out.println();
      System.out.println("Theme: " + theme);
      replaceLettersWithSpace(grid);
      print2DArray(grid);
      for (int i = 0; i < wordPlaced.length; i++) {
         if (wordPlaced[i] == false) {
            System.out.println("Sorry! This word couldn't be placed: " + words[i]);
         }
      }
      System.out.println("-------------------------------------");
      System.out.println("Across: ");
      System.out.println();

      for (int i = 0; i < orientation.length; i++) {
         if (orientation[i].equals("across") && wordPlaced[i] == true) {
            System.out.println("" + (i + 1) + ".  " + hints[i]);
         }
      }
      System.out.println("-------------------------------------");
      System.out.println("Down: ");
      System.out.println();

      for (int i = 0; i < orientation.length; i++) {
         if (orientation[i].equals("down") && wordPlaced[i] == true) {
            System.out.println("" + (i + 1) + ".  " + hints[i]);
         }
      }
   }
}
