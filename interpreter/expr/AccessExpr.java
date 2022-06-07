package interpreter.expr;

import interpreter.util.Memory;
import interpreter.util.Utils;
import interpreter.value.ArrayValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class AccessExpr extends SetExpr {

    private Expr base;
    private Expr index;

    public AccessExpr(int line, Expr base, Expr index) {
        super(line);
        this.base = base;
        this.index = index;
    }

    @Override
    public Value<?> expr() {
        if (base.expr() instanceof MapValue) {
            String value = ((TextValue) base.expr()).value();
            MapValue map = (MapValue) index.expr();

            if (!map.value().containsKey(value)) {
                return null;
            } else
                return map.value().get(value);
        } else if (base.expr() instanceof ArrayValue) {
            int iterator = ((NumberValue) index.expr()).value();
            ArrayValue array = (ArrayValue) base.expr();
            if (0 > iterator || iterator >= array.value().size())
                return null;
            else
                return array.value().get(iterator);
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    @Override
    public void setValue(Value<?> value) {
        Memory.write(index.toString(), value);
    }

}