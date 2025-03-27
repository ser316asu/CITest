public class Main {
    public static void main(String[] args) {
        NumberGuessGame game = new NumberGuessGame();

        try {
            // Simulating a few guesses
            System.out.println(game.makeGuess("50"));  // Too high
            System.out.println(game.makeGuess("35"));  // Too high
            System.out.println(game.makeGuess("abc"));  // Non-numeric input
            System.out.println(game.makeGuess("42"));  // Correct guess
            System.out.println(game.makeGuess("105"));  // Should throw GuessOutOfRangeException
        } catch (GuessOutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        // Testing additional methods
        System.out.println("Points for guess 45: " + game.calculatePoints(45));  // Points calculation
        System.out.println("Is '99' a valid guess? " + game.isGuessValid("99"));  // Valid guess check
        System.out.println("Is 'abc' a valid guess? " + game.isGuessValid("abc"));  // Invalid guess check
    }
}