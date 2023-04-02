package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<out> ::= write <id list>;
 *
 * @author Tron Tian
 */
public class Out {

    private IdList idList;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();
        //check and skip write key word
        Reporter.assertElseFatalError(t.getToken() == TokenKind.WRITE,"Expected \"write\".");
        t.skipToken(); //skip write

        //parse id list
        this.idList = new IdList();
        this.idList.parse();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,"Expected \";\".");
        t.skipToken(); // skip semicolon
    }

    public void print() {
        System.out.print("write ");
        this.idList.print();
        System.out.print(";");
    }

    public void exec() {
        this.idList.write();
    }
}
