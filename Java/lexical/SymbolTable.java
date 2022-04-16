package lexical;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS
        st.put("=", TokenType.ASSIGN);
        st.put(",", TokenType.COMMA);
        st.put("(", TokenType.PARENTESIS_OPEN);
        st.put(")", TokenType.PARENTESIS_CLOSE);
        st.put(";", TokenType.SEMICOLON);
        st.put("{", TokenType.BRACE_OPEN);
        st.put("}", TokenType.BRACE_CLOSE);
        st.put("[", TokenType.BRACKET_OPEN);
        st.put("]", TokenType.BRACKET_CLOSE);
        st.put(":", TokenType.COLON);

        // OPERATORS
        st.put("+=", TokenType.PLUS_ASSIGN);
        st.put("-=", TokenType.MINUS_ASSIGN);
        st.put("*=", TokenType.MULT_ASSIGN);
        st.put("%=", TokenType.MOD_ASSIGN);
        st.put("/=", TokenType.DIV_ASSIGN);
        st.put("^=", TokenType.POW_ASSIGN);
        st.put("&&", TokenType.AND);
        st.put("||", TokenType.OR);
        st.put("<", TokenType.LESS_THAN);
        st.put("<=", TokenType.LESS_THAN_EQUAL);
        st.put("==", TokenType.EQUAL);
        st.put(">", TokenType.GREATER_THAN);
        st.put(">=", TokenType.GREATER_THAN_EQUAL);
        st.put("!=", TokenType.NOT_EQUAL);
        st.put("!in", TokenType.NOT_IN);
        st.put("+", TokenType.PLUS);
        st.put("-", TokenType.MINUS);
        st.put("*", TokenType.MULT);
        st.put("/", TokenType.DIV);
        st.put("%", TokenType.MOD);
        st.put("^", TokenType.POW);
        st.put("!", TokenType.NOT);
        st.put("->", TokenType.ARROW);
        st.put(".", TokenType.DOT);

        // KEYWORDS
        st.put("if", TokenType.IF);
        st.put("else", TokenType.ELSE);
        st.put("while", TokenType.WHILE);
        st.put("for", TokenType.FOR);
        st.put("foreach", TokenType.FOREACH);
        st.put("def", TokenType.DEF);
        st.put("print", TokenType.PRINT);
        st.put("println", TokenType.PRINTLN);
        st.put("in", TokenType.IN);
        st.put("as", TokenType.AS);
        st.put("Boolean", TokenType.BOOLEAN);
        st.put("Integer", TokenType.INTEGER);
        st.put("String", TokenType.STRING);
        st.put("function", TokenType.FUNCTION);
        st.put("switch", TokenType.SWITCH);
        st.put("struct", TokenType.STRUCT);
        st.put("read", TokenType.READ);
        st.put("empty", TokenType.EMPTY);
        st.put("size", TokenType.SIZE);
        st.put("values", TokenType.VALUES);
        st.put("keys", TokenType.VALUES);
        st.put("case", TokenType.CASE);
        st.put("default", TokenType.DEFAULT);
        st.put("null", TokenType.NULL);
        st.put("false", TokenType.FALSE);
        st.put("true", TokenType.TRUE);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ? st.get(token) : TokenType.NAME;
    }
}
