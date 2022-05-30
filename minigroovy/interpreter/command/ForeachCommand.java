package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.Variable;

public class ForeachCommand extends Command {
    private Variable var;
    private Expr expr;
    private Command cmds;

    public ForeachCommand(int line, Variable var, Expr expr, Command cmds) {
        super(line);

        this.var = var;
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub  
    }
}
