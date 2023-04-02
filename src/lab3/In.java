package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<in> ::= read <id list>;
 *
 * @author Tron Tian
 */

public class In {
    private IdList idList;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //check and skip read
        Reporter.assertElseFatalError(t.getToken() == TokenKind.READ,"Expected \"read\".");
        t.skipToken(); // skip read

        //parse id list
        this.idList = new IdList();
        this.idList.parse();

        Reporter.assertElseFatalError(t.getToken() == TokenKind.SEMICOLON,"Expected \";\".");
        t.skipToken(); // skip semicolon
    }

    public void print() {
        System.out.print("read ");
        this.idList.print();
        System.out.print(";");
    }

    public void exec() {
        this.idList.read();
    }
}
