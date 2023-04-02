package lab3;


/**
 * {@code Reporter} utility class with methods to report various kinds of
 * notifications.
 */
public final class Reporter {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Reporter() {
        // no code needed here
    }

    /*
     * Public members ---------------------------------------------------------
     */

    /**
     * Prints the given error message to the console and terminates the
     * application.
     *
     * @param msg
     *            the error message
     * @ensures <pre>
     * {@code [msg is printed to the console and the application terminates]}
     * </pre>
     */
    public static void fatalErrorToConsole(String msg) {
        assert msg != null : "Violation of: msg is not null";
        throw new RuntimeException(msg);
    }

    /**
     * If the given condition is false, prints the given error message to the
     * console and terminates the application; otherwise it just returns.
     *
     * @param b
     *            the boolean condition
     * @param msg
     *            the error message
     * @ensures <pre>
     * {@code [if the condition is false, msg is printed to the console and
     *   the application terminates; otherwise it just returns]}
     * </pre>
     */
    public static void assertElseFatalError(boolean b, String msg) {
        assert msg != null : "Violation of: msg is not null";
        if (!b) {
            fatalErrorToConsole(msg);
        }
    }

}