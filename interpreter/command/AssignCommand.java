package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.SetExpr;
import interpreter.util.Utils;
import interpreter.value.NumberValue;
import interpreter.value.Value;

public class AssignCommand extends Command {

    public enum Op {
        StdOp,
        AddOp,
        SubOp,
        MulOp,
        DivOp,
        ModOp,
        PowerOp;
    }

    private SetExpr lhs;
    private Op op;
    private Expr rhs;

    public AssignCommand(int line, SetExpr lhs, Op op, Expr rhs) {
        super(line);

        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }

    @Override
    public void execute() {
        switch (op) {
            case StdOp:
                stdOp();
                break;
            case AddOp:
                addOp();
                break;
            case SubOp:
                subOp();
                break;
            case MulOp:
                mulOp();
                break;
            case DivOp:
                divOp();
                break;
            case ModOp:
                modOp();
                break;
            case PowerOp:
                powerOp();
                break;
            default:
                Utils.abort(super.getLine());
        }
    }

    private void stdOp() {
        Value<?> rvalue = rhs.expr();
        lhs.setValue(rvalue);
    }

    private void addOp() {
        NumberValue rvalue = (NumberValue) rhs.expr();
        Integer i = rvalue.value();
        NumberValue lvalue = (NumberValue) lhs.expr();

        Integer j = lvalue.value();
        lhs.setValue(new NumberValue(j + i));
    }

    private void subOp() {
    }

    private void mulOp() {
    }

    private void divOp() {
    }

    private void modOp() {
    }

    private void powerOp() {
    }

}
