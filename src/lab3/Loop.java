package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<loop> ::= while <cond> loop <stmt seq> end;
 *
 * @author Tron Tian
 */
public class Loop {
    private Cond cond;
    private StmtSeq stmtSeq;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.WHILE,"Expected \"while\".");
        t.skipToken(); // skip while

        //parse first cond
        this.cond = new Cond();
        this.cond.parse();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.LOOP,"Expected \"loop\".");
        t.skipToken(); //skip loop

        //parse stmt seq
        this.stmtSeq = new StmtSeq();
        this.stmtSeq.parse();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.END,"Expected \"end\".");
        t.skipToken(); // skip end

        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,"Expected \";\".");
        t.skipToken(); //skip semicolon
    }

    public void print(int indent) {
        System.out.print("while ");
        this.cond.print();
        System.out.print("loop");
        this.stmtSeq.print(indent + 4);
        System.out.println("");
        Utilities.printSpaces(indent);
        System.out.print("end;");
    }

    public void exec() {
        Boolean condition = this.cond.exec();
        while (condition) {
            this.stmtSeq.exec();
            condition = this.cond.exec();
        }
    }
}
