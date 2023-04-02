package lab3;
/**
 * Token kinds needed for Part 1 of the Core Interpreter project.
 *
 * @author Wayne D. Heym
 *
 */
public enum TokenKind {

    /**
     * Reserved words
     */
    PROGRAM(1),
    BEGIN(2),
    END(3),
    INT(4),
    IF(5),
    THEN(6),
    ELSE(7),
    WHILE(8),
    LOOP(9),
    READ(10),
    WRITE(11),


    /**
     * Special symbols
     */
    SEMICOLON(12),
    COMMA(13),
    ASSIGNMENT(14),
    EXCLAIM(15),
    LEFT_BRACKET(16),
    RIGHT_BRACKET(17),
    AND_OP(18),
    OR_OP(19),
    LEFT_PARENTHESIS(20),
    RIGHT_PARENTHESIS(21),
    PLUS(22),
    MINUS(23),
    MULTIPLY(24),
    NOT_EQ(25),
    IS_EQ(26),
    LESS_THAN(27),
    GREATER_THAN(28),
    LESS_EQ_THAN(29),
    GREATER_EQ_THAN(30),




    /**
     * Test driver's token number = 31.
     */
    INTEGER_CONSTANT(31),

    /**
     * Test driver's token number = 32.
     */
    IDENTIFIER(32),

    /**
     * Test driver's token number = 33.
     */
    EOF(33),


    /**
     * Test driver's token number = 34.
     */
    ERROR(34);




    private final int testDriverTokenNumber;

    /**
     * Constructor. (As class TokenKind is an enum, the visibility of the
     * explicit constructor is automatically private (i.e., private by default).
     * The default visibility for it is not package visibility.)
     *
     * @param number
     *            the test driver's token number
     */
    TokenKind(int number) {
        this.testDriverTokenNumber = number;
    }

    /**
     * Return test driver's token number.
     *
     * @return test driver's token number
     */
    public int testDriverTokenNumber() {
        return this.testDriverTokenNumber;
    }
}
