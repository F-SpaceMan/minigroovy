package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class ArrayExpr extends Expr {

    private List<Expr> array;

    public ArrayExpr(int line, List<Expr> array) {
        super(line);
        this.array = array;
    }

    @Override
    public Value<?> expr() {
        System.out.println("ArrayExpr.expr()");
        return null;
    }
    
}
