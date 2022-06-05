package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.ArrayValue;
import interpreter.value.Value;
import java.util.List;


public class DeclarationType2Command extends DeclarationCommand{
    private List<Variable> lhs;
    public DeclarationType2Command(int line, List<Variable> lhs, Expr rhs) {
        super(line, rhs);
        this.lhs = lhs;
    }
    
    @Override
    public void execute() {
        Value<?> value = rhs.expr();
        int iterator = 0;
        ArrayValue array = (ArrayValue) value;
        for(Variable variable : lhs){
                if(!(iterator >= array.value().size())) {
                    variable.setValue(array.value().get(iterator));
                }
                else{
                    variable.setValue(null);
                }
                iterator++;
        }  
    }
}
