package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<prog> ::= program <decl seq> begin <stmt seq> end
 *
 * @author Wayne Heym
 * @author Tron Tian
 */
final class Prog {
    private DeclSeq ds;
    private StmtSeq ss;

    /**
     * Parses a Core program into this object.
     *
     */
    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.PROGRAM,
                "Expected \"program\".");
        t.skipToken(); //skip program

        //parse decl seq
        this.ds = new DeclSeq();
        this.ds.parse();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.BEGIN,
                "Expected \"begin\".");
        t.skipToken(); // skip begin


        //parse stmt seq
        this.ss = new StmtSeq();
        this.ss.parse();
        Reporter.assertElseFatalError(t.getToken() == TokenKind.END,
                "Expected \"end\".");
        t.skipToken(); // skip end


        Reporter.assertElseFatalError(t.getToken() == TokenKind.EOF,
                "Expected end of program file.");
    }


    public void print() {
        System.out.print("program");

        this.ds.print(4);

        System.out.println("");
        System.out.print("begin");

        this.ss.print(4);

        System.out.println("");
        System.out.println("end");
    }

    /**
     * Executes a Core program.
     */
    public void exec() {
        this.ds.exec();
        this.ss.exec();

    }

}