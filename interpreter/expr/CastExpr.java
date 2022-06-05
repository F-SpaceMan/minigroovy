package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class CastExpr extends Expr {

    public enum CastOp {
        BooleanOp,
        IntegerOp,
        StringOp
    } 

    private Expr expr;
    private CastOp op;

    public CastExpr(int line, Expr expr, CastOp op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public Value<?> expr() {
        System.out.println("CastExpr.expr()");
        return null;
    }
    
}
