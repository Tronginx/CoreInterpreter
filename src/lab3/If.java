package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<if> ::= if <cond> then <stmt seq> end; |if <cond> then <stmt seq> else <stmt seq> end;
 *
 * @author Tron Tian
 */

public class If {
    private Cond cond;
    private StmtSeq stmtSeq1;
    private StmtSeq stmtSeq2;

    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first cond
        this.cond = new Cond();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.IF,"Expected \"if\".");
        t.skipToken(); // skip if

        this.cond.parse();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.THEN,"Expected \"then\".");
        t.skipToken(); // skip then

        //parse first stmt
        this.stmtSeq1 = new StmtSeq();
        this.stmtSeq1.parse();

        //if we have else token, parse second
        this.kind = t.getToken();
        if (this.kind == TokenKind.ELSE) {
            t.skipToken();
            this.stmtSeq2 = new StmtSeq();
            this.stmtSeq2.parse();
        }

        Reporter.assertElseFatalError(t.getToken() == TokenKind.END,"Expected \"end\".");
        t.skipToken(); // skip end

        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,"Expected \";\".");
        t.skipToken(); //skip semicolon

    }

    public void print(int indent) {
        System.out.print("if ");
        this.cond.print();

        System.out.print(" then");
        this.stmtSeq1.print(indent + 4); // print stmt with indent

        //print else stmt with indent
        if(this.kind == TokenKind.ELSE) {
            System.out.println("");
            Utilities.printSpaces(indent);
            System.out.print("else");
            this.stmtSeq2.print(indent + 4);
        }

        System.out.println("");
        Utilities.printSpaces(indent);
        System.out.print("end;");

    }

    public void exec() {
        if (this.cond.exec()) {
            this.stmtSeq1.exec();
        } else if (this.kind == TokenKind.ELSE) {
            this.stmtSeq2.exec();
        }
    }

}
