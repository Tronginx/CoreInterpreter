package lab3;

/**
 * parse, print and execute following BNF grammar:
 * <decl seq> ::= <decl> | <decl> <decl seq>
 *
 * @author Tron Tian
 */

public class DeclSeq {

    private Decl dcl;
    private DeclSeq ds;
    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse decl
        this.dcl = new Decl();
        this.dcl.parse();

        //if we still found decl seq, keep parsing
        this.kind = t.getToken();
        if(this.kind != TokenKind.BEGIN) {
            this.ds = new DeclSeq();
            this.ds.parse();
        }
    }
    //since decl seq is starting point a line, we need to apply indent on it
    public void print(int indent) {
        System.out.println("");
        Utilities.printSpaces(indent);

        this.dcl.print();

        if(this.kind != TokenKind.BEGIN) {
            this.ds.print(indent);
        }
    }

    public void exec() {
        this.dcl.exec();
        if (this.kind != TokenKind.BEGIN) {
            this.ds.exec();
        }
    }
}
