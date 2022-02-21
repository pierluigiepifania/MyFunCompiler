
package Utility;

import SymbleTable.Token;

public class MyFunC {

    public static String typeConverter(int type) {
        if (type == Token.STRING)
            return "char* ";
        if (type == Token.BOOL || type == Token.INTEGER)
            return "int ";
        if (type == Token.REAL)
            return "float ";
        if (type == Token.NULL)
            return "NULL ";
        return null;
    }

    public static String opConverter(int op) {
        if (op == Token.AND)
            return "&&";
        if (op == Token.OR)
            return "||";
        if (op == Token.NOT)
            return "!";
        if (op == Token.ASSIGN)
            return "=";
        if (op == Token.DIV)
            return "/";
        if (op == Token.EQ)
            return "==";
        if (op == Token.GE)
            return ">=";
        if (op == Token.GT)
            return ">";
        if (op == Token.LE)
            return "<=";
        if (op == Token.LT)
            return "<";
        if (op == Token.NE)
            return "!=";
        if (op == Token.MINUS)
            return "-";
        if (op == Token.PLUS)
            return "+";
        if (op == Token.TIMES)
            return "*";
        if (op == Token.POW)
            return "^";
        if (op == Token.STR_CONCAT)
            return "@";
        if (op == Token.DIVINT)
            return "/";
        return "error in opConverter";
    }

    public static String getPlaceholder(Integer type) {
        if (type == Token.STRING)
            return "%s";
        if (type == Token.INTEGER || type == Token.BOOL)
            return "%d";
        if (type == Token.REAL)
            return "%f";
        return "";
    }

}
