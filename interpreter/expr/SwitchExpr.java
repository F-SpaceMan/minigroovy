package interpreter.expr;

import java.util.List;
import interpreter.value.Value;
import java.util.ArrayList;

public class SwitchExpr extends Expr {
    private Expr expr;
    private List<CaseItem> cases = new ArrayList();
    private Expr default_;

    public SwitchExpr(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    public void addCase(CaseItem item) {
        cases.add(item);
    }

    public void setDefault(Expr default_) {
        this.default_ = default_;
    }

    @Override
    public Value<?> expr() {
        Value<?> value = expr.expr();
        for(CaseItem item : cases){
            if(item.key.expr().equals(value)){
                return item.value.expr();
            }
        }
        if (default_ != null){
            return default_.expr();
        }
        return null;
    }
}
