package lab3;

import java.util.HashMap;
import java.util.Map;

/**
 * parse, print and execute following BNF grammar:
 *<id> ::=<let>|<let><id>|<let><no>
 *
 * @author Tron Tian
 */
public class Id {
    private String identifier;
    private int value;
    private boolean declared = false;
    private boolean initialized = false;

    private static Map<String, Id> usedIds = new HashMap<>(); // store declared variables

    public static Id parse() {
        Tokenizer t = Tokenizer1.instance();
        //get id in String format
        String idString = t.idName();
        t.skipToken(); //skip id name;

        //return existed id
        //if the id is not existed, create new one in the map and return it
        if (usedIds.containsKey(idString)) {
            return usedIds.get(idString);
        } else {
            Id newId = new Id();
            newId.identifier = idString;
            usedIds.put(idString, newId);
            return newId;
        }


    }

    public void print() {
        System.out.print(this.identifier);
    }

    public void exec() {
        this.declared = true;
    }

    public void setVal(int value) {
        //runtime requirement: cannot assign value to a variable without prior declaration
        if (!this.declared) {
            Reporter.fatalErrorToConsole("Identifier " + this.identifier + " not declared");
        }
        this.value = value;
        this.initialized = true;
    }

    public String getName() {
        return this.identifier;
    }

    public int getVal() {
        if (!this.declared) {
            Reporter.fatalErrorToConsole("Error: Attempt to use variable without prior declaration.");
        } else if (!this.initialized) {
            Reporter.fatalErrorToConsole("Attempt to use variable " + this.identifier + " without prior initialization. Or, the variable declared more than once.");
        }
        return this.value;
    }


 }
