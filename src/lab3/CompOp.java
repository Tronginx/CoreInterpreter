package lab3;
/**
 * parse, print and execute following BNF grammar:
 *<compop>::=!=|==|<|>|<=|>=
 *
 * @author Tron Tian
 */
public class CompOp {
    private String op;

    private TokenKind kind;

    public void parse() {

        Tokenizer t = Tokenizer1.instance();
        this.kind = t.getToken();

        //determine which kind of comp op we are facing
        switch (this.kind) {
            case NOT_EQ:
                this.op = " != ";
                break;
            case IS_EQ:
                this.op = " == ";
                break;
            case LESS_THAN:
                this.op = " < ";
                break;
            case LESS_EQ_THAN:
                this.op = " <= ";
                break;
            case GREATER_THAN:
                this.op = " > ";
                break;
            case GREATER_EQ_THAN:
                this.op = " >= ";
                break;
            default:
                //not supposed to reach this line
                Reporter.fatalErrorToConsole("Parser of op needs fix");
        }
        t.skipToken();
    }
    public void print() {
        //print current op
        System.out.print(this.op);
    }


    public boolean exec(int r1, int r2) {
        //evaluate values with given comp op and values
        switch (this.kind) {
            case NOT_EQ:
                return r1 != r2;
            case IS_EQ:
                return r1 == r2;
            case LESS_THAN:
                return r1 < r2;
            case LESS_EQ_THAN:
                return r1 <= r2;
            case GREATER_THAN:
                return r1 > r2;
            case GREATER_EQ_THAN:
                return r1 >= r2;
            default:
                //not supposed to reach this line
                Reporter.fatalErrorToConsole("Parser of op needs fix");
        }
            return false;
    }

}