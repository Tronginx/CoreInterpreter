package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<decl>::=int <idlist>;
 *
 * @author Tron Tian
 */
public class Decl {
    private IdList idList;


    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //check and skip int keyword
        if(t.getToken() != TokenKind.INT) {
            Reporter.fatalErrorToConsole("int expected");
        }
        t.skipToken(); //skip int

        //parse id list
        this.idList = new IdList();
        this.idList.parse();

        //check and skip semicolon
        if(t.getToken() != TokenKind.SEMICOLON) {
            Reporter.fatalErrorToConsole("; expected");
        }
        t.skipToken();//skip ;
    }


    public void print() {
        System.out.print("int ");
        this.idList.print();
        System.out.print(";");
    }

    public void exec() {
        this.idList.exec();
    }

}
