package lab3;

/**
 * parse, print and execute following BNF grammar:
 * <exp> ::= <trm>|<trm>+<exp>|<trm>−<exp>
 *
 * @author Tron Tian
 */

public class Exp {
    private Trm trm;
    private Exp exp;
    private TokenKind kind;


    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first trm
        this.trm = new Trm();
        this.trm.parse();

        //parse with given token kind
        this.kind = t.getToken();
        if (this.kind == TokenKind.PLUS ||  this.kind == TokenKind.MINUS) {
            t.skipToken();
            this.exp = new Exp();
            this.exp.parse();
        }
    }

    public void print() {
        this.trm.print();
        if (this.kind == TokenKind.PLUS) {
            System.out.print(" + ");
            this.exp.print();
        } else if (this.kind == TokenKind.MINUS) {
            System.out.print(" - ");
            this.exp.print();
        }
    }

    public int exec() {
        int value = this.trm.exec();

        if (this.kind == TokenKind.PLUS) {
            value += exp.exec();
        } else if(this.kind == TokenKind.MINUS) {
            value -= exp.exec();
        }
        return value;
    }
}
