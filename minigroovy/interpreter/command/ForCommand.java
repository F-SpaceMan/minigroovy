package interpreter.command;

import interpreter.expr.Expr;
import interpreter.value.Value;

public class ForCommand extends Command {
    private Command init;
    private Expr cond;
    private Command inc;
    private Command cmds;

    public ForCommand(int line, Command init, Expr cond, Command inc, Command cmds) {
        super(line);

        this.init = init;
        this.cond = cond;
        this.inc = inc;
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        init.execute();
        do {
            Value<?> v = cond.expr();
            if (v != null && v.eval()) {
                cmds.execute();
                inc.execute();
            } else
                break;
        } while (true);
    }
}
