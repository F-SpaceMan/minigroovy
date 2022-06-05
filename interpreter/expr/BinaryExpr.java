package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.ArrayValue;
import interpreter.value.BooleanValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

// TODO: VERIFICAR TODAS IMPLEMENTACOES JA VEITAS
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
                System.out.println("adfasdaf");
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
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if ((!(lvalue instanceof NumberValue) || !(rvalue instanceof NumberValue)) &&
                (!(lvalue instanceof BooleanValue) || !(rvalue instanceof BooleanValue)) &&
                (!(lvalue instanceof TextValue) || !(rvalue instanceof TextValue)))
            Utils.abort(super.getLine());

        if (lvalue instanceof BooleanValue && rvalue instanceof BooleanValue) {
            BooleanValue nvl = (BooleanValue) lvalue;
            boolean lv = nvl.value();

            BooleanValue nvr = (BooleanValue) rvalue;
            boolean rv = nvr.value();

            BooleanValue res = new BooleanValue(lv & rv);
            return res;
        }
    }

    private Value<?> orOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if ((!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue)) &&
                (!(lvalue instanceof BooleanValue) ||
                        !(rvalue instanceof BooleanValue)))
            Utils.abort(super.getLine());

        BooleanValue nvl = (BooleanValue) lvalue;
        boolean lv = nvl.value();

        BooleanValue nvr = (BooleanValue) rvalue;
        boolean rv = nvr.value();

        BooleanValue res = new BooleanValue(lv | rv);
        return res;
    }

    private Value<?> equalOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv == rv);
        return res;
    }

    private Value<?> notEqualOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv != rv);
        return res;
    }

    private Value<?> lowerThanOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv < rv);
        return res;
    }

    private Value<?> lowerEqualOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv <= rv);
        return res;
    }

    private Value<?> greaterThanOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv > rv);
        return res;
    }

    private Value<?> greaterEqualOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        BooleanValue res = new BooleanValue(lv >= rv);
        return res;
    }

    private Value<?> containsOp() {
        // TODO: Implement me
        return null;
    }

    private Value<?> notContainsOp() {
        // Value<?> lvalue = left.expr();
        // Value<?> rvalue = right.expr();

        // if ((!(lvalue instanceof ArrayValue) ||
        // !(rvalue instanceof ArrayValue)) &&
        // (!(lvalue instanceof MapValue) ||
        // !(rvalue instanceof MapValue))
        // )
        // Utils.abort(super.getLine());

        // TODO: Implement me

        // return res;
        return null;
    }

    private Value<?> addOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();
        // TODO: Ver casos de concatenacao

        NumberValue res = new NumberValue(lv + rv);
        return res;
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
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        NumberValue res = new NumberValue(lv * rv);
        return res;
    }

    private Value<?> divOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        NumberValue res = new NumberValue(lv / rv);
        return res;
    }

    private Value<?> modOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        NumberValue res = new NumberValue(lv % rv);
        return res;
    }

    private Value<?> powerOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (!(lvalue instanceof NumberValue) ||
                !(rvalue instanceof NumberValue))
            Utils.abort(super.getLine());

        NumberValue nvl = (NumberValue) lvalue;
        int lv = nvl.value();

        NumberValue nvr = (NumberValue) rvalue;
        int rv = nvr.value();

        NumberValue res = new NumberValue((int) Math.pow(lv, rv));
        return res;
    }

}
