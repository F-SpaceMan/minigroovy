package lexical;

public enum TokenType {
    // SPECIALS
    UNEXPECTED_EOF,
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS
    ASSIGN, // =
    COMMA, // ,
    PARENTESIS_OPEN, // (
    PARENTESIS_CLOSE, // )
    SEMICOLON, // ;
    BRACE_OPEN, // {
    BRACE_CLOSE, // }
    BRACKET_OPEN, // [
    BRACKET_CLOSE, // ]
    COLON, // :

    // OPERATORS
    PLUS_ASSIGN, // +=
    MINUS_ASSIGN, // -=
    MULT_ASSIGN, // *=
    MOD_ASSIGN, // %=
    DIV_ASSIGN, // /=
    POW_ASSIGN, // ^=
    AND, // &&
    OR, // ||
    LESS_THAN, // <
    LESS_THAN_EQUAL, // <=
    EQUAL, // ==
    GREATER_THAN, // >
    GREATER_THAN_EQUAL, // >=
    NOT_EQUAL, // !=
    NOT_IN, // !in
    PLUS, // +
    MINUS, // -
    MULT, // *
    DIV, // /
    MOD, // %
    POW, // ^
    NOT, // !
    ARROW, // ->
    DOT, // .

    // KEYWORDS
    IF, // if
    ELSE, // else
    WHILE, // while
    FOR, // for
    FOREACH, // foreach
    DEF, // definition
    PRINT, // print
    PRINTLN, // println
    IN, // in
    AS, // as
    BOOLEAN, // boolean
    INTEGER, // integer
    STRING, // string
    FUNCTION, // function
    SWITCH, // switch
    STRUCT, // struct
    READ, // read
    EMPTY, // empty
    SIZE, // size
    VALUES, // values
    KEYS, // keys
    CASE, // case
    DEFAULT, // default
    NULL, // null
    FALSE, // false
    TRUE, // true

    // OTHERS
    NAME, // identifier
    NUMBER, // integer
    TEXT, // string
    DECL, // declaration
    DECL1, // declaration_type1
    DECL2, // declaration_type2
    EXPR // expression
};
