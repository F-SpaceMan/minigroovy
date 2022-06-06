package interpreter.expr;

import java.util.List;

import interpreter.value.Value;

public class MapExpr extends Expr {
    
    public class MapItem {
        private String key;
        private Expr value;
        public MapItem (String key, Expr value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<MapItem> array;

    public MapExpr(int line, List<MapItem> array) {
        super(line);
        this.array = array;
    }

    public void addItem(MapItem item) {
    } 

    @Override
    public Value<?> expr() {
        System.out.println("MapExpr.expr()");
        return null;
    }
//     public Value<?> expr() {
//         List<Value<?>> values = new ArrayList<Value<?>>();
//         for (Expr expression : array) {
//             values.add(expression.expr());
//         }
//         ArrayValue newArray = new ArrayValue(values);
//         return newArray;
//     }
// }
    
}
