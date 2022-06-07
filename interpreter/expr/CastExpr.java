package interpreter.expr;

import interpreter.util.Utils;
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
        if (op == CastOp.BooleanOp) {
            return new BooleanValue((Boolean) expr.expr().value());
        } else if (op == CastOp.IntegerOp) {
            return new NumberValue((Integer) expr.expr().value());
        } else if (op == CastOp.StringOp) {
            return new TextValue((String) expr.expr().value());
        }
        return null;
    }

    private Value<?> stringOp(Expr expression) {
        Value<?> value = expression.expr();
        return new TextValue(value.toString());
    }

    private Value<?> booleanOp(Expr expression) {
        Value<?> value = expression.expr();
        if (value instanceof BooleanValue) {
            return value;
        } else if (value instanceof TextValue) {
            return new BooleanValue(!((TextValue) value).value().isEmpty());
        } else if (value instanceof NumberValue) {
            return new BooleanValue(((NumberValue) value).value() != 0);
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> integerOp(Expr expression) {
        Value<?> value = expression.expr();
        if (value instanceof TextValue) {
            return new NumberValue(Integer.parseInt(((TextValue) value).value()));
        } else if (value instanceof BooleanValue) {
            return new NumberValue(((BooleanValue) value).value() ? 1 : 0);
        } else if (value instanceof NumberValue) {
            return new NumberValue(((NumberValue) value).value());
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }
}
