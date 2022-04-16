package lexical;

public enum TokenType {
    // SPECIALS
    UNEXPECTED_EOF,
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS

    // OPERATORS

    // KEYWORDS

    // OTHERS
    NAME, // identifier
    NUMBER, // integer
    TEXT, // string

    DECL,
    PRINT,
    IF,
    WHILE,
    FOR,
    FOREACH,
    ASSIGN, DEF, DECL1, DECL2, COMMA, PARENTESIS_OPEN, PARENTESIS_CLOSE, PRINTLN, ELSE, SEMICOLON, IN, CURLY_BRACKET_OPEN, CURLY_BRACKET_CLOSE, EXPR, PLUS_ASSIGN, MINUS_ASSIGN, MULT_ASSIGN, MOD_ASSIGN, DIV_ASSIGN, POW_ASSIGN, AND, OR, LESS_THAN, LESS_THAN_EQUAL, EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, NOT_EQUAL, NOT_IN, AS, BOOLEAN, INTEGER, STRING, PLUS, MINUS, MULT, DIV, MOD, POW, NOT, DOT, BRACKET_OPEN, BRACKET_CLOSE, FUNCTION, SWITCH, STRUCT, READ, EMPTY, SIZE, VALUES, KEYS, BRACE_OPEN, CASE, ARROW, DEFAULT, COLON, NULL, FALSE, TRUE
};