package SymbolTable;

import java.util.LinkedList;
import java.util.List;

public class Symbol {

    private static final int VAR = 0;
    private static final int METHOD = 1;

    public String id;
    public Integer kind;
    public List<Integer> typeList;
    public Integer typeRet;
    public List<Integer> outpar;

    public Symbol(String id, Integer type) {
        this.id = id;
        this.kind = VAR;
        typeList = new LinkedList<>();
        typeList.add(type);
    }

    public Symbol(String id, List<Integer> param, Integer ret) {
        this.id = id;
        this.kind = METHOD;
        this.typeList = new LinkedList<Integer>();
        if (param != null)
            typeList.addAll(param);
        if (ret != null)
            typeRet = ret;
    }

    public Symbol(String id, List<Integer> param, List<Integer> outpar, Integer ret) {
        this.id = id;
        this.kind = METHOD;
        this.typeList = new LinkedList<Integer>();
        this.outpar = new LinkedList<>();
        if (param != null)
            typeList.addAll(param);
        if (ret != null)
            typeRet = ret;
        if (outpar != null)
            this.outpar.addAll(outpar);
    }

    public Symbol(String id, List<Integer> param, List<Integer> outpar) {
        this.id = id;
        this.kind = METHOD;
        this.typeList = new LinkedList<Integer>();
        this.outpar = new LinkedList<>();
        if (param != null)
            typeList.addAll(param);
        if (outpar != null)
            this.outpar.addAll(outpar);
    }

    public Symbol(String id, List<Integer> param) {
        this.id = id;
        this.kind = METHOD;
        this.typeList = new LinkedList<Integer>();
        if (param != null)
            typeList.addAll(param);
    }

    public Integer getType() {
        if (kind != VAR)
            return null;
        return typeList.get(0);
    }
}
