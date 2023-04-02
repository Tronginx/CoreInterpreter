package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<no>::=<digit> | <digit><no>
 *
 * @author Tron Tian
 */
public class No {
    private int num;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.INTEGER_CONSTANT,"Expected \"integer\".");

        //store current token's int value to this.num
        this.num = t.intVal();
        t.skipToken();
    }
    public void print() {
        System.out.print(this.num + "");
    }

    public int exec() {
        return this.num;
    }
}
