import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FastTest {

    // Method that supplies instances of different classes to the parameterized test
    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new NumberGuessGame()),
                Arguments.of(new GuessingGame())
                );
    }

    // Parameterized test that tests the same method on different classes
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test makeGuess() across different classes")
    void testMakeGuess(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Example test case for all the classes
        double result = guessingGame.makeGuess("example guess");

        // Check the result (this would depend on the actual implementation)
        assertEquals(3.0, result);  // This is just an example
    }
    // Parameterized test that tests the same method on different classes
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test makeGuess() across different classes")
    void testMakeGuess2(GuessingGame guessingGame) throws GuessOutOfRangeException {
        // Example test case for all the classes
        double result = guessingGame.makeGuess("example guess");

        // Check the result (this would depend on the actual implementation)
        assertEquals(3.0, result);  // This is just an example
    }
}
