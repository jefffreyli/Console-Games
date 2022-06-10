import java.util.*;

public class Connect4 {
   private static int[] columnFilled = new int[7];

   public static void main(String[] args) {
      System.out.println("\nWelcome to Connect 4!\n");

      boolean valid = false;
      String player1 = "";
      String player2 = "";

      Scanner sc = new Scanner(System.in);
      while (!valid) {
         System.out.print("Player 1, X or O: ");
         player1 = sc.nextLine().substring(0, 1).toUpperCase();
         if (player1.equals("X") || player1.equals("O")) {
            valid = true;
         }
      }

      if (player1.equals("X")) {
         player2 = "O";
      } else {
         player2 = "X";
      }

      System.out.println("\nHere is the board:\n");
      String[][] board = new String[6][7];
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            board[i][j] = " ";
         }
      }

      display(board);

      while (!isWin(board, player1, player2)) {
         System.out.println("\nPlayer 1's turn.\n");
         boolean validMove = false;
         int col = -1;
         while (!validMove) {
            System.out.print("Enter a valid column, Player 1: ");
            col = Integer.parseInt(sc.nextLine().substring(0, 1));
            if (col <= 6 && col >= 0 && columnFilled[col] <= 5) {
               validMove = true;
            }
         }

         updateBoard(board, col, player1);
         System.out.println();
         display(board);
         if (isWin(board, player1, player2)) {
            System.out.println("line 53");
            break;
         }

         // player 2 moves
         System.out.println("\nPlayer 2's turn.\n");
         validMove = false;
         col = -1;
         while (!validMove) {
            System.out.print("Enter a valid column, Player 2: ");
            col = Integer.parseInt(sc.nextLine().substring(0, 1));
            if (col <= 6 && col >= 0 && columnFilled[col] <= 5) {
               validMove = true;
            }
         }

         updateBoard(board, col, player2);
         System.out.println();
         display(board);
         if (isWin(board, player1, player2)) {
            System.out.println("line 72");
            break;
         }
      }

   }

   ////////////////////////////////////////////////////////////////////////////////////////////////

   public static boolean isWin(String[][] board, String player1, String player2) {
      // horizontal win
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length - 4; j++) {
            if (!board[i][j].equals(" ") && board[i][j].equals(board[i][j + 1])
                  && board[i][j + 1].equals(board[i][j + 2]) && board[i][j + 2].equals(board[i][j + 3])) {
               if (board[i][j].equals(player1)) {
                  System.out.println("Player 1 wins!");
               } else {
                  System.out.println("Player 2 wins!");
               }
               System.out.println("Game ended.");
               return true;
            }
         }
      }

      // vertical win
      for (int i = board.length - 1; i >= 2; i--) {
         for (int j = 0; j < board[i].length; j++) {
            if (!board[i][j].equals(" ") && board[i][j].equals(board[i - 1][j])
                  && board[i - 1][j].equals(board[i - 2][j]) && board[i - 2][j].equals(board[i - 3][j])) {
               if (board[i][j].equals(player1)) {
                  System.out.println("Player 1 wins!");
               } else {
                  System.out.println("Player 2 wins!");
               }
               System.out.println("Game ended.");
               return true;
            }
         }
      }

      // diagonal left-right win

      for (int i = 0; i < board.length - 3; i++) {
         for (int j = 0; j < board[i].length - 3; j++) {
            if (!board[i][j].equals(" ") && board[i][j].equals(board[i + 1][j + 1])
                  && board[i + 1][j + 1].equals(board[i + 2][j + 2])
                  && board[i + 2][j + 2].equals(board[i + 3][j + 3])) {
               if (board[i][j].equals(player1)) {
                  System.out.println("Player 1 wins!");
               } else {
                  System.out.println("Player 2 wins!");
               }
               System.out.println("Game ended.");
               return true;
            }
         }
      }

      // diagonal right-left win
      for (int i = 0; i < board.length - 3; i++) {
         for (int j = board[i].length - 1; j >= 3; j--) {
            if (!board[i][j].equals(" ") && board[i][j].equals(board[i + 1][j - 1])
                  && board[i + 1][j - 1].equals(board[i + 2][j - 2])
                  && board[i + 2][j - 2].equals(board[i + 3][j - 3])) {
               if (board[i][j].equals(player1)) {
                  System.out.println("Player 1 wins!");
               } else {
                  System.out.println("Player 2 wins!");
               }
               System.out.println("Game ended.");
               return true;
            }
         }
      }

      return false;
   }

   public static void updateBoard(String[][] board, int c, String l) {
      int row = 0;
      while (row < board.length && board[row][c].equals(" ")) {
         row++;
      }
      board[row - 1][c] = l;
      columnFilled[c]++;
   }

   public static void display(String[][] board) {
      System.out.println("  0   1   2   3   4   5   6");
      System.out.println(" -----------------------------");
      for (int i = 0; i < board.length; i++) {
         System.out.print(" | ");
         for (int j = 0; j < board[i].length; j++) {
            // board[i][j] = " ";
            System.out.print(board[i][j] + " | ");
         }
         System.out.println();
         System.out.println("  ----------------------------");
      }
   }

}