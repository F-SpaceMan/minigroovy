package interpreter.expr;

import java.util.ArrayList;
import java.util.List;
import interpreter.value.ArrayValue;
import interpreter.value.Value;

public class ArrayExpr extends Expr {

    private List<Expr> array;

    public ArrayExpr(int line, List<Expr> array) {
        super(line);
        this.array = array;
    }

    @Override
    public Value<?> expr() {
        List<Value<?>> values = new ArrayList<Value<?>>();
        for (Expr expression : array) {
            values.add(expression.expr());
        }
        ArrayValue newArray = new ArrayValue(values);
        return newArray;
    }
}
