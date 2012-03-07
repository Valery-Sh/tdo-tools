/*
 * LParser.java
 *
 * Created on 06.06.2007, 9:11:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tdo.tools.scanner;

import java.io.IOException;
import tdo.tools.expr.Symbol;

/**
 * Класс является оболочкой класса {@link LScanner}.
 * Метод {@link #getNextSymbol} позволяет получть доступ к следующей лексеме
 * в потоке, но при этом, сохраняет текущее состояние, т.е. последующий
 * вызов метода {@link #nextSymbol} работает так, как будто не было вызова
 * getNextSymbol. Это позволяет уточнить информацию о текущей лексеме. Например,
 * если текущей лексемой является <code>IDENTIFIER</code>, то мы можем получить
 * следующуя за идентификатором лексему, и, если это открывающая круглая скобка,
 * то мы счтаем, что имеем дело с именем функции, а не именем колонки.
 *
 */
public class BufferedScanner {
    
    protected LScanner scanner;
    //    protected Symbol lastSymbol;
    protected Symbol buffer;
    protected String expression;
    
    protected Symbol nextSymbol;
    protected boolean eofReached = false;
    protected boolean hasNextExecuted = false;
    
    protected int symPos = 0;
    
    public BufferedScanner(String expr) {
        expression = expr;
        nextSymbol = null;
        eofReached = false;
        hasNextExecuted = false;
        scanner = new LScanner(new java.io.StringReader(expr));
    }
    
    public boolean hasNextSymbol() {
        try {
            if (hasNextExecuted) {
                return !nextSymbol.isEof();
            }
            hasNextExecuted = true;
            if (eofReached) {
                return false;
            }
            if (buffer != null && !buffer.isEof()) {
                nextSymbol = buffer;
                buffer = null;
                return true;
            } else {
                if (buffer != null) {
                    nextSymbol = buffer;
                    buffer = null;
                    eofReached = nextSymbol.isEof();
                    return false;
                }
            }
            nextSymbol = scanner.nextToken();
            eofReached = nextSymbol.isEof();
            return !nextSymbol.isEof();
        } catch (IOException ex) {
            throw new ExpressionException(-1,symPos,"BufferedScanner.hasNextSymbol() :SEVERE. " + ex.getMessage());
        } catch( RuntimeException rte) {
            throw new ExpressionException(-2,symPos,"BufferedScanner.hasNextSymbol() :SEVERE. " + rte.getMessage());
        }
   
    }
    
    
    public Symbol nextSymbol() {
        tdo.tools.expr.Symbol result = null;
        
        try {
            hasNextExecuted = false;
            if (nextSymbol != null && nextSymbol.isEof()) {
                throw new ExpressionException(1,symPos,"BufferedScanner.nextSymbol() : No tokens found ");
            }
            symPos++;
            if (nextSymbol != null) {
                result = nextSymbol;
                nextSymbol = null;
                return result;
            }
            if (buffer != null) {
                result = buffer;
                buffer = null;
            } else {
                result = scanner.nextToken();
            }
            if (result.isEof()) {
                throw new ExpressionException(2,symPos,"BufferedScanner.nextSymbol() : No tokens found ");
            }
            
        } catch (IOException ex) {
            throw new ExpressionException(-3,symPos,"BufferedScanner.nextSymbol() :SEVERE. " + ex.getMessage());
        } catch( RuntimeException rte) {
            throw new ExpressionException(-4,symPos,"BufferedScanner.nextSymbol() : " + rte.getMessage());
        }
        
        return result;
        
    }
    
    public Symbol peekNextSymbol(){
        if (buffer != null) {
            throw new ExpressionException(3,symPos,"BufferedScanner.getNextSymbol() : cannot be invoked twice");
        }
        
        try {
            if (nextSymbol != null) {
                return nextSymbol;
            }
            buffer = scanner.nextToken();
            return buffer;
        } catch (IOException ex) {
            throw new ExpressionException(-5,symPos,"BufferedScanner.getNextSymbol() :SEVERE. " + ex.getMessage());
        } catch( RuntimeException rte) {
            throw new ExpressionException(-6,symPos,"BufferedScanner.getNextSymbol() : " + rte.getMessage());

        }  
    }
    
    public void reset() {
        buffer = null;
        nextSymbol = null;
        eofReached = false;
        hasNextExecuted = false;
        scanner = new LScanner(new java.io.StringReader(expression));
        symPos = 0;
    }
}//BufferedScanner
