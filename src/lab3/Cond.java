package lab3;
/**
 *parse, print and execute following BNF grammar:
 * <cond> ::= <comp>|!<cond> | [<cond> && <cond>] | [<cond> or <cond>]
 *
 * @author Tron Tian
 */
public class Cond {
    private Comp comp;
    private Cond cond1;
    private Cond cond2;

    private TokenKind kind1;
    private TokenKind kind2;


    public void parse() {
        Tokenizer t = Tokenizer1.instance();
        this.kind1 = t.getToken();

        //get into the second choice
        if (this.kind1 == TokenKind.NOT_EQ ||  this.kind1 == TokenKind.LEFT_BRACKET) {

            t.skipToken(); // skip [

            //parse first cond
            this.cond1 = new Cond();
            this.cond1.parse();
            //get second comp op kind
            this.kind2 = t.getToken();

            //get into the second and third choice
            if (this.kind2  == TokenKind.AND_OP || this.kind2  == TokenKind.OR_OP) {
                t.skipToken(); // skip && / or
                this.cond2 = new Cond();
                this.cond2.parse();
                Reporter.assertElseFatalError(t.getToken() == TokenKind.RIGHT_BRACKET,"Expected \"]\".");
                t.skipToken(); // skip )
            }
        } else { //the first choice
            this.comp = new Comp();
            this.comp.parse();
        }
    }

    public void print() {
        //same logic as parse
        if (this.kind1 == TokenKind.LEFT_PARENTHESIS) {
            this.comp.print();
        } else if (this.kind1  == TokenKind.LEFT_BRACKET) {
            System.out.print("[");
            this.cond1.print();

            if (this.kind2 == TokenKind.AND_OP) {
                System.out.print(" && ");
            } else if (this.kind2 == TokenKind.OR_OP){
                System.out.print(" or ");
            }

            this.cond2.print();
            System.out.print("]");
        } else if (this.kind1 == TokenKind.NOT_EQ){
            System.out.print("!");
            this.cond1.print();
        }

    }

    public boolean exec() {
        if (this.kind1 == TokenKind.LEFT_PARENTHESIS) {
            return this.comp.exec();
        } else if (this.kind1 == TokenKind.LEFT_BRACKET) {
            boolean c1 = this.cond1.exec();
            if (this.kind2 == TokenKind.AND_OP) {
                return c1 && this.cond2.exec();
            } else {
                return c1 || this.cond2.exec();
            }
        } else {
            return !this.cond1.exec();
        }
    }
}