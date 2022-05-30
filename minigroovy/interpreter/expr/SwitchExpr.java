package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class SwitchExpr extends Expr {

    public class CaseItem {
        private Expr key;
        private Expr value;

        public CaseItem(Expr key, Expr value) {
            this.key = key;
            this.value = value;
        }
    }

    private Expr expr;
    private List<CaseItem> cases;
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
        // TODO Auto-generated method stub
        return null;
    }
}
