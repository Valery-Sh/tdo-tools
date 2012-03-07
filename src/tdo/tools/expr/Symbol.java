/*
 * LSymbol.java
 *
 * Created on 04.06.2007, 13:50:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tdo.tools.expr;


/* ****************************************************************
  Class LSymbol
  what the parser expects to receive from the lexer.
  the token is identified as follows:
  sym:    the symbol type
  parse_state: the parse state.
  value:  is the lexical value of type Object
  left :  is the left position in the original input file
  right:  is the right position in the original input file
 ******************************************************************/

public class Symbol implements LexConst{
    
    /*******************************
  Constructor for l,r values
     ******************************* @param id 
     * @param l 
     * @param r 
     * @param o 
     */
    
    public Symbol(int id, int l, int r, Object o) {
        this(id);
        left = l;
        right = r;
        value = o;
    }
    
    /*******************************
     *   Constructor for no l,r values.
     * @param id 
     * @param o 
     */
    
    public Symbol(int id, Object o) {
        this(id, -1, -1, o);
    }
    
    /*****************************
  Constructor for no value
     *************************** 
     * @param id 
     * @param l 
     * @param r 
     */
    
    public Symbol(int id, int l, int r) {
        this(id, l, r, null);
    }
    
    /***********************************
     *  Constructor for no value or l,r
     * @param sym_num 
     */
    public Symbol(int sym_num) {
        this(sym_num, -1);
        left = -1;
        right = -1;
        value = null;
    }
    
    public boolean isEof() {
        return this.sym == EOF;
    }
    /***********************************
  Constructor to give a start state
     ***********************************/
    Symbol(int sym_num, int state) {
        sym = sym_num;
        parse_state = state;
    }
    
    
    /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/
    
    /** The symbol number of the terminal or non terminal being represented */
    public int sym;
    
    /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/
    
    /** The parse state to be recorded on the parse stack with this symbol.
     *  This field is for the convenience of the parser and shouldn't be
     *  modified except by the parser.
     */
    public int parse_state;
    /** This allows us to catch some errors caused by scanners recycling
     *  symbols.  For the use of the parser only. [CSA, 23-Jul-1999] */
    boolean used_by_parser = false;
    
    /*******************************
        The data passed to parser
     *******************************/
    
    public int left, right;
    public Object value;

    public boolean isOperator() {

        if ( sym == COMMA || sym == EQ || sym == GT || sym == LT || sym == LTEQ ||
             sym == GTEQ || sym == NOTEQ || sym == AND || sym == OR || sym == NOT ||
             sym == PLUS || sym == MINUS || sym == MULT || sym == DIV || sym == CONCAT ||
             sym == ISNULL || sym == ISNOTNULL || sym == BETWEEN || sym == NOTBETWEEN ||                      
             sym == IN || sym == NOTIN || sym == CONTAINING || sym == NOTCONTAINING ||                                                      
             sym == STARTINGWITH || sym == NOTSTARTINGWITH || sym == LIKE || sym == NOTLIKE ||                                          
             sym == REGEX || sym == NOTREGEX || sym == ANDBETWEEN ) {
                
            return true;
        } else
            return false;
    }

    public boolean isOperand() {
        if ( sym == BOOLEAN_LITERAL ||  sym == INTEGER_LITERAL ||
             sym == FLOATING_POINT_LITERAL || sym == STRING_LITERAL ||
             sym == IDENTIFIER || sym == PARAMETER)
             return true;
        else
            return false;
    }
    public boolean isIdentifier() {
        if ( sym == IDENTIFIER )
             return true;
        else
            return false;
    }
    public boolean isParameter() {
        if ( sym == PARAMETER )
             return true;
        else
            return false;
    }
    
    public boolean isParenthesis() {
        if ( sym == LPAREN || sym == RPAREN )
             return true;
        else
            return false;
    }
    public boolean isLeftParenthesis() {
        if ( sym == LPAREN )
             return true;
        else
            return false;
    }
    public boolean isRightParenthesis() {
        if ( sym == RPAREN )
             return true;
        else
            return false;
    }
    
    /*****************************
    Printing this token out. (Override for pretty-print).
     ****************************/
    public String toString() {
        String s = "";
        switch ( sym ) {
        case EOF :
            s = "EOF";
            break;
        case BOOLEAN_LITERAL :
            s = "BOOLEAN_LITERAL = " + this.value.toString();
            break;
        case INTEGER_LITERAL  :
            s = "INTEGER_LITERAL = " + this.value.toString();
            break;
        case FLOATING_POINT_LITERAL :
            s = "FLOATING_POINT_LITERAL = " + this.value.toString();
            break;
        case STRING_LITERAL :
            s = "STRING_LITERAL = " + this.value.toString();
            break;
            
        case IDENTIFIER :
            s = "IDENTIFIER = " + this.value.toString();
            break;
        case PARAMETER :
            s = "PARAMETER = " + this.value.toString();
            break;
        case LPAREN :
            s = "LPAREN";
            break;
        case RPAREN :
            s = "RPAREN";
            break;
        case COMMA :
            s = "COMMA" ;
            break;
        case DOT :
            s = "DOT";
            break;
            
        case EQ :
            s = "EQ";
            break;
        case GT :
            s = "GT";
            break;
        case LT :
            s = "LT";
            break;
        case LTEQ :
            s = "LTEQ";
            break;
        case GTEQ :
            s = "GTEQ";
            break;
        case NOTEQ :
            s = "NOTEQ";
            break;
        case AND :
            s = "AND";
            break;
        case OR :
            s = "OR";
            break;
        case NOT :
            s = "NOT";
            break;
        case PLUS :
            s = "PLUS";
            break;
        case MINUS :
            s = "MINUS";
            break;
        case MULT :
            s = "MULT";
            break;
        case DIV :
            s = "DIV";
            break;
        case CONCAT :
            s = "CONCAT";
            break;
            
        case ISNULL :
            s = "IS NULL";
            break;
        case ISNOTNULL :
            s = "IS NOT NULL";
            break;
        case BETWEEN :
            s = "BETWEEN";
            break;
        case NOTBETWEEN :
            s = "NOT BETWEEN";
            break;
        case CONTAINING :
            s = "CONTAINING";
            break;
        case NOTCONTAINING :
            s = "NOTCONTAINING";
            break;
        case STARTINGWITH :
            s = "STARTINGWITH";
            break;
        case NOTSTARTINGWITH :
            s = "NOTSTARTINGWITH";
            break;
            
        case IN :
            s = "IN";
            break;
        case NOTIN :
            s = "NOT IN";
            break;
        case LIKE :
            s = "LIKE";
            break;
        case NOTLIKE :
            s = "NOT LIKE";
            break;
        case REGEX :
            s = "REGEX";
            break;
        case NOTREGEX :
            s = "NOT REGEX";
            break;
        case ANDBETWEEN :
            s = "ANDBETTWEEN";
            break;
        }
        
        return s;
    }
    
    
}





