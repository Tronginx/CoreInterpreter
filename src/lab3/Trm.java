package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<trm> ::= <op> | <op> * <trm>
 *
 * @author Tron Tian
 */
public class Trm {
    private Op op;
    private Trm trm;

    private TokenKind kind;


    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first op
        this.op = new Op();
        this.op.parse();

        //if we have *, keep parsing
        this.kind = t.getToken();
        if (this.kind == TokenKind.MULTIPLY) {
            t.skipToken();
            this.trm = new Trm();
            this.trm.parse();
        }
    }

    public void print() {
        this.op.print();
        if (this.kind == TokenKind.MULTIPLY) {
            System.out.print(" * ");
            this.trm.print();
        }
    }

    public int exec() {
        int value = op.exec();
        if (this.kind == TokenKind.MULTIPLY) {
            value = value * trm.exec();
        }
        return value;
    }
}
