package main.java.Visitor;

public interface Visitable {

    public Object accept(Visitor visitor) throws Exception;

}
