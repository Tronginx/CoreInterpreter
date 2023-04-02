package lab3;

/**
 * parse, print and execute following BNF grammar:
 *<id list> ::= <id> | <id>, <id list>
 *
 * @author Tron Tian
 */
public class IdList {
    private Id id;
    private IdList idList;
    private TokenKind kind;

    public void parse() {
        Tokenizer t = Tokenizer1.instance();

        //parse first id
        this.id = Id.parse();

        //get into to second choice and parse
        this.kind = t.getToken();
        if (this.kind == TokenKind.COMMA) {
            t.skipToken(); //skip comma
            this.idList = new IdList();
            this.idList.parse();
        }
    }


    public void print() {
        this.id.print();

        if (this.kind == TokenKind.COMMA) {
            System.out.print(", ");
            this.idList.print();
        }
    }

    public void exec() {
        this.id.exec();
        if (this.kind == TokenKind.COMMA) {
            this.idList.exec();
        }
    }

    public void read() {
        //read int value from CoreInterpreter's data scanner
        int value = CoreInterpreter.data.nextInt();
        //set value to given id
        this.id.setVal(value);

        if (this.kind == TokenKind.COMMA) {
            this.idList.read();
        }
    }

    public void write() {
        //print out given id and its value
        System.out.println(this.id.getName() + " = " + this.id.getVal());
        if (this.kind== TokenKind.COMMA) {
            this.idList.write();
        }
    }
}


