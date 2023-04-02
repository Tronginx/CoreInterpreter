package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<comp> ::= (<op> <comp op> <op>)
 *
 * @author Tron Tian
 */
public class Comp {
    private Op op1;
    private Op op2;
    private CompOp compOp;





    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //check if there's ( and skip it
        Reporter.assertElseFatalError(t.getToken() == TokenKind.LEFT_PARENTHESIS,"Expected \"(\".");
        t.skipToken(); // skip (

        //parse first op
        this.op1 = new Op();
        this.op1.parse();

        //parse comp op in the middle
        this.compOp = new CompOp();
        this.compOp.parse();

        //parse the last op
        this.op2 = new Op();
        this.op2.parse();

        //check if there's ) and skip it
        Reporter.assertElseFatalError(t.getToken() == TokenKind.RIGHT_PARENTHESIS,"Expected \")\".");
        t.skipToken(); //skip )
    }

    public void print() {
        //print (
        System.out.print("(");

        //print the first op
        this.op1.print();
        //print comp op in the middle
        this.compOp.print();
        //print the last op
        this.op2.print();

        //print the last ) with a space
        System.out.print(") ");
    }

    public boolean exec() {
        //evaluate value of first op
        int value1 = this.op1.exec();
        //evaluate value of second op
        int value2 = this.op2.exec();

        //return evaluation of two values
        return this.compOp.exec(value1,value2);
    }
}
