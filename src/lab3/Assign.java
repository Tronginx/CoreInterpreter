package lab3;

/**
 *parse, print and execute following BNF grammar:
 * <assign> ::=<id> = <exp>;
 *
 * @author Tron Tian
 */
public class Assign {

    private Id id;

    private Exp exp;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first id
        this.id =  Id.parse();

        //check if there's a "="
        Reporter.assertElseFatalError(t.getToken() == TokenKind.ASSIGNMENT,"Expected \"=\".");
        t.skipToken(); // skip =

        //parse exp
        this.exp = new Exp();
        this.exp.parse();

        //check the semicolon at the end
        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,"Expected \";\".");
        t.skipToken(); // skip semicolon
    }

    public void print() {
        //print id
        this.id.print();
        //print =
        System.out.print(" = ");
        //print exp
        this.exp.print();
        //print ;
        System.out.print(";");
    }

    public void exec() {
        //evaluate value of exp
        int value = this.exp.exec();
        //assign value of that exp to id
        this.id.setVal(value);
    }
}
