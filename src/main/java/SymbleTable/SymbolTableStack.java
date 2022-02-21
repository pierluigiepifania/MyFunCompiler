package SymbleTable;

import java.util.ArrayDeque;
import java.util.Deque;

public class SymbolTableStack {

    private Deque<SymbolTable> stack;

    public SymbolTableStack() {
        this.stack = new ArrayDeque<>();
    }

    public void enterScope() {
        stack.push(new SymbolTable());
    }

    public Symbol lookup(String name) {
        Symbol toRet = null;
        for (SymbolTable s : stack) {
            toRet = s.get(name);
            if (toRet != null)
                return toRet;
        }
        return null;
    }

    public Symbol lookup(String name, int type) {
        Symbol toRet;
        for (SymbolTable s : stack) {
            toRet = s.get(name);
            if (toRet != null && toRet.kind == type)
                return toRet;
        }
        return null;
    }

    public boolean probe(Symbol sym) {
        return stack.peek().containsKey(sym.id);
    }

    public boolean probe(String name) {
        return stack.peek().containsKey(name);
    }

    public boolean addId(Symbol sym) {
        if (probe(sym.id))
            return false;
        else
            stack.peek().put(sym.id, sym);
        return true;
    }

    public void exitScope() {
        stack.pop();
    }

}
