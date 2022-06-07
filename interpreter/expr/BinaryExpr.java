package interpreter.expr;

import java.util.Arrays;

import interpreter.util.Utils;
import interpreter.value.ArrayValue;
import interpreter.value.BooleanValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
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
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();
        BooleanValue lv = null;
        BooleanValue rv = null;

        if (lvalue instanceof ArrayValue || rvalue instanceof MapValue) {
            Utils.abort(super.getLine());
            return null;
        } else {
            if (lvalue instanceof NumberValue) {
                if ((Integer) lvalue.value() == 0) {
                    lv = new BooleanValue(false);
                } else {
                    lv = new BooleanValue(true);
                }
            } else if (lvalue instanceof TextValue) {
                if (((String) lvalue.value()).isEmpty()) {
                    lv = new BooleanValue(false);
                } else {
                    lv = new BooleanValue(true);
                }
            } else if (lvalue instanceof BooleanValue) {
                lv = (BooleanValue) lvalue;
            }

            if (rvalue instanceof NumberValue) {
                if ((Integer) rvalue.value() == 0) {
                    rv = new BooleanValue(false);
                } else {
                    rv = new BooleanValue(true);
                }
            } else if (rvalue instanceof TextValue) {
                if (((String) rvalue.value()).isEmpty()) {
                    rv = new BooleanValue(false);
                } else {
                    rv = new BooleanValue(true);
                }
            } else if (rvalue instanceof BooleanValue) {
                rv = (BooleanValue) rvalue;
            }

            if (lvalue == null) {
                lv = new BooleanValue(false);
            }
            if (rvalue == null) {
                rv = new BooleanValue(false);
            }

            boolean l = (boolean) lv.value();
            boolean r = (boolean) rv.value();
            BooleanValue res = new BooleanValue(l && r);
            return res;
        }
    }

    private Value<?> orOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();
        BooleanValue lv = null;
        BooleanValue rv = null;

        if (lvalue instanceof ArrayValue || rvalue instanceof MapValue) {
            Utils.abort(super.getLine());
            return null;
        } else {
            if (lvalue instanceof NumberValue) {
                if ((Integer) lvalue.value() == 0) {
                    lv = new BooleanValue(false);
                } else {
                    lv = new BooleanValue(true);
                }
            } else if (lvalue instanceof TextValue) {
                if (((String) lvalue.value()).isEmpty()) {
                    lv = new BooleanValue(false);
                } else {
                    lv = new BooleanValue(true);
                }
            } else if (lvalue instanceof BooleanValue) {
                lv = (BooleanValue) lvalue;
            }

            if (rvalue instanceof NumberValue) {
                if ((Integer) rvalue.value() == 0) {
                    rv = new BooleanValue(false);
                } else {
                    rv = new BooleanValue(true);
                }
            } else if (rvalue instanceof TextValue) {
                if (((String) rvalue.value()).isEmpty()) {
                    rv = new BooleanValue(false);
                } else {
                    rv = new BooleanValue(true);
                }
            } else if (rvalue instanceof BooleanValue) {
                rv = (BooleanValue) rvalue;
            }

            if (lvalue == null) {
                lv = new BooleanValue(false);
            }
            if (rvalue == null) {
                rv = new BooleanValue(false);
            }

            boolean l = (boolean) lv.value();
            boolean r = (boolean) rv.value();
            BooleanValue res = new BooleanValue(l || r);
            return res;
        }
    }

    private Value<?> equalOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();
        BooleanValue res = null;

        if (lvalue == null && rvalue == null) {
            res = new BooleanValue(true);
        } else if (lvalue == null) {
            res = new BooleanValue(rvalue.value().equals(null));
        } else if (rvalue == null) {
            res = new BooleanValue(lvalue.value().equals(null));
        } else {
            res = new BooleanValue(lvalue.value().equals(rvalue.value()));
        }

        return res;
    }

    private Value<?> notEqualOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();
        BooleanValue res = null;

        if (lvalue == null && rvalue == null) {
            res = new BooleanValue(false);
        } else if (lvalue == null) {
            res = new BooleanValue(!(rvalue.value().equals(null)));
        } else if (rvalue == null) {
            res = new BooleanValue(!(lvalue.value().equals(null)));
        } else {
            res = new BooleanValue(!(lvalue.value().equals(rvalue.value())));
        }

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
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();
        BooleanValue res = null;
        if (!(rvalue instanceof TextValue) || !(rvalue instanceof ArrayValue) || !(rvalue instanceof MapValue)) {
            Utils.abort(super.getLine());
            return null;
        } else if (rvalue instanceof TextValue) {
            TextValue rightString = (TextValue) rvalue;
            res = new BooleanValue(lvalue.value().equals(rightString.value()));
            return res;
        } else if (rvalue instanceof ArrayValue) {
            ArrayValue array = (ArrayValue) rvalue;
            res = new BooleanValue(Arrays.asList(array).contains(lvalue));
            return res;
        } else if (rvalue instanceof MapValue) {
            MapValue array = (MapValue) rvalue;
            res = new BooleanValue(Arrays.asList(array).contains(lvalue));
            return res;
        }
        return res;
    }

    private Value<?> notContainsOp() {
        return new BooleanValue(!containsOp().eval());
    }

    private Value<?> addOp() {
        Value<?> lvalue = left.expr();
        Value<?> rvalue = right.expr();

        if (lvalue instanceof NumberValue && rvalue instanceof NumberValue) {
            NumberValue nvl = (NumberValue) lvalue;
            int lv = nvl.value();

            NumberValue nvr = (NumberValue) rvalue;
            int rv = nvr.value();

            NumberValue res = new NumberValue(lv + rv);
            return res;
        } else if (lvalue == null || rvalue == null) {
            Utils.abort(super.getLine());
            return null;
        } else {
            return new TextValue(lvalue.value().toString() + rvalue.value().toString());
        }
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
