public class NumberGuessGame extends GuessingGame {

    public NumberGuessGame() {
        super();
    }

    /**
     * Makes a guess and returns a code based on the outcome.
     * - 0: Correct guess
     * - 1.x: Too high, x is how far off the guess was
     * - 2.x: Too low, x is how far off the guess was
     * - 3.0: Non-numeric input
     * - 4.0: Guess was already made
     * - 5.0: Game over after 10 incorrect guesses
     * - 6.0: Guess made after game is over
     *
     * @param guess the user's guess as a string
     * @return a double indicating the outcome of the guess
     * @throws GuessOutOfRangeException if the guess is outside the allowed range (1-100)
     */
    public double makeGuess(String guess) throws GuessOutOfRangeException {
        // Check if the game is already over
        if (gameOver) {
            return 6.0;  // Guess made after the game is over
        }

        // End the game after 10 guesses
        if (guessCount >= 10) {
            gameOver = true;
            return 5.0;  // Game over
        }

        // Check if the input is a valid number
        try {
            int numGuess = Integer.parseInt(guess);

            // Throw exception if the guess is out of range
            if (numGuess < 1 || numGuess > 100) {
                throw new GuessOutOfRangeException("Guess must be between 1 and 100");
            }

            // Check if the guess was already made
            if (previousGuesses.contains(guess)) {
                score -= 2;
                return 4.0;  // Guess was already made
            }

            // Add guess to the list
            previousGuesses.add(guess);
            guessCount++;

            // Check if the guess is correct
            if (numGuess == correctNumber) {
                gameOver = true;
                return 0.0;  // Correct guess
            }

            // Check if too high or too low
            if (numGuess > correctNumber) {
                score -= (numGuess - correctNumber);
                return 1.0 + (numGuess - correctNumber) / 100.0;  // Too high, return how far off
            } else {
                score -= (correctNumber - numGuess);
                return 2.0 + (correctNumber - numGuess) / 100.0;  // Too low, return how far off
            }

        } catch (NumberFormatException e) {
            score -= 3;
            return 3.0;  // Non-numeric input
        }

    }
}
