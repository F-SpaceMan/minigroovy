package syntatic;

import interpreter.command.Command;
import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;

import java.util.Stack;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;
    private Stack<Lexeme> history;
    private Stack<Lexeme> queued;

    public SyntaticAnalysis(LexicalAnalysis lex) {
        this.lex = lex;
        this.current = lex.nextToken();
        this.history = new Stack<Lexeme>();
        this.queued = new Stack<Lexeme>();
    }

    public void start() {
        procCode();
        eat(TokenType.END_OF_FILE);
    }

    private void rollback() {
        assert !history.isEmpty();

        // System.out.println("Rollback (\"" + current.token + "\", " +
        // current.type + ")");
        queued.push(current);
        current = history.pop();
    }

    private void advance() {
        // System.out.println("Advanced (\"" + current.token + "\", " +
        // current.type + ")");
        history.add(current);
        current = queued.isEmpty() ? lex.nextToken() : queued.pop();
    }

    private void eat(TokenType type) {
        // System.out.println("Expected (..., " + type + "), found (\"" +
        // current.token + "\", " + current.type + ")");
        if (type == current.type) {
            advance();
            // history.add(current);
            // current = queued.isEmpty() ? lex.nextToken() : queued.pop();
        } else {
            showError();
        }
    }

    private void showError() {
        System.out.printf("%02d: ", lex.getLine());

        switch (current.type) {
            case INVALID_TOKEN:
                System.out.printf("Lexema inválido [%s]\n", current.token);
                break;
            case UNEXPECTED_EOF:
            case END_OF_FILE:
                System.out.printf("Fim de arquivo inesperado\n");
                break;
            default:
                System.out.printf("Lexema não esperado [%s]\n", current.token);
                break;
        }

        System.exit(1);
    }

    // <code> ::= { <cmd> }
    private void procCode() {
        while (current.type != TokenType.END_OF_FILE) {
            procCmd();
        }
    }

    // <cmd> ::= <decl> | <print> | <if> | <while> | <for> | <foreach> | <assign>
    private void procCmd() {
        if (current.type == TokenType.DECL) {
            procDecl();
        } else if (current.type == TokenType.PRINT) {
            procPrint();
        } else if (current.type == TokenType.IF) {
            procIf();
        } else if (current.type == TokenType.WHILE) {
            procWhile();
        } else if (current.type == TokenType.FOR) {
            procFor();
        } else if (current.type == TokenType.FOREACH) {
            procForeach();
        } else if (current.type == TokenType.ASSIGN) {
            procAssign();
        } else {
            showError();
        }

    }

    // <decl> ::= def ( <decl-type1> | <decl-type2> )
    private void procDecl() {
        eat(TokenType.DEF);

        if (current.type == TokenType.DECL1) {
            procDeclType1();
        } else if (current.type == TokenType.DECL2) {
            procDeclType2();
        } else {
            showError();
        }
    }

    // <decl-type1> ::= <name> [ '=' <expr> ] { ',' <name> [ '=' <expr> ] }
    private void procDeclType1() {
        eat(TokenType.NAME);

        if (current.type == TokenType.ASSIGN) {
            eat(TokenType.ASSIGN);
            procExpr();
        }

        while (current.type == TokenType.COMMA) {
            eat(TokenType.COMMA);
            eat(TokenType.NAME);

            if (current.type == TokenType.ASSIGN) {
                eat(TokenType.ASSIGN);
                procExpr();
            }
        }
    }

    // <decl-type2> ::= '(' <name> { ',' <name> } ')' = <expr>
    private void procDeclType2() {
        eat(TokenType.PARENTESIS_OPEN);
        eat(TokenType.NAME);

        while (current.type == TokenType.COMMA) {
            eat(TokenType.COMMA);
            eat(TokenType.NAME);
        }

        eat(TokenType.PARENTESIS_CLOSE);
        eat(TokenType.ASSIGN);

        procExpr();
    }

    // <print> ::= (print | println) '(' <expr> ')'
    private void procPrint() {
        if (current.type == TokenType.PRINT) {
            eat(TokenType.PRINT);
        } else {
            eat(TokenType.PRINTLN);
        }

        eat(TokenType.PARENTESIS_OPEN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
    }

    // <if> ::= if '(' <expr> ')' <body> [ else <body> ]
    private void procIf() {
        eat(TokenType.IF);
        eat(TokenType.PARENTESIS_OPEN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
        procBody();

        if (current.type == TokenType.ELSE) {
            eat(TokenType.ELSE);
            procBody();
        }
    }

    // <while> ::= while '(' <expr> ')' <body>
    private void procWhile() {
        eat(TokenType.WHILE);
        eat(TokenType.PARENTESIS_OPEN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
        procBody();
    }

    // o que aqui está como decl era def. Era pra ser assim msm? falar com o ANDREI

    // <for> ::= for '(' [ ( <decl> | <assign> ) { ',' ( <decl> | <assign> ) } ] ';'
    // [ <expr> ] ';' [ <assign> { ',' <assign> } ] ')' <body>
    private void procFor() {
        eat(TokenType.FOR);
        eat(TokenType.PARENTESIS_OPEN);
        if (current.type == TokenType.DECL || current.type == TokenType.ASSIGN) {
            procDecl();
        }
        while (current.type == TokenType.COMMA) {
            eat(TokenType.COMMA);
            if (current.type == TokenType.DECL || current.type == TokenType.ASSIGN) {
                procDecl();
            }
        }
        eat(TokenType.SEMICOLON);
        if (current.type != TokenType.END_OF_FILE) {
            procExpr();
        }
        eat(TokenType.SEMICOLON);
        if (current.type == TokenType.ASSIGN) {
            procAssign();
        }
        while (current.type == TokenType.COMMA) {
            eat(TokenType.COMMA);
            if (current.type == TokenType.ASSIGN) {
                procAssign();
            }
        }
        eat(TokenType.PARENTESIS_CLOSE);
        procBody();
    }

    // <foreach> ::= foreach '(' [ def ] <name> in <expr> ')' <body>
    private void procForeach() {
        eat(TokenType.FOREACH);
        eat(TokenType.PARENTESIS_OPEN);
        if (current.type == TokenType.DEF) {
            eat(TokenType.DEF);
        }
        eat(TokenType.NAME);
        eat(TokenType.IN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
        procBody();
    }

    // <body> ::= <cmd> | '{' <code> '}'
    private void procBody() {
        if (current.type == TokenType.BRACE_OPEN) {
            eat(TokenType.BRACE_OPEN);
            procCode();
            eat(TokenType.BRACE_CLOSE);
        } else {
            procCmd();
        }
    }

    // <assign> ::= [ <expr> ( '=' | '+=' | '-=' | '*=' | '/=' | '%=' | '**=') ]
    // <expr>
    private void procAssign() {
        if (current.type == TokenType.EXPR) {
            procExpr();
        }
        if (current.type == TokenType.ASSIGN) {
            eat(TokenType.ASSIGN);
        } else if (current.type == TokenType.PLUS_ASSIGN) {
            eat(TokenType.PLUS_ASSIGN);
        } else if (current.type == TokenType.MINUS_ASSIGN) {
            eat(TokenType.MINUS_ASSIGN);
        } else if (current.type == TokenType.MULT_ASSIGN) {
            eat(TokenType.MULT_ASSIGN);
        } else if (current.type == TokenType.DIV_ASSIGN) {
            eat(TokenType.DIV_ASSIGN);
        } else if (current.type == TokenType.MOD_ASSIGN) {
            eat(TokenType.MOD_ASSIGN);
        } else if (current.type == TokenType.POW_ASSIGN) {
            eat(TokenType.POW_ASSIGN);
        } else {
            showError();
        }
        procExpr();
    }

    // <expr> ::= <rel> { ('&&' | '||') <rel> }
    private void procExpr() {
        procRel();

        while (current.type == TokenType.AND || current.type == TokenType.OR) {
            if (current.type == TokenType.AND) {
                eat(TokenType.AND);
            } else {
                eat(TokenType.OR);
            }
            procRel();
        }
    }

    // <rel> ::= <cast> [ ('<' | '>' | '<=' | '>=' | '==' | '!=' | in | '!in')
    // <cast> ]
    private void procRel() {
        procCast();

        if (current.type == TokenType.LESS_THAN || current.type == TokenType.GREATER_THAN ||
                current.type == TokenType.LESS_THAN_EQUAL || current.type == TokenType.GREATER_THAN_EQUAL ||
                current.type == TokenType.EQUAL || current.type == TokenType.NOT_EQUAL ||
                current.type == TokenType.IN || current.type == TokenType.NOT_IN) {
            if (current.type == TokenType.LESS_THAN) {
                eat(TokenType.LESS_THAN);
            } else if (current.type == TokenType.GREATER_THAN) {
                eat(TokenType.GREATER_THAN);
            } else if (current.type == TokenType.LESS_THAN_EQUAL) {
                eat(TokenType.LESS_THAN_EQUAL);
            } else if (current.type == TokenType.GREATER_THAN_EQUAL) {
                eat(TokenType.GREATER_THAN_EQUAL);
            } else if (current.type == TokenType.EQUAL) {
                eat(TokenType.EQUAL);
            } else if (current.type == TokenType.NOT_EQUAL) {
                eat(TokenType.NOT_EQUAL);
            } else if (current.type == TokenType.IN) {
                eat(TokenType.IN);
            } else {
                eat(TokenType.NOT_IN);
            }
            procCast();
        }
    }

    // <cast> ::= <arith> [ as ( Boolean | Integer | String) ]
    private void procCast() {
        procArith();

        if (current.type == TokenType.AS) {
            eat(TokenType.AS);
            if (current.type == TokenType.BOOLEAN) {
                eat(TokenType.BOOLEAN);
            } else if (current.type == TokenType.INTEGER) {
                eat(TokenType.INTEGER);
            } else if (current.type == TokenType.STRING) {
                eat(TokenType.STRING);
            } else {
                showError();
            }
        }
    }

    // <arith> ::= <term> { ('+' | '-') <term> }
    private void procArith() {
        procTerm();

        while (current.type == TokenType.PLUS || current.type == TokenType.MINUS) {
            if (current.type == TokenType.PLUS) {
                eat(TokenType.PLUS);
            } else {
                eat(TokenType.MINUS);
            }
            procTerm();
        }
    }

    // <term> ::= <power> { ('*' | '/' | '%') <power> }
    private void procTerm() {
        procPower();

        while (current.type == TokenType.MULT || current.type == TokenType.DIV || current.type == TokenType.MOD) {
            if (current.type == TokenType.MULT) {
                eat(TokenType.MULT);
            } else if (current.type == TokenType.DIV) {
                eat(TokenType.DIV);
            } else {
                eat(TokenType.MOD);
            }
            procPower();
        }
    }

    // <power> ::= <factor> { '**' <factor> }
    private void procPower() {
        procFactor();

        while (current.type == TokenType.POW) {
            eat(TokenType.POW);
            procFactor();
        }
    }

    // <factor> ::= [ '!' | '-' ] ( '(' <expr> ')' | <rvalue> )
    private void procFactor() {
        if (current.type == TokenType.NOT || current.type == TokenType.MINUS) {
            if (current.type == TokenType.NOT) {
                eat(TokenType.NOT);
            } else {
                eat(TokenType.MINUS);
            }
        }
        if (current.type == TokenType.PARENTESIS_OPEN) {
            eat(TokenType.PARENTESIS_OPEN);
            procExpr();
            eat(TokenType.PARENTESIS_CLOSE);
        } else {
            procRvalue();
        }
    }

    // <lvalue> ::= <name> { '.' <name> | '[' <expr> ']' }
    private void procLvalue() {
        eat(TokenType.NAME);

        while (current.type == TokenType.DOT || current.type == TokenType.BRACKET_OPEN) {
            if (current.type == TokenType.DOT) {
                eat(TokenType.DOT);
                eat(TokenType.NAME);
            } else {
                eat(TokenType.BRACKET_OPEN);
                procExpr();
                eat(TokenType.BRACKET_CLOSE);
            }
        }
    }

    // <rvalue> ::= <const> | <function> | <switch> | <struct> | <lvalue>
    private void procRvalue() {
        if (current.type == TokenType.BOOLEAN || current.type == TokenType.INTEGER ||
                current.type == TokenType.STRING) {
            procConst();
        } else if (current.type == TokenType.FUNCTION) {
            procFunction();
        } else if (current.type == TokenType.SWITCH) {
            procSwitch();
        } else if (current.type == TokenType.STRUCT) {
            procStruct();
        } else {
            procLvalue();
        }
    }

    // <const> ::= null | false | true | <number> | <text>
    private void procConst() {
        if (current.type == TokenType.NULL) {
            eat(TokenType.NULL);
        } else if (current.type == TokenType.FALSE) {
            eat(TokenType.FALSE);
        } else if (current.type == TokenType.TRUE) {
            eat(TokenType.TRUE);
        } else if (current.type == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
        } else if (current.type == TokenType.TEXT) {
            eat(TokenType.TEXT);
        } else {
            showError();
        }
    }

    // <function> ::= (read | empty | size | keys | values) '(' <expr> ')'
    private void procFunction() {
        if (current.type == TokenType.READ) {
            eat(TokenType.READ);
        } else if (current.type == TokenType.EMPTY) {
            eat(TokenType.EMPTY);
        } else if (current.type == TokenType.SIZE) {
            eat(TokenType.SIZE);
        } else if (current.type == TokenType.KEYS) {
            eat(TokenType.KEYS);
        } else if (current.type == TokenType.VALUES) {
            eat(TokenType.VALUES);
        } else {
            showError();
        }
        eat(TokenType.PARENTESIS_OPEN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
    }

    // <switch> ::= switch '(' <expr> ')' '{' { case <expr> '->' <expr> } [ default
    // '->' <expr> ] '}'
    private void procSwitch() {
        eat(TokenType.SWITCH);
        eat(TokenType.PARENTESIS_OPEN);
        procExpr();
        eat(TokenType.PARENTESIS_CLOSE);
        eat(TokenType.BRACE_OPEN);

        while (current.type == TokenType.CASE) {
            eat(TokenType.CASE);
            procExpr();
            eat(TokenType.ARROW);
            procExpr();
        }

        if (current.type == TokenType.DEFAULT) {
            eat(TokenType.DEFAULT);
            eat(TokenType.ARROW);
            procExpr();
        }

        eat(TokenType.BRACE_CLOSE);
    }

    // <struct> ::= '[' [ ':' | <expr> { ',' <expr> } | <name> ':' <expr> { ','
    // <name> ':' <expr> } ] ']'
    private void procStruct() {
        eat(TokenType.BRACKET_OPEN);

        if (current.type == TokenType.COLON) {
            eat(TokenType.COLON);
        } else {
            procExpr();
            while (current.type == TokenType.COMMA) {
                eat(TokenType.COMMA);
                procExpr();
            }
        }

        while (current.type == TokenType.COLON) {
            eat(TokenType.COLON);
            eat(TokenType.NAME);
            eat(TokenType.COLON);
            procExpr();
            while (current.type == TokenType.COMMA) {
                eat(TokenType.COMMA);
                eat(TokenType.NAME);
                eat(TokenType.COLON);
                procExpr();
            }
        }

        eat(TokenType.BRACKET_CLOSE);
    }

}
