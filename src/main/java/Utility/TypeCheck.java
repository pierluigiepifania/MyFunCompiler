package main.java.Utility;


import main.java.SymbleTable.Token;

public class TypeCheck {

    public static int checkType(Integer op, Integer type1, Integer type2) {

        if (isMatOp(op)) {
            if (isMatNotPermissOp(type1) || isMatNotPermissOp(type2)) {
                return -1;
            }
            if (type1 == type2)
                return type1;
            else
                return Token.REAL;
        }

        if (isLogicOp(op)) {
            if (type1 == type2 && type1 == Token.BOOL) {
                return Token.BOOL;
            }
        }

        if (isRelOp(op)) {
            if (type1 != Token.NULL && type2 != Token.NULL)
                return Token.BOOL;
        }

        if (isStringOp(op)) {
            if (type1 != Token.NULL && type2 != Token.NULL)
                return Token.STRING;
        }

        if (op == Token.ASSIGN) {
            if (type1 == type2 || (type1 == Token.STRING && type2 == Token.NULL))
                return type1;
        }

        return -1;
    }

    public static int checkType(Integer op, Integer type) {
        if (!isUnaryOp(op))
            return -1;
        if (op == Token.MINUS && (!isMatNotPermissOp(type)))
            return type;
        if (op == Token.NOT && type == Token.BOOL)
            return type;
        return -1;
    }

    public static boolean isRelOp(Integer op) {
        return op == Token.LE || op == Token.LT || op == Token.GE || op == Token.GT || op == Token.EQ;
    }

    public static boolean isMatOp(Integer op) {
        return op == Token.PLUS || op == Token.MINUS || op == Token.DIV || op == Token.POW || op == Token.TIMES;
    }

    public static boolean isMatNotPermissOp(Integer type) {
        return type != Token.INTEGER && type != Token.REAL;
    }

    public static boolean isUnaryOp(Integer op) {
        return op == Token.NE || op == Token.NOT || op == Token.MINUS;
    }

    public static boolean isLogicOp(Integer op) {
        return op == Token.AND || op == Token.OR;
    }

    public static boolean isStringOp(Integer op) {
        return op == Token.STR_CONCAT;
    }

}
