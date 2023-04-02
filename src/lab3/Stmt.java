package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<stmt> ::= <assign>|<if>|<loop>|<in>|<out>
 *
 * @author Tron Tian
 */
public class Stmt {

    private Assign assign;
    private If ifInstance;
    private Loop loop;
    private In in;
    private Out out;

    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //determine which stmt choice we go in
        this.kind = t.getToken();
        switch (this.kind) {
            case IDENTIFIER:
                this.assign = new Assign();
                this.assign.parse();
                break;
            case IF:
                this.ifInstance = new If();
                this.ifInstance.parse();
                break;
            case WHILE:
                this.loop = new Loop();
                this.loop.parse();
                break;
            case READ:
                this.in = new In();
                this.in.parse();
                break;
            case WRITE:
                this.out = new Out();
                this.out.parse();
                break;
            default:
                Reporter.fatalErrorToConsole("Not valid <stmt>");
        }
    }

    public void print(int n) {
        switch (this.kind) {
            case IDENTIFIER:
                this.assign.print();
                break;
            case IF:
                this.ifInstance.print(n);
                break;
            case WHILE:
                this.loop.print(n);
                break;
            case READ:
                this.in.print();
                break;
            case WRITE:
                this.out.print();
                break;
            default:
                Reporter.fatalErrorToConsole("Should not reach this line, parser of stmt has issues.");
        }
    }

    public void exec() {
        switch (this.kind) {
            case IDENTIFIER :
                this.assign.exec();
                break;
            case IF:
                this.ifInstance.exec();
                break;
            case WHILE:
                this.loop.exec();
                break;
            case READ:
                this.in.exec();
                break;
            case WRITE:
                this.out.exec();
                break;
            default:
                Reporter.fatalErrorToConsole("Should not reach this line, parser of stmt has issues.");
        }
    }
}
