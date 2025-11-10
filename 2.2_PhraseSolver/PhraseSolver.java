import java.util.Scanner;

public class PhraseSolver {

    // Instance variables
    private Player player1;
    private Player player2;
    private Board gameBoard;
    private boolean solved;

    // Constructor
    public PhraseSolver() {
        // Create two players and a board
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameBoard = new Board("HELLO WORLD"); // You can change this phrase
        solved = false;
    }

    // Main game loop
    public void play() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Phrase Solver Game!");
        System.out.println("Try to guess the phrase one letter at a time.\n");

        Player currentPlayer = player1;

        // Keep playing until the phrase is solved
        while (!gameBoard.isSolved()) {
            System.out.println("Current phrase: " + gameBoard.getHiddenPhrase());
            System.out.println(currentPlayer.getName() + "'s turn. Score: " + currentPlayer.getScore());
            System.out.print("Guess a letter or type 'solve' to guess the phrase: ");
            String guess = input.nextLine().toUpperCase();

            // If player wants to solve the phrase
            if (guess.equals("SOLVE")) {
                System.out.print("Enter your full phrase guess: ");
                String fullGuess = input.nextLine().toUpperCase();

                if (fullGuess.equals(gameBoard.getPhrase())) {
                    System.out.println("Correct! You solved the phrase!");
                    currentPlayer.addPoints(10); // Bonus points for solving
                    solved = true;
                    break;
                } else {
                    System.out.println("Incorrect guess. You lose your turn.");
                }
            }
            // If player guesses a single letter
            else if (guess.length() == 1) {
                char letter = guess.charAt(0);
                boolean correct = gameBoard.guessLetter(letter);

                if (correct) {
                    System.out.println("Good guess! Letter revealed.");
                    currentPlayer.addPoints(2); // Add small points for each correct guess
                } else {
                    System.out.println("Letter not in phrase.");
                }
            } else {
                System.out.println("Please enter only one letter or 'solve'.");
            }

            // Switch turns between players
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            System.out.println();
        }

        // Game over
        input.close();
        System.out.println("The phrase was: " + gameBoard.getPhrase());
        System.out.println("🎉 Game over! Final scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());

        // Display winner
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Good job!" + player1.getName() + " wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Good job! " + player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Accessor method
    public boolean isSolved() {
        return solved;
    }
}