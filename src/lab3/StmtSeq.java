package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<stmt seq> ::= <stmt> | <stmt> <stmt seq>
 *
 * @author Tron Tian
 */
public class StmtSeq {
    private Stmt stmt;
    private StmtSeq stmtSeq;

    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first stmt
        this.stmt = new Stmt();
        this.stmt.parse();

        //if we still found stmt seq. keep parsing
        this.kind = t.getToken();
        if (this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
            this.stmtSeq = new StmtSeq();
            this.stmtSeq.parse();
        }
    }

    public void print(int indent) {
        System.out.println("");
        Utilities.printSpaces(indent);
        this.stmt.print(indent);
        if (this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
            this.stmtSeq.print(indent);
        }

    }

    public void exec() {
        this.stmt.exec();
        if (this.kind != TokenKind.END && this.kind != TokenKind.ELSE) {
            this.stmtSeq.exec();
        }
    }

}
