import java.util.HashSet;
import java.util.Set;

public class GuessingGame {

    int correctNumber = 42;  // Example number
    Set<String> previousGuesses = new HashSet<>();
    boolean gameOver = false;
    int guessCount = 0;
    double score = 0;

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
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

    public void setScore(int numGuess){
        int value = calculatePoints(numGuess);
        score += value;
    }

    /**
     * Calculates points based on the distance between the guess and the correct number.
     * @param numGuess the number guessed
     * @return the points awarded (or deducted)
     */
    public int calculatePoints(int numGuess) {
        int difference = Math.abs(numGuess - correctNumber);

        if (numGuess == correctNumber) {
            return 10;  // Max points for correct guess
        } else if (difference <= 5) {
            if (difference == 1) {
                return 8;  // Very close guess
            } else {
                return 5;  // Close guess
            }
        } else if (difference <= 10) {
            if (numGuess < correctNumber) {
                return 3;  // Close but lower guess
            } else {
                return 2;  // Close but higher guess
            }
        } else {
            return -5;  // Penalty for being too far
        }
    }

    /**
     * Validates the guess input.
     * @param guess the input guess as a string
     * @return true if valid, false otherwise
     */
    public boolean isGuessValid(String guess) {
        try {
            int numGuess = Integer.parseInt(guess);

            if (numGuess >= 1 && numGuess <= 100) {
                return true;
            } else {
                return false;  // Out of range
            }
        } catch (NumberFormatException e) {
            return false;  // Non-numeric input
        }
    }
}
