/*
 * ExpressionException.java
 * 
 * Created on 07.06.2007, 8:40:55
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tdo.tools.scanner;

/**
 *
 * @author valery
 */
public class ExpressionException extends RuntimeException {
    
    private int code;
    private int symbolNumber;
    //private String text;
    
    public ExpressionException() {
        super();
    }
    public ExpressionException(String msg) {
        super(msg);
        code = -1;
        symbolNumber = 0;
    }
    public ExpressionException(int code, int symbolNumber, String msg) {
        super(msg);
        this.code = code;
        if (symbolNumber < 0)
            this.symbolNumber = 0;
        else
            this.symbolNumber = symbolNumber;
    }

    public int getCode() {
        return this.code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getSymbolNumber() {
        return this.symbolNumber;
    }
    public void setSymbolNumber(int symbolNumber) {
        this.symbolNumber = symbolNumber;
    }
/*    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
*/    
}//ExpressionException
