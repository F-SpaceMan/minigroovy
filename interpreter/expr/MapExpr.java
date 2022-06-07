package interpreter.expr;

import interpreter.value.MapValue;
import java.util.List;

import interpreter.value.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapExpr extends Expr {
    private List<MapItem> array = new ArrayList<>();

    public MapExpr(int line, List<MapItem> array) {
        super(line);
        this.array = array;
    }

    public void addItem(MapItem item) {
        array.add(item);
    } 

    @Override
    public Value<?> expr() {
        Map<String, Value<?>> map = new HashMap<>();
        for(MapItem item : array){
            map.put(item.key, item.value.expr());
        }
        MapValue mapValue = new MapValue(map);
        return mapValue;
    }
    
}
