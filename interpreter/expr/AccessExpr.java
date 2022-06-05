package interpreter.expr;

import interpreter.util.Memory;
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
        // Value<?> v = Memory.read(base.toString());
        Value<?> v = base.expr();
        return v;
    }

    @Override
    public void setValue(Value<?> value) {
        Memory.write(index.toString(), value);
    }
    
}
