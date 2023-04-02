package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<op> ::= <no> | <id> | (<exp>)
 *
 * @author Tron Tian
 */
public class Op {
    private No no;
    private Id id;
    private Exp exp;

    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //check it current token is no
        this.kind = t.getToken();
        if (this.kind == TokenKind.INTEGER_CONSTANT) { // parse no
            this.no = new No();
            this.no.parse();
        } else if (this.kind == TokenKind.IDENTIFIER) { // parse id
            this.id = Id.parse();
        } else if(this.kind == TokenKind.LEFT_PARENTHESIS){ // parse exp
            t.skipToken(); // skip (

            this.exp = new Exp();
            this.exp.parse();

            Reporter.assertElseFatalError(t.getToken() == TokenKind.RIGHT_PARENTHESIS,"Expected \")\".");

            t.skipToken(); // skip )
        } else {
            Reporter.fatalErrorToConsole("Incorrect operator");
        }
    }

    public void print() {

        if (this.kind == TokenKind.INTEGER_CONSTANT) {
            this.no.print();
        } else if(this.kind == TokenKind.IDENTIFIER) {
            this.id.print();
        } else {
            System.out.print("(");
            this.exp.print();
            System.out.print(")");
        }



    }

    public int exec() {
        if (this.kind == TokenKind.INTEGER_CONSTANT) {
            return this.no.exec();
        } else if(this.kind == TokenKind.IDENTIFIER) {
            return this.id.getVal();
        } else {
            return this.exp.exec();
        }
    }
}
