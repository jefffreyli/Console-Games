import java.util.*;
import java.io.*;

class Wordle {
    public static void main(String[] args) throws IOException {
        System.out.println("\nAdapted from the New York Times (https://www.nytimes.com/games/wordle/index.html) \n");
        System.out.println("Guess the WORDLE in six tries.\n");
        System.out.println("Each guess must be a valid five-letter word. Hit the enter button to submit.\n");
        System.out.println(
                "After each guess, the color of the tiles will change to show how close your guess was to the word. \n");
        System.out.println("----------------------------------------------------\n");
        System.out.println("Examples: \n");
        System.out.println("W E A R Y");
        System.out.println("🟩⬜️⬜️⬜️⬜️");
        System.out.println("The letter W is in the word and in the correct spot. \n");
        System.out.println("P I L L S");
        System.out.println("⬜️🟨⬜️⬜️⬜️");
        System.out.println("The letter I is in the word but in the wrong spot. \n");
        System.out.println("V A G U E");
        System.out.println("⬜️⬜️⬜️⬜️⬜️");
        System.out.println("The letter U is not in the word in any spot.");
        System.out.println("----------------------------------------------------\n");
        System.out.println("A new WORDLE will be available each day!\n\n");
        System.out.println("----------------------------------------------------");

        BufferedReader br = new BufferedReader(new FileReader("Wordle.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        str = str.replace("\"", "");
        str = str.replace(",", " ");
        String[] words = str.split(" ");
        int randInt = (int) (Math.random() * words.length);
        String target = words[randInt];

        String[] guesses = { "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _" };
        String[] colors = { "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _", "_ _ _ _ _" };
        int numGuesses = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println(target);
        while (numGuesses < 6) {
            int len = 0;
            String wordGuessed = "";
            while (len != 5) {
                System.out.print("Enter a five-lettered word: ");
                wordGuessed = sc.nextLine();
                System.out.println();
                len = wordGuessed.length();
            }

            String temp = "";
            for (int i = 0; i < 5; i++) {
                temp += wordGuessed.substring(i, i + 1) + " ";
            }

            guesses[numGuesses] = temp;
            colors[numGuesses] = wordColors(wordGuessed, target);

            for (int i = 0; i < 6; i++) {
                System.out.println("Guess: " + guesses[i]);
                System.out.println("Hints: " + colors[i]);
                System.out.println("-----------------------------");
            }

            if (colors[numGuesses].equals("🟩🟩🟩🟩🟩")) {
                System.out.println("You guessed the WORDLE in " + (numGuesses + 1));
                break;
            }
            numGuesses++;
            // hi
        }

        if (numGuesses == 6) {
            System.out.println("The wordle was: " + target);
        }
    }

    public static String wordColors(String guess, String tg) {
        String[] colors = new String[5];
        String temp = tg;

        for (int i = 0; i < 5; i++) {
            if (guess.substring(i, i + 1).toLowerCase().equals(tg.substring(i, i + 1))) {
                colors[i] = "🟩";
                temp = temp.substring(0, i) + "-" + temp.substring(i + 1);
            }
        }

        for (int i = 0; i < 5; i++) {
            if (contains(guess.substring(i, i + 1), temp) && colors[i] == null) {
                colors[i] = "🟨";
                for (int j = 0; j < 5; j++) {
                    if (temp.substring(j, j + 1).equals(guess.substring(i, i + 1))) {
                        temp = temp.substring(0, j) + "-" + temp.substring(j + 1);
                        break;
                    }
                }
            }
        }

        String c = "";
        for (int i = 0; i < 5; i++) {
            if (colors[i] == null) {
                c += "⬜️";
            } else
                c += colors[i];
        }
        return c;
    }

    public static boolean contains(String letter, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).toLowerCase().equals(letter)) {
                return true;
            }
        }
        return false;
    }
}

