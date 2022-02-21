package Node;
import java.util.LinkedList;
import java.util.List;

public abstract class Node {

    private String op;
    public List<Integer> typeList;
    public Integer ret;
    public List<Integer> outpar;
    public boolean isFunction;
    public boolean vartype;

    public Node(String op) {
        this.op = op;
        typeList = new LinkedList<>();
        outpar = new LinkedList<>();
    }

    public String getOp() {
        return op;
    }

    public void setOp(int type) {
        this.op = op;
    }

    public Integer getType() {
        return typeList.get(0);
    }

    public Integer getRetType() {
        return ret;
    }

    public void addType(int type) {
        typeList.add(type);
    }

    public void addRetType(int type) {
        outpar.add(type);
    }

}
