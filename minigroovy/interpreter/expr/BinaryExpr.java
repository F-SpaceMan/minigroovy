package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.NumberValue;
import interpreter.value.Value;

public class BinaryExpr extends Expr {

    public enum Op {
        AndOp,
        OrOp,
        EqualOp,
        NotEqualOp,
        LowerThanOp,
        LowerEqualOp,
        GreaterThanOp,
        GreaterEqualOp,
        ContainsOp,
        NotContainsOp,
        AddOp,
        SubOp,
        MulOp,
        DivOp,
        ModOp,
        PowerOp;
    }

    private Expr left;
    private Op op;
    private Expr right;

    public BinaryExpr(int line, Expr left, Op op, Expr right) {
        super(line);

        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = null;
        switch (op) {
            case AndOp:
                v = andOp();
                break;
            case OrOp:
                v = orOp();
                break;
            case EqualOp:
                v = equalOp();
                break;
            case NotEqualOp:
                v = notEqualOp();
                break;
            case LowerThanOp:
                v = lowerThanOp();
                break;
            case LowerEqualOp:
                v = lowerEqualOp();
                break;
            case GreaterThanOp:
                v = greaterThanOp();
                break;
            case GreaterEqualOp:
                v = greaterEqualOp();
                break;
            case ContainsOp:
                v = containsOp();
                break;
            case NotContainsOp:
                v = notContainsOp();
                break;
            case AddOp:
                v = addOp();
                break;
            case SubOp:
                v = subOp();
                break;
            case MulOp:
                v = mulOp();
                break;
            case DivOp:
                v = divOp();
                break;
            case ModOp:
                v = modOp();
                break;
            case PowerOp:
                v = powerOp();
                break;
            default:
                Utils.abort(super.getLine());
        }

        return v;
    }

    private Value<?> andOp() {
        return null;
    }

    private Value<?> orOp() {
        return null;
    }

    private Value<?> equalOp() {
        return null;
    }

    private Value<?> notEqualOp() {
        return null;
    }

    private Value<?> lowerThanOp() {
        return null;
    }

    private Value<?> lowerEqualOp() {
        return null;
    }

    private Value<?> greaterThanOp() {
        return null;
    }

    private Value<?> greaterEqualOp() {
        return null;
    }

    private Value<?> containsOp() {
        return null;
    }

    private Value<?> notContainsOp() {
        return null;
    }

    private Value<?> addOp() {
        return null;
    }

    private Value<?> subOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
            !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        NumberValue res = new NumberValue(lv - rv);
        return res;
    }

    private Value<?> mulOp() {
        return null;
    }

    private Value<?> divOp() {
        return null;
    }

    private Value<?> modOp() {
        return null;
    }

    private Value<?> powerOp() {
        return null;
    }
    
}
