
package lumi;

/**
 * Represents a custom exception specific to the Lumi application.
 */
public class LumiException extends Exception {

    /**
     * Creates a LumiException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public LumiException(String message) {

        super(message);
    }
}

