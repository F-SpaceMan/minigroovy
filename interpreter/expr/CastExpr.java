package interpreter.expr;

import java.util.List;

import interpreter.value.BooleanValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
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
        if(op == CastOp.BooleanOp) {
            return new BooleanValue((Boolean) expr.expr().value());
        } else if(op == CastOp.IntegerOp) {
            return new NumberValue((Integer) expr.expr().value());
        } else if(op == CastOp.StringOp) {
            return new TextValue((String) expr.expr().value());
        }
        return null;
    }
    
}
